package model;

public class Cliente {
        // Atributos
        private int idCliente;
        private String nombre;
        private String email;
        private String direccion;
        private String fechaNacimiento;
        private String nivelFidelidad;

        // Constructores
        public Cliente() {}

        public Cliente(int idCliente, String nombre, String email, String direccion, String fechaNacimiento, String nivelFidelidad) {
            this.idCliente = idCliente;
            this.nombre = nombre;
            this.email = email;
            this.direccion = direccion;
            this.fechaNacimiento = fechaNacimiento;
            this.nivelFidelidad = nivelFidelidad;
        }

        // Getters y Setters
        public int getIdCliente() { return idCliente; }
        public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getDireccion() { return direccion; }
        public void setDireccion(String direccion) { this.direccion = direccion; }

        public String getFechaNacimiento() { return fechaNacimiento; }
        public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

        public String getNivelFidelidad() { return nivelFidelidad; }
        public void setNivelFidelidad(String nivelFidelidad) { this.nivelFidelidad = nivelFidelidad; }

        // Métodos
        @Override
        public String toString() {
            return nombre + " (" + email + ")";
        }
    }
