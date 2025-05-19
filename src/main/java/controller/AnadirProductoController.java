package controller;

import DAO.ProductoDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Producto;

// Controlador para la ventana de añadir producto
public class AnadirProductoController {

    // Campos de texto que se enlazan desde el FXML
    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtStock;

    // Acción al pulsar "Guardar"
    @FXML
    private void onGuardar() {
        String nombre = txtNombre.getText();
        String precioStr = txtPrecio.getText();
        String stockStr = txtStock.getText();

        // Validación básica
        if (nombre.isEmpty() || precioStr.isEmpty() || stockStr.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Rellena todos los campos.");
            return;
        }

        try {
            // Convertimos los campos a sus tipos correctos
            double precio = Double.parseDouble(precioStr);
            int stock = Integer.parseInt(stockStr);

            // Creamos e insertamos el producto
            Producto p = new Producto(nombre, precio, stock);
            ProductoDAO.insert(p);

            mostrarAlerta("Producto añadido", "Se añadió correctamente.");

            // Cerramos esta ventana
            Stage actual = (Stage) txtNombre.getScene().getWindow();
            actual.close();

            // Abrimos el menú del vendedor de nuevo
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller/menuVendedor.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Menú Vendedor");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (NumberFormatException e) {
            mostrarAlerta("Formato incorrecto", "Precio debe ser decimal y stock entero.");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo añadir el producto.");
        }
    }

    // Acción al pulsar "Volver" sin guardar
    @FXML
    private void onVolver() {
        try {
            Stage actual = (Stage) txtNombre.getScene().getWindow();
            actual.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller/menuVendedor.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Menú Vendedor");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo volver al menú.");
        }
    }

    // Método para mostrar alertas informativas
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(mensaje);
        a.showAndWait();
    }
}
