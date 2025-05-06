package baseDatos;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class ConnectionDB {

    private final static String FILE = "connection.xml";
    private static Connection con;
    private static ConnectionDB _instance;

    private ConnectionDB() {
        ConnectionProperties properties = XMLManager.readXML(new ConnectionProperties(), FILE);
        try {
            con = DriverManager.getConnection(properties.getUrl(),
                    properties.getUser(),
                    properties.getPassword()
            );

            System.out.println("✅ Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar con la base de datos");
            e.printStackTrace();
            con = null;
        }
    }

    public static Connection getConnection() {
        if (_instance == null) {
            _instance = new ConnectionDB();
        }
        return con;
    }

    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
                System.out.println("✅ Conexión cerrada");
            } catch (SQLException e) {
                System.out.println("❌ Error al cerrar conexión");
                e.printStackTrace();
            }
        }
    }
}
