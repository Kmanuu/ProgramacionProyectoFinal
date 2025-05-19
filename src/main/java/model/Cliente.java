package model;

// La clase Cliente hereda de Usuario
public class Cliente extends Usuario {

    // Atributos específicos de Cliente
    private String direccion;
    private String fechaNacimiento;
    private String nivelFidelidad;

    // Constructor vacío (necesario para JavaFX o DAOs)
    public Cliente() {
        super();
    }

    // Constructor con todos los atributos
    public Cliente(int idUsuario, String nombre, String email, String contrasena,
                   String direccion, String fechaNacimiento, String nivelFidelidad) {
        super(idUsuario, nombre, email, contrasena); // Llama al constructor de Usuario
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.nivelFidelidad = nivelFidelidad;
    }

    // Getters y setters para acceder a los datos
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getNivelFidelidad() { return nivelFidelidad; }
    public void setNivelFidelidad(String nivelFidelidad) { this.nivelFidelidad = nivelFidelidad; }

    // Implementación del método mostrarInfo (heredado de Usuario)
    @Override
    public void mostrarInfo() {
        System.out.println("Cliente: " + nombre + " - Email: " + email);
    }

    // Método toString para ver el contenido completo del objeto en pruebas
    @Override
    public String toString() {
        return "Cliente{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", nivelFidelidad='" + nivelFidelidad + '\'' +
                '}';
    }
}
