package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

// Controlador del menú principal del vendedor
public class MenuVendedorController {

    @FXML private Button btnVer;
    @FXML private Button btnAnadir;
    @FXML private Button btnEditar;
    @FXML private Button btnSalir;

    // Abre la vista para ver productos
    @FXML
    private void onVerProductos() {
        abrirVentana("/controller/verProductosVendedor.fxml", "Productos");
    }

    // Abre la vista para añadir un nuevo producto
    @FXML
    private void onAnadirProducto() {
        abrirVentana("/controller/anadirProducto.fxml", "Añadir Producto");
    }

    // Abre la vista para editar o eliminar productos
    @FXML
    private void onEditarProducto() {
        abrirVentana("/controller/editarEliminarProducto.fxml", "Editar / Eliminar Producto");
    }

    // Cierra sesión y vuelve al login
    @FXML
    private void onCerrarSesion() {
        abrirVentana("/controller/login.fxml", "Login");
    }

    // Método para abrir una ventana y cerrar la actual
    private void abrirVentana(String rutaFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();

            // Cerramos la ventana actual (menú vendedor)
            ((Stage) btnVer.getScene().getWindow()).close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
