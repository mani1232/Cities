package cc.worldmandia.controllers;

import cc.worldmandia.CityApplication;
import cc.worldmandia.DataBase.Objects.CityUser;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationOrLoginWindow {

    public PasswordField password;
    public TextField login;


    public void loginOrRegister(ActionEvent actionEvent) {
        if (CityApplication.dataBase.getDataBaseAPI().contains("username", login.getText())) {
            if (CityApplication.dataBase.getDataBaseAPI().getObject("username", login.getText()) instanceof CityUser cityUser) {
                if (!cityUser.password.equals(password.getText())) {
                    return;
                }
            }
        } else {
            CityUser cityUser = new CityUser();
            cityUser.setPassword(password.getText());
            cityUser.setUsername(login.getText());
            CityApplication.dataBase.getDataBaseAPI().createObject(cityUser);
        }
    }
}

