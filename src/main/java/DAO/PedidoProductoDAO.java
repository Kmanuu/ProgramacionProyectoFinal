package DAO;

import baseDatos.ConnectionDB;
import model.PedidoProducto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoProductoDAO {

    /**
     * Inserta una relación entre pedido y producto si no existe ya.
     */
    public static PedidoProducto insert(PedidoProducto pp) {
        // Comprobamos que no sea nulo y que no exista ya en la base de datos
        if (pp != null && findByIds(pp.getIdPedido(), pp.getIdProducto()) == null) {
            Connection con = ConnectionDB.getConnection();
            String sql = "INSERT INTO pedido_producto (idPedido, idProducto, cantidad) VALUES (?, ?, ?)";

            try {
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, pp.getIdPedido());
                pst.setInt(2, pp.getIdProducto());
                pst.setInt(3, pp.getCantidad());

                pst.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return pp;
        }
        return null;
    }

    /**
     * Devuelve todas las relaciones pedido-producto de la tabla.
     */
    public static List<PedidoProducto> getAll() {
        List<PedidoProducto> lista = new ArrayList<>();
        Connection con = ConnectionDB.getConnection();
        String sql = "SELECT * FROM pedido_producto";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Recorremos cada fila y la convertimos en un objeto PedidoProducto
            while (rs.next()) {
                PedidoProducto pp = new PedidoProducto();
                pp.setIdPedido(rs.getInt("idPedido"));
                pp.setIdProducto(rs.getInt("idProducto"));
                pp.setCantidad(rs.getInt("cantidad"));
                lista.add(pp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    /**
     * Busca una relación concreta por IDs (clave compuesta).
     */
    public static PedidoProducto findByIds(int idPedido, int idProducto) {
        PedidoProducto pp = null;
        Connection con = ConnectionDB.getConnection();
        String sql = "SELECT * FROM pedido_producto WHERE idPedido = ? AND idProducto = ?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idPedido);
            stmt.setInt(2, idProducto);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pp = new PedidoProducto();
                pp.setIdPedido(rs.getInt("idPedido"));
                pp.setIdProducto(rs.getInt("idProducto"));
                pp.setCantidad(rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pp;
    }

    /**
     * Actualiza la cantidad de un producto en un pedido.
     */
    public static void update(PedidoProducto pp) {
        String sql = "UPDATE pedido_producto SET cantidad = ? WHERE idPedido = ? AND idProducto = ?";
        Connection con = ConnectionDB.getConnection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, pp.getCantidad());
            pst.setInt(2, pp.getIdPedido());
            pst.setInt(3, pp.getIdProducto());

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Elimina una relación pedido-producto por clave compuesta.
     */
    public static void delete(int idPedido, int idProducto) {
        String sql = "DELETE FROM pedido_producto WHERE idPedido = ? AND idProducto = ?";
        Connection con = ConnectionDB.getConnection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idPedido);
            pst.setInt(2, idProducto);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
