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

    private Connection con = ConnectionDB.getConnection();


    /**
     * Inserta un nuevo cliente en la base de datos si no existe previamente.
     *
     * @param c El cliente a insertar.
     * @return El cliente insertado si se ha realizado correctamente; null si ya existe o es nulo.
     */


    public Cliente insertCliente(Cliente c) {
        if (c != null && findById(c.getIdCliente()) == null) {
            Connection con = ConnectionDB.getConnection();
            String sql = "INSERT INTO cliente (nombre, email, direccion, fechaNacimiento, nivelFidelidad) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, c.getNombre());
                pst.setString(2, c.getEmail());
                pst.setString(3, c.getDireccion());
                pst.setString(4, c.getFechaNacimiento());
                pst.setString(5, c.getNivelFidelidad());

                pst.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return c;
        }
        return null;
    }



    /**
     * Obtiene todos los clientes de la base de datos.
     *
     * @return Lista de todos los clientes encontrados.
     */
    public static List<Cliente> getAll() {
        List<Cliente> clientes = new ArrayList<>();
        Connection con = ConnectionDB.getConnection();
        String sql = "SELECT * FROM cliente";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setEmail(rs.getString("email"));
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
     * Busca un cliente en la base de datos por su ID.
     *
     * @param id El ID del cliente a buscar.
     * @return El cliente encontrado si existe; null si no se encuentra.
     */
    public static Cliente findById(int id) {
        Cliente cliente = null;
        Connection con = ConnectionDB.getConnection();
        String sql = "SELECT * FROM cliente WHERE idCliente = ?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setEmail(rs.getString("email"));
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
     * Actualiza los datos de un cliente existente en la base de datos.
     *
     * @param cliente El cliente con los datos actualizados.
     */
    public static void update(Cliente cliente) {
        String sql = "UPDATE cliente SET nombre = ?, email = ?, direccion = ?, fechaNacimiento = ?, nivelFidelidad = ? WHERE idCliente = ?";
        Connection con = ConnectionDB.getConnection();

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getEmail());
            pst.setString(3, cliente.getDireccion());
            pst.setString(4, cliente.getFechaNacimiento());
            pst.setString(5, cliente.getNivelFidelidad());
            pst.setInt(6, cliente.getIdCliente());

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Elimina un cliente de la base de datos por su ID.
     *
     * @param id El ID del cliente a eliminar.
     */
    public static void delete(int id) {
        String sql = "DELETE FROM cliente WHERE idCliente = ?";
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
