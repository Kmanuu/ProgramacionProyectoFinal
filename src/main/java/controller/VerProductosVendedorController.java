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

/**
 * Controlador para mostrar los productos al vendedor.
 */
public class VerProductosVendedorController {

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

    // Se ejecuta al abrir la vista
    @FXML
    public void initialize() {
        // Asignamos las columnas a los atributos del modelo
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        cargarProductos(); // Llenamos la tabla
    }

    // Cargar productos desde la base de datos
    private void cargarProductos() {
        List<Producto> productos = ProductoDAO.getAll();
        ObservableList<Producto> lista = FXCollections.observableArrayList(productos);
        tablaProductos.setItems(lista);
    }

    // Volver al menú del vendedor
    @FXML
    private void onVolver() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller/menuVendedor.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Menú Vendedor");
            stage.setScene(new Scene(root));
            stage.show();

            // Cerrar esta ventana
            Stage actual = (Stage) btnVolver.getScene().getWindow();
            actual.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
