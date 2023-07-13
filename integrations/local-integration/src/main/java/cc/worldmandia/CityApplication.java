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

public class CityApplication extends Application implements Integration {

    public static ArrayList<String> cities = new ArrayList<>(List.of(
            "Kyiv",
            "Lviv",
            "Odesa",
            "Vinitsa",
            "London",
            "Paris",
            "Berlin",
            "Madrid",
            "Amsterdam",
            "Barcelona",
            "Copenhagen",
            "Dublin",
            "Edinburgh",
            "Florence",
            "Geneva",
            "Helsinki",
            "Istanbul",
            "Jerusalem",
            "Lisbon",
            "Naples",
            "Oslo",
            "Prague",
            "Rome",
            "Stockholm",
            "Tokyo",
            "Utrecht",
            "Valencia",
            "Warsaw",
            "Kharkiv",
            "Yalta",
            "Zurich",
            "Atlanta",
            "Bucharest",
            "Chicago",
            "Dallas",
            "Edmonton",
            "Frankfurt",
            "Houston",
            "Indianapolis",
            "Jakarta",
            "Kansas City",
            "Louisville",
            "Miami",
            "Nashville",
            "Omaha",
            "Portland",
            "Raleigh",
            "Seattle",
            "Tampa",
            "Vancouver",
            "Wellington",
            "Xiamen",
            "Yokohama",
            "Zagreb"
    ));

    public static DataBase<CityUser> dataBase;
    public static CityUser user;

    @Override
    public void start(Stage stage) {
        try {
            Scene scene = new Scene(new FXMLLoader().load(this.getClass().getResourceAsStream("/HelloWindow.fxml")));
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> dataBase.getDataBaseAPI().saveAll(true));
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
