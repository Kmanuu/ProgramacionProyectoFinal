import baseDatos.ConnectionDB;
import java.sql.Connection;

public class pruebas {
    public static void main(String[] args) {
        Connection con = ConnectionDB.getConnection();
        if (con != null) {
            System.out.println("🔥 Conexión exitosa a la base de datos.");
        } else {
            System.out.println("❌ Error al conectar a la base de datos.");
        }

        // Cerramos la conexión al final (opcional pero buena práctica)
        ConnectionDB.closeConnection();
    }
}
