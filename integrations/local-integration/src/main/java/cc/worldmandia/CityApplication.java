package cc.worldmandia;

import cc.worldmandia.DataBase.DataBase;
import cc.worldmandia.DataBase.Objects.CityUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CityApplication extends Application implements Integration {

    public static ArrayList<String> cities = new ArrayList<>(List.of(
            "Kyiv",
            "Lviv",
            "Odesa",
            "Vinitsa",
            "London",
            "Paris",
            "Berlin",
            "Madrid"
    ));

    public static DataBase<CityUser> dataBase;
    public static CityUser user;

    @Override
    public void start(Stage stage) {
        try {
            Scene scene = new Scene(new FXMLLoader().load(this.getClass().getResourceAsStream("/HelloWindow.fxml")));
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> {
                try {
                    if (Utils.executor.awaitTermination(3, TimeUnit.SECONDS)) {
                        event.consume();
                    } else {
                        System.exit(0);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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
    public void start(String[] args, DataBase dataBase) {
        CityApplication.dataBase = dataBase;
        launch(args);
    }
}
