package baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    // Ruta del archivo XML donde están guardados los datos de conexión
    private final static String FILE = "connection.xml";

    // Método que abre y devuelve la conexión con la base de datos
    public static Connection getConnection() {
        // Leemos los datos de conexión (URL, usuario y contraseña) desde el XML
        ConnectionProperties properties = XMLManager.readXML(new ConnectionProperties(), FILE);
        try {
            // Intentamos conectar con la base de datos usando los datos obtenidos
            Connection con = DriverManager.getConnection(
                    properties.getUrl(),
                    properties.getUser(),
                    properties.getPassword()
            );
            System.out.println("✅ Conexión abierta"); // Para indicar que funciona
            return con;
        } catch (SQLException e) {
            // Para que avise que la conexión ha fallado
            System.out.println("❌ Error al conectar con la base de datos");
            e.printStackTrace();
            return null;
        }
    }
}
