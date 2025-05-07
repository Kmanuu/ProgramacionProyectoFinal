package DAO;

import baseDatos.ConnectionDB;
import model.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    /**
     * Inserta un nuevo pedido en la base de datos si no existe previamente.
     *
     * @param p El pedido a insertar.
     * @return El pedido insertado si se ha realizado correctamente; null si ya existe o es nulo.
     */
    public static Pedido insertPedido(Pedido p) {
        if (p != null && findById(p.getIdPedido()) == null) {
            Connection con = ConnectionDB.getConnection();
            String sql = "INSERT INTO pedido (idCliente, fecha, estado) VALUES (?, ?, ?)";

            try {
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, p.getIdCliente());
                pst.setString(2, p.getFecha());
                pst.setString(3, p.getEstado());

                pst.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return p;
        }
        return null;
    }

    /**
     * Obtiene todos los pedidos de la base de datos.
     *
     * @return Lista de todos los pedidos encontrados.
     */
    public static List<Pedido> getAll() {
        List<Pedido> pedidos = new ArrayList<>();
        Connection con = ConnectionDB.getConnection();
        String sql = "SELECT * FROM pedido";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
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
            throw new RuntimeException(e);
        }

        return pedidos;
    }

    /**
     * Busca un pedido en la base de datos por su ID.
     *
     * @param id El ID del pedido a buscar.
     * @return El pedido encontrado si existe; null si no se encuentra.
     */
    public static Pedido findById(int id) {
        Pedido pedido = null;
        Connection con = ConnectionDB.getConnection();
        String sql = "SELECT * FROM pedido WHERE idPedido = ?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
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
            throw new RuntimeException(e);
        }

        return pedido;
    }

    /**
     * Actualiza los datos de un pedido existente en la base de datos.
     *
     * @param pedido El pedido con los datos actualizados.
     */
    public static void update(Pedido pedido) {
        String sql = "UPDATE pedido SET idCliente = ?, fecha = ?, estado = ? WHERE idPedido = ?";
        Connection con = ConnectionDB.getConnection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, pedido.getIdCliente());
            pst.setString(2, pedido.getFecha());
            pst.setString(3, pedido.getEstado());
            pst.setInt(4, pedido.getIdPedido());

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Elimina un pedido de la base de datos por su ID.
     *
     * @param id El ID del pedido a eliminar.
     */
    public static void delete(int id) {
        String sql = "DELETE FROM pedido WHERE idPedido = ?";
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
