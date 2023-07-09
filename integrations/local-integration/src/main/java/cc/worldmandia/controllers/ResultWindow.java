package cc.worldmandia.controllers;

import cc.worldmandia.CityApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ResultWindow {

    public Label resultText;
    public Button restartApp;
    public Button quitApp;

    public void quit(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        CityApplication.user.availableCities.clear();
        CityApplication.user.availableCities.addAll(CityApplication.cities);
        CityApplication.dataBase.getDataBaseAPI().replaceObject("username", CityApplication.user.username, CityApplication.user);
        stage.close();
        System.exit(0);
    }

    public void restart(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        try {
            CityApplication.user.availableCities.clear();
            CityApplication.user.availableCities.addAll(CityApplication.cities);
            CityApplication.dataBase.getDataBaseAPI().replaceObject("username", CityApplication.user.username, CityApplication.user);
            stage.setScene(new Scene(new FXMLLoader().load(this.getClass().getResourceAsStream("/GameWindow.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



