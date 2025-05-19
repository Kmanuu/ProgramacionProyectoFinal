package controller;

import DAO.ProductoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Producto;

import java.util.List;

/**
 * Controlador para editar o eliminar productos desde el panel del vendedor.
 */
public class EditarEliminarProductoController {

    @FXML private TableView<Producto> tabla;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Double> colPrecio;
    @FXML private TableColumn<Producto, Integer> colStock;

    @FXML
    public void initialize() {
        // Configurar columnas de la tabla
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        colPrecio.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getPrecio()));
        colStock.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getStock()));

        cargar();
    }

    /**
     * Carga la lista de productos en la tabla.
     */
    private void cargar() {
        List<Producto> productos = ProductoDAO.getAll();
        ObservableList<Producto> lista = FXCollections.observableArrayList(productos);
        tabla.setItems(lista);
    }

    /**
     * Elimina el producto seleccionado de la base de datos.
     */
    @FXML
    private void onEliminar() {
        Producto seleccionado = tabla.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Selecciona un producto", "Debes seleccionar un producto para eliminar.");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Seguro que deseas eliminar el producto \"" + seleccionado.getNombre() + "\"?");

        confirmacion.showAndWait().ifPresent(respuesta -> {
            if (respuesta == ButtonType.OK) {
                ProductoDAO.delete(seleccionado.getIdProducto());
                cargar(); // Refrescar la tabla
                mostrarAlerta("Eliminado", "Producto eliminado correctamente.");
            }
        });
    }

    /**
     * Abre el formulario de edición para el producto seleccionado.
     */
    @FXML
    private void onEditar() {
        Producto seleccionado = tabla.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller/editarProductoForm.fxml"));
                Parent root = loader.load();

                EditarProductoFormController controller = loader.getController();
                controller.setProducto(seleccionado);
                controller.setOnProductoActualizado(aVoid -> cargar()); // Recargar tabla tras edición

                Stage stage = new Stage();
                stage.setTitle("Editar Producto");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                mostrarAlerta("Error", "No se pudo abrir la ventana de edición.");
            }
        } else {
            mostrarAlerta("Selecciona un producto", "Debes seleccionar un producto para editar.");
        }
    }

    /**
     * Vuelve al menú del vendedor.
     */
    @FXML
    private void onVolver() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller/menuVendedor.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Menú Vendedor");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) tabla.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo volver al menú.");
        }
    }

    /**
     * Muestra una alerta con título y mensaje.
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
