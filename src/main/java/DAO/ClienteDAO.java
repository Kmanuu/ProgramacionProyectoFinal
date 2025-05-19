package DAO;

import baseDatos.ConnectionDB;
import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Se obtiene la conexión a la base de datos
    private Connection con = ConnectionDB.getConnection();

    /**
     * Inserta un cliente nuevo si no existe ya en la base de datos
     */
    public Cliente insertCliente(Cliente c) {
        // Comprobamos que el cliente no sea nulo y que no exista en la BD
        if (c != null && findById(c.getIdUsuario()) == null) {
            Connection con = ConnectionDB.getConnection();
            String sql = "INSERT INTO cliente (idUsuario, direccion, fechaNacimiento, nivelFidelidad) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pst = con.prepareStatement(sql)) {
                // Insertamos los datos del cliente en la base de datos
                pst.setInt(1, c.getIdUsuario());
                pst.setString(2, c.getDireccion());
                pst.setString(3, c.getFechaNacimiento());
                pst.setString(4, c.getNivelFidelidad());

                pst.executeUpdate(); // Ejecutamos el insert
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return c;
        }
        return null; // Si ya existe o es nulo, no se inserta
    }

    /**
     * Devuelve todos los clientes guardados en la base de datos
     */
    public static List<Cliente> getAll() {
        List<Cliente> clientes = new ArrayList<>();
        Connection con = ConnectionDB.getConnection();
        String sql = "SELECT * FROM cliente";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Recorremos los resultados y creamos objetos Cliente
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdUsuario(rs.getInt("idUsuario"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setFechaNacimiento(rs.getString("fechaNacimiento"));
                cliente.setNivelFidelidad(rs.getString("nivelFidelidad"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clientes;
    }

    /**
     * Busca un cliente por su idUsuario
     */
    public static Cliente findById(int id) {
        Cliente cliente = null;
        Connection con = ConnectionDB.getConnection();
        String sql = "SELECT * FROM cliente WHERE idUsuario = ?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            // Si lo encuentra, lo convierte en objeto Cliente
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setIdUsuario(rs.getInt("idUsuario"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setFechaNacimiento(rs.getString("fechaNacimiento"));
                cliente.setNivelFidelidad(rs.getString("nivelFidelidad"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cliente;
    }

    /**
     * Actualiza los datos de un cliente existente
     */
    public static void update(Cliente cliente) {
        String sql = "UPDATE cliente SET direccion = ?, fechaNacimiento = ?, nivelFidelidad = ? WHERE idUsuario = ?";
        Connection con = ConnectionDB.getConnection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cliente.getDireccion());
            pst.setString(2, cliente.getFechaNacimiento());
            pst.setString(3, cliente.getNivelFidelidad());
            pst.setInt(4, cliente.getIdUsuario());

            pst.executeUpdate(); // Ejecutamos el update
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Elimina un cliente por su idUsuario
     */
    public static void delete(int id) {
        String sql = "DELETE FROM cliente WHERE idUsuario = ?";
        Connection con = ConnectionDB.getConnection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate(); // Ejecutamos el delete
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
