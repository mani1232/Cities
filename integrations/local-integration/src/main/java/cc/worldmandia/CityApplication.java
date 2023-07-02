package cc.worldmandia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CityApplication extends Application implements Integration {


    @Override
    public void start(Stage stage) {
        try {
            Scene scene = new Scene(new FXMLLoader().load(this.getClass().getResourceAsStream("/HelloWindow.fxml")));
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                event.consume();
                System.exit(0);
            });
            stage.setMaxHeight(600);
            stage.setMaxWidth(600);
            stage.setMinHeight(100);
            stage.setMinWidth(400);
            stage.setTitle("App by Technical Cats");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void start(String[] args) {
        launch(args);
    }
}
