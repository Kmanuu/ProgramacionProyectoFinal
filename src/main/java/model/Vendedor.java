package model;

// Clase que representa a un vendedor y hereda de Usuario
public class Vendedor extends Usuario {

    // Atributos específicos del vendedor
    private String tienda;
    private String telefonoContacto;
    private String direccionTienda;
    private String descripcionTienda;

    // Constructor vacío (requerido por JavaFX o DAO)
    public Vendedor() {
        super();
    }

    // Constructor completo con todos los campos
    public Vendedor(int idUsuario, String nombre, String email, String contrasena,
                    String tienda, String telefonoContacto, String direccionTienda, String descripcionTienda) {
        super(idUsuario, nombre, email, contrasena); // Llama al constructor de Usuario
        this.tienda = tienda;
        this.telefonoContacto = telefonoContacto;
        this.direccionTienda = direccionTienda;
        this.descripcionTienda = descripcionTienda;
    }

    // Getters y setters para acceder a los datos del vendedor
    public String getTienda() { return tienda; }
    public void setTienda(String tienda) { this.tienda = tienda; }

    public String getTelefonoContacto() { return telefonoContacto; }
    public void setTelefonoContacto(String telefonoContacto) { this.telefonoContacto = telefonoContacto; }

    public String getDireccionTienda() { return direccionTienda; }
    public void setDireccionTienda(String direccionTienda) { this.direccionTienda = direccionTienda; }

    public String getDescripcionTienda() { return descripcionTienda; }
    public void setDescripcionTienda(String descripcionTienda) { this.descripcionTienda = descripcionTienda; }

    // Implementación del método abstracto de Usuario
    @Override
    public void mostrarInfo() {
        System.out.println("Vendedor: " + nombre + " (" + tienda + ")");
        System.out.println("Contacto: " + email + " - Tel: " + telefonoContacto);
        System.out.println("Ubicación: " + direccionTienda);
        System.out.println("Descripción: " + descripcionTienda);
    }

    // Método útil para mostrar info resumida del objeto
    @Override
    public String toString() {
        return "Vendedor{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", tienda='" + tienda + '\'' +
                ", telefonoContacto='" + telefonoContacto + '\'' +
                ", direccionTienda='" + direccionTienda + '\'' +
                ", descripcionTienda='" + descripcionTienda + '\'' +
                '}';
    }
}
