package cc.worldmandia.controllers;

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
        stage.close();
        System.exit(0);
    }

    public void restart(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        try
        {
            stage.setScene(new Scene(new FXMLLoader().load(this.getClass().getResourceAsStream("/GameWindow.fxml"))));
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        //TODO: Reset counters
    }
}



