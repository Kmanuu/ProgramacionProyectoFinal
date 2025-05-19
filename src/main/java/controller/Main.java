package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Clase principal para lanzar la aplicación JavaFX.
 */
public class Main extends Application {

    // Método que se ejecuta al iniciar la aplicación
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargamos la ventana de login desde el archivo FXML
        Parent root = FXMLLoader.load(getClass().getResource("/controller/login.fxml"));

        // Creamos la escena y la asignamos al escenario principal
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login RetroDrip");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método main tradicional, que lanza JavaFX
    public static void main(String[] args) {
        launch(args);
    }
}
