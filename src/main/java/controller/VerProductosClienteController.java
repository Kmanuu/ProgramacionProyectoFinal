package controller;

import DAO.ProductoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Producto;

import java.util.List;

// Controlador que permite al cliente ver los productos disponibles
public class VerProductosClienteController {

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private TableColumn<Producto, Integer> colStock;

    @FXML
    private Button btnVolver;

    // Este método se ejecuta automáticamente al cargar la ventana
    @FXML
    public void initialize() {
        // Asociamos cada columna con el atributo correspondiente de Producto
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        // Cargamos los productos en la tabla
        cargarProductos();
    }

    // Método que obtiene los productos de la base de datos y los muestra en la tabla
    private void cargarProductos() {
        List<Producto> productos = ProductoDAO.getAll();
        ObservableList<Producto> lista = FXCollections.observableArrayList(productos);
        tablaProductos.setItems(lista);
    }

    // Acción al pulsar el botón "Volver" al menú del cliente
    @FXML
    private void onVolver() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller/menuCliente.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Menú Cliente");
            stage.setScene(new Scene(root));
            stage.show();

            // Cerramos la ventana actual
            Stage actual = (Stage) btnVolver.getScene().getWindow();
            actual.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
