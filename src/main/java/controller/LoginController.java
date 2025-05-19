package controller;

import DAO.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Cliente;
import model.Usuario;
import model.Vendedor;
import model.Sesion;

public class LoginController {

    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;

    /**
     * Acción al pulsar el botón de iniciar sesión.
     */
    @FXML
    private void onLogin() {
        String email = txtEmail.getText();
        String contrasena = txtPassword.getText();

        if (email.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, rellena todos los campos.");
            return;
        }

        Usuario u = UsuarioDAO.login(email, contrasena);

        if (u == null) {
            mostrarAlerta("Login incorrecto", "Email o contraseña incorrectos.");
        } else {
            mostrarAlerta("Sesión iniciada", "Bienvenido, " + u.getNombre());

            if (u instanceof Cliente) {
                try {
                    // Guardamos el cliente en la sesión global
                    Sesion.setClienteActual((Cliente) u);

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller/menuCliente.fxml"));
                    Parent root = loader.load();

                    Stage stage = new Stage();
                    stage.setTitle("Menú Cliente");
                    stage.setScene(new Scene(root));
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                    mostrarAlerta("Error", "No se pudo abrir el menú del cliente.");
                }

            } else if (u instanceof Vendedor) {
                abrirVentana("/controller/menuVendedor.fxml", "Menú Vendedor");
            }

            // Cerramos el login
            Stage stage = (Stage) txtEmail.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Abre la ventana de registro.
     */
    @FXML
    private void onCrearCuenta() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller/registro.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Registro de Usuario");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo abrir la ventana de registro.");
        }
    }

    /**
     * Abre cualquier ventana simple.
     */
    private void abrirVentana(String rutaFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo abrir la ventana: " + titulo);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
