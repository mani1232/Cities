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
import java.util.Optional;

public class GameWindow {

    public TextField city;
    public Label enterCityName;
    public Button yourTurn;
    public Label computer;


    public void nextTurn(ActionEvent actionEvent) {
        String userInput = city.getText().trim();
        try {
            String computerText = computer.getText().split(" ")[1];
            if (userInput.startsWith(computerText.substring(computerText.length() - 1).toUpperCase())) {
                initMethod(userInput, actionEvent);
            } else {
                computer.setText("You lose");
            }
        } catch (Exception ignored) {
            initMethod(userInput, actionEvent);
        }

    }

    private void initMethod(String userInput, ActionEvent actionEvent) {
        if (userInput.equalsIgnoreCase("exit")) {
            try {
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(new Scene(new FXMLLoader().load(this.getClass().getResourceAsStream("/ResultWindow.fxml"))));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (CityApplication.user.availableCities.contains(userInput)) {
                CityApplication.user.availableCities.remove(userInput);
                Optional<String> computerCity = CityApplication.user.availableCities.stream().filter(s -> s.startsWith(userInput.substring(userInput.length() - 1).toUpperCase())).findAny();
                if (computerCity.isPresent()) {
                    computer.setText("Computer: " + computerCity.get());
                } else {
                    computer.setText("You win!");
                }
            } else {
                computer.setText("There is no such city in DataBase");
            }
            CityApplication.dataBase.getDataBaseAPI().replaceObject("username", CityApplication.user.username, CityApplication.user);
        }
    }
}

