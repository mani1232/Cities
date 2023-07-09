package cc.worldmandia.controllers;

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

    public TextField CityId;
    public Label EnterCityNameId;
    public Button YourTurnId;
    public Label ComputerId;


    public void nextTurn(ActionEvent actionEvent) {
        String userInput = CityId.getText().trim().toLowerCase();

        if (userInput.equalsIgnoreCase("exit")) {
            try {
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(new Scene(new FXMLLoader().load(this.getClass().getResourceAsStream("/ResultWindow.fxml"))));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            if (CityDataBase.cities.contains(userInput) && !UsedCities.usedCities.contains(userInput)) {
                String computerCity = CityDataBase.getRandomCityFromDataBase(userInput, (UsedCities) UsedCities.usedCities);
                ((UsedCities) UsedCities.usedCities).addUserInput(userInput);
                if (computerCity != null) {
                    ComputerId.setText(computerCity);
                    ((UsedCities) UsedCities.usedCities).addComputerCity(computerCity);
                } else {
                    ComputerId.setText("You win!");
                }
            } else if (!CityDataBase.cities.contains(userInput)) {
                ComputerId.setText("There is no such city in DataBase");
            } else if (UsedCities.usedCities.contains(userInput)) {
                ComputerId.setText("This city is used");
            }

                // Actions
//                CityApplication.user.passedCities.add(userInput);
//                CityApplication.dataBase.getDataBaseAPI().replaceObject("username", CityApplication.user.username, CityApplication.user);
            }
        }
    }

