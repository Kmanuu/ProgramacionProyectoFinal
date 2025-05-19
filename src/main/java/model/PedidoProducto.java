package model;

/*
 * Esta clase representa la tabla intermedia entre Pedido y Producto (relación muchos a muchos).
 * Gracias a esta clase, podemos guardar cuántas unidades de cada producto se han pedido.
 */
public class PedidoProducto {

    // Atributos: clave compuesta y cantidad
    private int idPedido;
    private int idProducto;
    private int cantidad;

    // Constructor vacío (requerido para DAOs)
    public PedidoProducto() {}

    // Constructor con todos los atributos
    public PedidoProducto(int idPedido, int idProducto, int cantidad) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    // Getters y setters
    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    // toString útil para depuración o mostrar por consola
    @Override
    public String toString() {
        return "Pedido #" + idPedido + " - Producto #" + idProducto + " (Cantidad: " + cantidad + ")";
    }
}
