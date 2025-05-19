package controller;

import DAO.ProductoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Producto;

import java.util.function.Consumer;

// Controlador del formulario de edición de un producto
public class EditarProductoFormController {

    @FXML
    private TextField nombreField;

    @FXML
    private TextField precioField;

    @FXML
    private TextField stockField;

    private Producto producto; // Producto a editar

    private Consumer<Void> onProductoActualizado; // Callback para avisar al padre que se actualizó

    // Este método se llama desde otro controlador para pasarle el producto a editar
    public void setProducto(Producto producto) {
        this.producto = producto;

        // Rellenamos los campos con los datos actuales
        nombreField.setText(producto.getNombre());
        precioField.setText(String.valueOf(producto.getPrecio()));
        stockField.setText(String.valueOf(producto.getStock()));
    }

    // Este método permite pasar un callback para que se ejecute después de guardar
    public void setOnProductoActualizado(Consumer<Void> callback) {
        this.onProductoActualizado = callback;
    }

    // Al pulsar el botón Guardar
    @FXML
    private void onGuardar() {
        try {
            // Actualizamos el objeto con los datos del formulario
            producto.setNombre(nombreField.getText());
            producto.setPrecio(Double.parseDouble(precioField.getText()));
            producto.setStock(Integer.parseInt(stockField.getText()));

            // Guardamos en la base de datos
            ProductoDAO.update(producto);

            // Ejecutamos el callback si existe (para que recargue la tabla)
            if (onProductoActualizado != null) {
                onProductoActualizado.accept(null);
            }

            // Cerramos esta ventana
            Stage stage = (Stage) nombreField.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
