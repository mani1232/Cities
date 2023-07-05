package cc.worldmandia.controllers;

import cc.worldmandia.CityApplication;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GameWindow {


    public TextField CityId;
    public Label EnterCityNameId;
    public Button YourTurnId;
    public Label ComputerId;

    public void nextTurn(ActionEvent actionEvent) {
        CityApplication.user.passedCities.add("dfghfgh");
        CityApplication.dataBase.getDataBaseAPI().replaceObject("username", CityApplication.user.username, CityApplication.user);
    }
}
