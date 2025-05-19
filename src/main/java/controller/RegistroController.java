package controller;

import DAO.ClienteDAO;
import DAO.UsuarioDAO;
import DAO.VendedorDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Cliente;
import model.Usuario;
import model.Vendedor;

// Controlador de la pantalla de registro de usuario
public class RegistroController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtContrasena;
    @FXML private ComboBox<String> comboTipo;
    @FXML private Button btnRegistrar;

    // Se ejecuta al iniciar la ventana
    @FXML
    public void initialize() {
        // Rellenamos el ComboBox con los tipos de usuario disponibles
        comboTipo.getItems().addAll("Cliente", "Vendedor");
    }

    // Acción al pulsar el botón Registrar
    @FXML
    private void onRegistrar() {
        String nombre = txtNombre.getText();
        String email = txtEmail.getText();
        String contrasena = txtContrasena.getText();
        String tipo = comboTipo.getValue();

        // Comprobamos que ningún campo esté vacío
        if (nombre.isEmpty() || email.isEmpty() || contrasena.isEmpty() || tipo == null) {
            mostrarAlerta("Campos vacíos", "Por favor, rellena todos los campos.");
            return;
        }

        // Validación básica de email con expresión regular
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            mostrarAlerta("Email inválido", "El formato del email no es válido.");
            return;
        }


        // Comprobamos si ya existe un usuario con ese email
        if (UsuarioDAO.login(email, contrasena) != null) {
            mostrarAlerta("Error", "Ya existe un usuario con ese email.");
            return;
        }

        // Creamos el objeto usuario según el tipo seleccionado
        Usuario usuario;
        if (tipo.equals("Cliente")) {
            usuario = new Cliente(0, nombre, email, contrasena,
                    "Dirección genérica", "2000-01-01", "BRONCE");
        } else {
            usuario = new Vendedor(0, nombre, email, contrasena,
                    "Mi Tienda", "600000000", "Calle Ejemplo", "Vendedor estándar");
        }

        // Insertamos el usuario en la tabla principal
        int id = UsuarioDAO.insertUsuario(usuario);
        if (id == -1) {
            mostrarAlerta("Error", "No se pudo insertar en la tabla usuario.");
            return;
        }

        usuario.setIdUsuario(id); // actualizamos el ID

        // Insertamos en la tabla correspondiente
        if (usuario instanceof Cliente) {
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.insertCliente((Cliente) usuario);
        } else {
            VendedorDAO.insertVendedor((Vendedor) usuario);
        }

        mostrarAlerta("Registro correcto", "Usuario registrado correctamente.");

        // Cerramos la ventana de registro
        Stage stage = (Stage) btnRegistrar.getScene().getWindow();
        stage.close();
    }

    // Método para mostrar alertas informativas
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
