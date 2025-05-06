package baseDatos;

import javax.xml.bind.*;
import java.io.File;

public class XMLManager {

    public static <T> T readXML(T object, String path) {
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Unmarshaller unmarshaller = context.createUnmarshaller();
            object = (T) unmarshaller.unmarshal(new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static <T> void writeXML(T object, String path) {
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(object, new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
