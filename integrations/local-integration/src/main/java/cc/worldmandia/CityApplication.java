package cc.worldmandia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CityApplication extends Application implements Integration {


    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader();

        // Create the Pane and all Details
        VBox root;
        try {
            root = loader.load(this.getClass().getResourceAsStream("/Example.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Create the Scene
        Scene scene = new Scene(root);
        // Set the Scene to the Stage
        primaryStage.setScene(scene);
        // Set the Title to the Stage
        primaryStage.setTitle("A FXML Example with a Controller");
        // Display the Stage
        primaryStage.show();
    }


    @Override
    public void start(String[] args) {
        launch(args);
    }
}
