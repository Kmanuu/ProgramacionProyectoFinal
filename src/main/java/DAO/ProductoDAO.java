package DAO;

import baseDatos.ConnectionDB;
import model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    /**
     * Inserta un nuevo producto en la base de datos si no existe previamente.
     *
     * @param p El producto a insertar.
     * @return El producto insertado si se ha realizado correctamente; null si ya existe o es nulo.
     */
    public static Producto insertProducto(Producto p) {
        if (p != null && findById(p.getIdProducto()) == null) {
            Connection con = ConnectionDB.getConnection();
            String sql = "INSERT INTO producto (nombre, marca, categoria, anio, precio, stock, imagen) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try {
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, p.getNombre());
                pst.setString(2, p.getMarca());
                pst.setString(3, p.getCategoria());
                pst.setInt(4, p.getAnio());
                pst.setDouble(5, p.getPrecio());
                pst.setInt(6, p.getStock());
                pst.setString(7, p.getImagen());

                pst.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return p;
        }
        return null;
    }

    /**
     * Obtiene todos los productos de la base de datos.
     *
     * @return Lista de todos los productos encontrados.
     */
    public static List<Producto> getAll() {
        List<Producto> productos = new ArrayList<>();
        Connection con = ConnectionDB.getConnection();
        String sql = "SELECT * FROM producto";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setMarca(rs.getString("marca"));
                producto.setCategoria(rs.getString("categoria"));
                producto.setAnio(rs.getInt("anio"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setImagen(rs.getString("imagen"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return productos;
    }

    /**
     * Busca un producto en la base de datos por su ID.
     *
     * @param id El ID del producto a buscar.
     * @return El producto encontrado si existe; null si no se encuentra.
     */
    public static Producto findById(int id) {
        Producto producto = null;
        Connection con = ConnectionDB.getConnection();
        String sql = "SELECT * FROM producto WHERE idProducto = ?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                producto = new Producto();
                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setMarca(rs.getString("marca"));
                producto.setCategoria(rs.getString("categoria"));
                producto.setAnio(rs.getInt("anio"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setImagen(rs.getString("imagen"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return producto;
    }

    /**
     * Actualiza los datos de un producto existente en la base de datos.
     *
     * @param producto El producto con los datos actualizados.
     */
    public static void update(Producto producto) {
        String sql = "UPDATE producto SET nombre = ?, marca = ?, categoria = ?, anio = ?, precio = ?, stock = ?, imagen = ? WHERE idProducto = ?";
        Connection con = ConnectionDB.getConnection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getMarca());
            pst.setString(3, producto.getCategoria());
            pst.setInt(4, producto.getAnio());
            pst.setDouble(5, producto.getPrecio());
            pst.setInt(6, producto.getStock());
            pst.setString(7, producto.getImagen());
            pst.setInt(8, producto.getIdProducto());

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Elimina un producto de la base de datos por su ID.
     *
     * @param id El ID del producto a eliminar.
     */
    public static void delete(int id) {
        String sql = "DELETE FROM producto WHERE idProducto = ?";
        Connection con = ConnectionDB.getConnection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
