package cc.worldmandia.controllers;

import cc.worldmandia.CityApplication;
import cc.worldmandia.DataBase.Objects.CityUser;
import cc.worldmandia.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RegistrationOrLoginWindow {

    public PasswordField password;
    public TextField login;
    public Label incorrectText;


    public void loginOrRegister(ActionEvent actionEvent) {
        if (CityApplication.dataBase.getDataBaseAPI().contains("username", login.getText())) {
            CityUser cityUser = CityApplication.dataBase.getDataBaseAPI().getObject("username", login.getText());
            if (cityUser.password.equals(password.getText())) {
                CityApplication.user = cityUser;
                next(actionEvent);
            } else {
                incorrectText.setVisible(true);
                Utils.executor.schedule(() -> {
                    if (incorrectText.isVisible()) {
                        incorrectText.setVisible(false);
                    }
                }, 5, TimeUnit.SECONDS);
            }
        } else {
            CityUser cityUser = new CityUser();
            cityUser.setPassword(password.getText());
            cityUser.setUsername(login.getText());
            cityUser.availableCities.addAll(CityApplication.cities);
            CityApplication.user = cityUser;
            CityApplication.dataBase.getDataBaseAPI().createObject(cityUser);
            next(actionEvent);
        }
    }

    private void next(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(new FXMLLoader().load(this.getClass().getResourceAsStream("/GameWindow.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

