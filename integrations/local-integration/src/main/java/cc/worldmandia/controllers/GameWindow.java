package cc.worldmandia.controllers;

import cc.worldmandia.CityApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class GameWindow {
    public TextField city;
    public Label enterCityName;
    public Button yourTurn;
    public Label computer;

    public void nextTurn(ActionEvent actionEvent) {
        CityApplication.user.passedCities.add("dfghfgh");
        CityApplication.dataBase.getDataBaseAPI().replaceObject("username", CityApplication.user.username, CityApplication.user);
    }
    public void stopGame(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        try
        {
            stage.setScene(new Scene(new FXMLLoader().load(this.getClass().getResourceAsStream("/ResultWindow.fxml"))));
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
