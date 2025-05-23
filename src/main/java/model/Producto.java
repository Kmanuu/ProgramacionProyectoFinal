package model;

// Clase que representa un producto disponible en la tienda
public class Producto {

    // Atributos del producto
    private int idProducto;
    private String nombre;
    private String marca;
    private String categoria;
    private int anio;
    private double precio;
    private int stock;
    private String imagen;

    // Constructor vacío (necesario para DAOs o JavaFX)
    public Producto() {}

    // Constructor completo con todos los datos
    public Producto(int idProducto, String nombre, String marca, String categoria, int anio, double precio, int stock, String imagen) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
        this.anio = anio;
        this.precio = precio;
        this.stock = stock;
        this.imagen = imagen;
    }

    // Constructor rápido para cuando solo se necesita nombre, precio y stock
    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y setters para todos los campos
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    // toString para mostrar el producto de forma resumida
    @Override
    public String toString() {
        return nombre + " (" + marca + ", " + anio + ")";
    }
}
