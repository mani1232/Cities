package cc.worldmandia;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.print.DocFlavor;
import java.util.ResourceBundle;

public class Example {

    @FXML
    // The reference of inputText will be injected by the FXML loader
    private TextField inputText;

    // The reference of outputText will be injected by the FXML loader
    @FXML
    private TextArea outputText;

    // location and resources will be automatically injected by the FXML loader
    @FXML
    private DocFlavor.URL location;

    @FXML
    private ResourceBundle resources;

    // Add a public no-args constructor
    public Example() {
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void printOutput() {
        outputText.setText(inputText.getText());
    }

}
