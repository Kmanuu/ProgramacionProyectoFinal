package model;

/**
 * Clase de utilidad para mantener la sesión activa de un cliente logueado.
 */
public class Sesion {

    private static Cliente clienteActual;

    /**
     * Establece el cliente logueado actualmente.
     */
    public static void setClienteActual(Cliente cliente) {
        clienteActual = cliente;
    }

    /**
     * Devuelve el cliente actualmente logueado.
     */
    public static Cliente getClienteActual() {
        return clienteActual;
    }

    /**
     * Limpia la sesión (al cerrar sesión).
     */
    public static void cerrarSesion() {
        clienteActual = null;
    }
}
