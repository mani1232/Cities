package cc.worldmandia.controllers;

import cc.worldmandia.CityApplication;
import cc.worldmandia.DataBase.Objects.CityUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationOrLoginWindow {

    public PasswordField password;
    public TextField login;


    public void loginOrRegister(ActionEvent actionEvent) {
        if (CityApplication.dataBase.getDataBaseAPI().contains("username", login.getText())) {
            CityUser cityUser = CityApplication.dataBase.getDataBaseAPI().getObject("username", login.getText());
            if (cityUser.password.equals(password.getText())) {
                CityApplication.user = cityUser;
            }
        } else {
            CityUser cityUser = new CityUser();
            cityUser.setPassword(password.getText());
            cityUser.setUsername(login.getText());
            CityApplication.dataBase.getDataBaseAPI().createObject(cityUser);
        }
        try {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(new FXMLLoader().load(this.getClass().getResourceAsStream("/GameWindow.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

