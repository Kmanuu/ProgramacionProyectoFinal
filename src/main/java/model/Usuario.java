package model;

// Clase abstracta base para Cliente y Vendedor
public abstract class Usuario {

    // Atributos comunes a cualquier tipo de usuario
    protected int idUsuario;
    protected String nombre;
    protected String email;
    protected String contrasena;

    // Constructor vacío (necesario para DAO o JavaFX)
    public Usuario() {}

    // Constructor con todos los campos
    public Usuario(int idUsuario, String nombre, String email, String contrasena) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }

    // Getters y setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    // Método abstracto que deben implementar las subclases
    public abstract void mostrarInfo();
}
