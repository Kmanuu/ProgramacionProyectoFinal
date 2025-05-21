package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Cliente;

public class MenuClienteController {

    @FXML private Button btnVer;
    @FXML private Button btnPedir;

    private Cliente clienteLogueado;

    // Recibe el cliente desde el login
    public void setClienteLogueado(Cliente cliente) {
        this.clienteLogueado = cliente;
    }

    // Metodo que abre la ventana de ver productos
    @FXML
    private void onVerProductos() {
        abrirVentanaSimple("/controller/verProductosCliente.fxml", "Ver productos");
    }

    // Metodo que abre la ventana de hacer pedido
    @FXML
    private void onHacerPedido() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller/hacerPedido.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de la nueva ventana
            Stage stage = new Stage();
            stage.setTitle("Hacer Pedido");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Metodo para cerrar sesion en nuestra cuenta
    @FXML
    private void onCerrarSesion() {
        abrirVentanaSimple("/controller/login.fxml", "Login");
        Stage actual = (Stage) btnVer.getScene().getWindow();
        actual.close();
    }

    // Metodo para abrir la ventana
    private void abrirVentanaSimple(String rutaFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Esta clase me ha dado mil dolores de cabeza
}
