package DAO;

import baseDatos.ConnectionDB;
import model.Vendedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para gestionar vendedores en la base de datos.
 * El vendedor depende de que el usuario ya esté creado en la tabla usuario.
 */
public class VendedorDAO {

    // Consultas SQL que se van a reutilizar
    private static final String SQL_INSERT = "INSERT INTO vendedor (idUsuario, tienda, telefonoContacto, direccionTienda, descripcionTienda) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM vendedor WHERE idUsuario = ?";
    private static final String SQL_ALL = "SELECT * FROM vendedor";
    private static final String SQL_UPDATE = "UPDATE vendedor SET tienda = ?, telefonoContacto = ?, direccionTienda = ?, descripcionTienda = ? WHERE idUsuario = ?";
    private static final String SQL_DELETE = "DELETE FROM vendedor WHERE idUsuario = ?";

    /**
     * Inserta un nuevo vendedor si no existe aún.
     */
    public static Vendedor insertVendedor(Vendedor v) {
        if (v == null || findById(v.getIdUsuario()) != null) return null;

        Connection con = ConnectionDB.getConnection();

        try {
            PreparedStatement pst = con.prepareStatement(SQL_INSERT);
            pst.setInt(1, v.getIdUsuario());
            pst.setString(2, v.getTienda());
            pst.setString(3, v.getTelefonoContacto());
            pst.setString(4, v.getDireccionTienda());
            pst.setString(5, v.getDescripcionTienda());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar vendedor");
            e.printStackTrace();
        }

        return v;
    }

    /**
     * Busca un vendedor por su ID de usuario.
     */
    public static Vendedor findById(int id) {
        Vendedor v = null;
        Connection con = ConnectionDB.getConnection();

        try {
            PreparedStatement pst = con.prepareStatement(SQL_FIND_BY_ID);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                v = new Vendedor();
                v.setIdUsuario(id);
                v.setTienda(rs.getString("tienda"));
                v.setTelefonoContacto(rs.getString("telefonoContacto"));
                v.setDireccionTienda(rs.getString("direccionTienda"));
                v.setDescripcionTienda(rs.getString("descripcionTienda"));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar vendedor por ID");
            e.printStackTrace();
        }

        return v;
    }

    /**
     * Devuelve todos los vendedores guardados en la base de datos.
     */
    public static List<Vendedor> getAll() {
        List<Vendedor> lista = new ArrayList<>();
        Connection con = ConnectionDB.getConnection();

        try {
            PreparedStatement pst = con.prepareStatement(SQL_ALL);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Vendedor v = new Vendedor();
                v.setIdUsuario(rs.getInt("idUsuario"));
                v.setTienda(rs.getString("tienda"));
                v.setTelefonoContacto(rs.getString("telefonoContacto"));
                v.setDireccionTienda(rs.getString("direccionTienda"));
                v.setDescripcionTienda(rs.getString("descripcionTienda"));
                lista.add(v);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener todos los vendedores");
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Actualiza los datos de un vendedor.
     */
    public static void update(Vendedor v) {
        if (v == null) return;

        Connection con = ConnectionDB.getConnection();

        try {
            PreparedStatement pst = con.prepareStatement(SQL_UPDATE);
            pst.setString(1, v.getTienda());
            pst.setString(2, v.getTelefonoContacto());
            pst.setString(3, v.getDireccionTienda());
            pst.setString(4, v.getDescripcionTienda());
            pst.setInt(5, v.getIdUsuario());

            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar vendedor");
            e.printStackTrace();
        }
    }

    /**
     * Elimina un vendedor según su ID de usuario.
     */
    public static void delete(int id) {
        Connection con = ConnectionDB.getConnection();

        try {
            PreparedStatement pst = con.prepareStatement(SQL_DELETE);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar vendedor");
            e.printStackTrace();
        }
    }
}
