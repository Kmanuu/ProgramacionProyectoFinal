package DAO;

import baseDatos.ConnectionDB;
import model.Cliente;
import model.Usuario;
import model.Vendedor;

import java.sql.*;

/**
 * DAO que gestiona los usuarios en la base de datos.
 */
public class UsuarioDAO {

    // Sentencias SQL para insertar y hacer login
    private static final String SQL_INSERT = "INSERT INTO usuario (nombre, email, contrasena) VALUES (?, ?, ?)";
    private static final String SQL_LOGIN = "SELECT * FROM usuario WHERE email = ? AND contrasena = ?";

    /**
     * Inserta un usuario en la tabla usuario.
     * Solo guarda los datos comunes (nombre, email, contraseña).
     * Luego se debe insertar en la tabla cliente o vendedor.
     */
    public static int insertUsuario(Usuario u) {
        int generatedId = -1;

        if (u == null) return -1;

        Connection con = ConnectionDB.getConnection();

        try {
            PreparedStatement pst = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, u.getNombre());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getContrasena());

            pst.executeUpdate();

            // Obtenemos el ID autogenerado
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
                u.setIdUsuario(generatedId); // lo guardamos en el objeto
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar usuario");
            e.printStackTrace();
        }

        return generatedId;
    }

    /**
     * Intenta iniciar sesión con el email y contraseña.
     * Si encuentra al usuario, determina si es cliente o vendedor.
     * Devuelve un objeto Cliente o Vendedor según el caso.
     */
    public static Usuario login(String email, String contrasena) {
        Usuario usuario = null;

        Connection con = ConnectionDB.getConnection();

        try {
            PreparedStatement pst = con.prepareStatement(SQL_LOGIN);
            pst.setString(1, email);
            pst.setString(2, contrasena);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("idUsuario");
                String nombre = rs.getString("nombre");

                // Comprobamos si el usuario es cliente
                Cliente cliente = ClienteDAO.findById(id);
                if (cliente != null) {
                    cliente.setIdUsuario(id);
                    cliente.setNombre(nombre);
                    cliente.setEmail(email);
                    cliente.setContrasena(contrasena);
                    usuario = cliente;
                } else {
                    // Si no es cliente, comprobamos si es vendedor
                    Vendedor vendedor = VendedorDAO.findById(id);
                    if (vendedor != null) {
                        vendedor.setIdUsuario(id);
                        vendedor.setNombre(nombre);
                        vendedor.setEmail(email);
                        vendedor.setContrasena(contrasena);
                        usuario = vendedor;
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al hacer login");
            e.printStackTrace();
        }

        return usuario;
    }
}
