package model;

public class PedidoProducto {

    /* Creo que es importante que quede claro para que sirve esta entidad
    La entidad PedidoProducto representa la relación N:M entre Pedido y Producto y permite indicar cuántas
     unidades de cada producto se incluyen en cada pedido.
    Sin ella, no podríamos registrar correctamente qué se compra ni en qué cantidades.”
    */


    // Atributos
    private int idPedido;
    private int idProducto;
    private int cantidad;

    // Constructores
    public PedidoProducto() {}

    public PedidoProducto(int idPedido, int idProducto, int cantidad) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    // Métodos
    @Override
    public String toString() {
        return "Pedido #" + idPedido + " - Producto #" + idProducto + " (Cantidad: " + cantidad + ")";
    }
}
