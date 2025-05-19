package model;

// Clase que representa un pedido hecho por un cliente
public class Pedido {

    // Atributos principales del pedido
    private int idPedido;
    private int idCliente;
    private String fecha;
    private String estado;

    // Constructor vacío (necesario para DAOs y JavaFX)
    public Pedido() {}

    // Constructor completo
    public Pedido(int idPedido, int idCliente, String fecha, String estado) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.estado = estado;
    }

    // Getters y setters para acceder y modificar los datos
    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    // Método toString útil para mostrar info por consola o pruebas
    @Override
    public String toString() {
        return "Pedido #" + idPedido + " (Cliente: " + idCliente + ", Fecha: " + fecha + ")";
    }
}
