package controller;

import DAO.PedidoDAO;
import DAO.PedidoProductoDAO;
import DAO.ProductoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Cliente;
import model.Pedido;
import model.PedidoProducto;
import model.Producto;
import model.Sesion;

import java.time.LocalDate;
import java.util.List;

public class HacerPedidoController {

    @FXML private ComboBox<Producto> comboProductos;
    @FXML private TextField txtCantidad;

    /**
     * Método que se llama automáticamente cuando se carga la vista.
     */
    @FXML
    public void initialize() {
        Cliente cliente = Sesion.getClienteActual(); // ✅ Obtenemos el cliente guardado en la sesión

        if (cliente == null) {
            mostrarAlerta("Error", "No se detectó sesión del cliente.");
            return;
        }

        // Cargamos los productos en el ComboBox
        List<Producto> productos = ProductoDAO.getAll();
        comboProductos.getItems().setAll(productos);

        // Validación para que txtCantidad solo acepte números
        txtCantidad.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                txtCantidad.setText(newVal.replaceAll("[^\\d]", ""));
            }
        });
    }


    /**
     * Acción al confirmar el pedido.
     */
    @FXML
    private void onConfirmar() {
        Cliente cliente = Sesion.getClienteActual();

        // Comprobamos que el cliente esté en sesión
        if (cliente == null) {
            mostrarAlerta("Error", "No se detectó sesión del cliente.");
            return;
        }

        Producto producto = comboProductos.getValue();
        String cantidadStr = txtCantidad.getText();

        // Validamos que el producto y la cantidad sean válidos
        if (producto == null || cantidadStr.isEmpty()) {
            mostrarAlerta("Error", "Selecciona un producto y escribe una cantidad.");
            return;
        }

        try {
            int cantidad = Integer.parseInt(cantidadStr);

            // Validamos que la cantidad sea positiva y no exceda el stock
            if (cantidad <= 0 || cantidad > producto.getStock()) {
                mostrarAlerta("Error", "Cantidad inválida o sin stock.");
                return;
            }

            // Creamos el pedido
            Pedido pedido = new Pedido();
            pedido.setIdCliente(cliente.getIdUsuario());
            pedido.setFecha(LocalDate.now().toString());
            int idPedido = PedidoDAO.insert(pedido);

            // Asociamos el producto al pedido
            PedidoProducto pedidoProducto = new PedidoProducto(idPedido, producto.getIdProducto(), cantidad);
            PedidoProductoDAO.insert(pedidoProducto);

            // Actualizamos el stock
            producto.setStock(producto.getStock() - cantidad);
            ProductoDAO.update(producto);

            mostrarAlerta("Pedido confirmado", "Se ha guardado tu pedido.");

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Introduce un número válido.");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error interno", "No se pudo procesar el pedido.");
        }
    }

   // Controla la opcion de retroceder en el menu
    @FXML
    private void onVolver() {
        Stage stage = (Stage) comboProductos.getScene().getWindow();
        stage.close();
    }

    // Método para mostrar alertas
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Esta clase me ha dado millones de cabezas, sin dudas la que mas me ha costado
}
