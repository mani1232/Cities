package cc.worldmandia.controllers;

import cc.worldmandia.CityApplication;
import cc.worldmandia.Utils;
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
        String userInput = city.getText().trim().toLowerCase();

        if (userInput.equalsIgnoreCase("exit")) {
            try {
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(new Scene(new FXMLLoader().load(this.getClass().getResourceAsStream("/ResultWindow.fxml"))));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (CityApplication.cities.contains(userInput) && !CityApplication.user.passedCities.contains(userInput)) {
                String computerCity = Utils.getRandomCityFromDataBase(userInput, CityApplication.user.passedCities);
                computer.setText(computerCity);
                if (computerCity != null) {
                    computer.setText(computerCity);
                    CityApplication.user.passedCities.add(computerCity);
                } else {
                    computer.setText("You win!");
                }
            } else if (!CityApplication.cities.contains(userInput)) {
                computer.setText("There is no such city in DataBase");
            } else if (CityApplication.user.passedCities.contains(userInput)) {
                computer.setText("This city is used");
            }
            CityApplication.dataBase.getDataBaseAPI().replaceObject("username", CityApplication.user.username, CityApplication.user);
        }
    }
}

