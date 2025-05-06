package model;

public class Pedido {

    // Atributos
    private int idPedido;
    private int idCliente;
    private String fecha;
    private String estado;

    // Constructores
    public Pedido() {}

    public Pedido(int idPedido, int idCliente, String fecha, String estado) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    // Métodos
    @Override
    public String toString() {
        return "Pedido #" + idPedido + " (Cliente: " + idCliente + ", Fecha: " + fecha + ")";
    }
}

