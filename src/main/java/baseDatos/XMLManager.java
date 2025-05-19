package baseDatos;

import javax.xml.bind.*;
import java.io.File;

// Esta clase se encarga de leer y escribir archivos XML usando JAXB
public class XMLManager {

    // Método genérico para leer un archivo XML y convertirlo en un objeto
    public static <T> T readXML(T object, String path) {
        try {
            // Preparamos el contexto JAXB para la clase del objeto
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Leemos el archivo XML y lo convertimos en el objeto
            object = (T) unmarshaller.unmarshal(new File(path));
        } catch (JAXBException e) {
            // Si hay error al leer, lo mostramos
            e.printStackTrace();
        }
        // Devolvemos el objeto con los datos del XML
        return object;
    }

    // Método genérico para guardar un objeto en un archivo XML
    public static <T> void writeXML(T object, String path) {
        try {
            // Preparamos el contexto JAXB para la clase del objeto
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();

            // Hacemos que el XML tenga formato legible (con saltos de línea)
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Guardamos el objeto en el archivo XML
            marshaller.marshal(object, new File(path));
        } catch (JAXBException e) {
            // Si hay error al escribir, lo mostramos
            e.printStackTrace();
        }
    }
}
