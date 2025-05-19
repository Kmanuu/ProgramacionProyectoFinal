package DAO;

import baseDatos.ConnectionDB;
import model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    /**
     * Inserta un nuevo pedido y devuelve el ID generado automáticamente.
     */
    public static int insert(Pedido pedido) {
        String sql = "INSERT INTO pedido (idCliente, fecha, estado) VALUES (?, ?, ?)";
        int idGenerado = -1;

        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Asignamos los valores del pedido
            pst.setInt(1, pedido.getIdCliente());
            pst.setDate(2, Date.valueOf(pedido.getFecha()));
            pst.setString(3, "pendiente"); // Estado por defecto

            pst.executeUpdate();

            // Obtenemos el ID generado automáticamente
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idGenerado;
    }

    /**
     * Devuelve todos los pedidos de un cliente según su ID.
     */
    public static List<Pedido> findByClienteId(int idCliente) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido WHERE idCliente = ?";

        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setIdCliente(rs.getInt("idCliente"));
                pedido.setFecha(rs.getString("fecha"));
                pedido.setEstado(rs.getString("estado"));
                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }

    /**
     * Busca un pedido por su ID.
     */
    public static Pedido findById(int id) {
        Pedido pedido = null;
        String sql = "SELECT * FROM pedido WHERE idPedido = ?";

        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setIdCliente(rs.getInt("idCliente"));
                pedido.setFecha(rs.getString("fecha"));
                pedido.setEstado(rs.getString("estado"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedido;
    }

    /**
     * Devuelve todos los pedidos de la base de datos.
     */
    public static List<Pedido> getAll() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido";

        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setIdCliente(rs.getInt("idCliente"));
                pedido.setFecha(rs.getString("fecha"));
                pedido.setEstado(rs.getString("estado"));
                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }

    /**
     * Actualiza los datos de un pedido.
     */
    public static void update(Pedido pedido) {
        String sql = "UPDATE pedido SET idCliente = ?, fecha = ?, estado = ? WHERE idPedido = ?";

        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, pedido.getIdCliente());
            pst.setString(2, pedido.getFecha());
            pst.setString(3, pedido.getEstado());
            pst.setInt(4, pedido.getIdPedido());
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un pedido por su ID.
     */
    public static void delete(int id) {
        String sql = "DELETE FROM pedido WHERE idPedido = ?";

        try (Connection con = ConnectionDB.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
