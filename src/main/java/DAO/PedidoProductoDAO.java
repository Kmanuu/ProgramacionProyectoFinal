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

    private Connection con = ConnectionDB.getConnection();

    // 🔹 Insertar relación pedido-producto
    public void insert(PedidoProducto pedidoProducto) {
        // TODO: Implementar lógica de inserción
    }

    // 🔹 Obtener todas las relaciones pedido-producto
    public List<PedidoProducto> getAll() {
        // TODO: Implementar lógica para obtener todas las relaciones
        return new ArrayList<>();
    }

    // 🔹 Buscar por ID de relación (si tienes PK compuesta, puede ser por pedido y producto)
    public PedidoProducto findById(int id) {
        // TODO: Implementar lógica para buscar por ID
        return null;
    }

    // 🔹 Actualizar relación pedido-producto
    public void update(PedidoProducto pedidoProducto) {
        // TODO: Implementar lógica de actualización
    }

    // 🔹 Eliminar relación por ID
    public void delete(int id) {
        // TODO: Implementar lógica de eliminación
    }
}
