package com.example.napr;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class HelloController {
    @FXML
    private Button RED;
    @FXML
    private Button GREEN;
    @FXML
    private Button BLUE;
    @FXML
    private Label label;

    @FXML
    protected void REDPressed() throws IOException
    {
        label.setText(RED.getText());
        action(RED.getText());
    }

    @FXML
    protected void BLUEPressed() throws IOException
    {
        label.setText(BLUE.getText());
        action(BLUE.getText());
    }

    @FXML
    protected void GREENPressed() throws IOException
    {
        label.setText(GREEN.getText());
        action(GREEN.getText());
    }

    // The important part
    private void action(String name) throws IOException
    {
        String lowername = name.toLowerCase();
        String file = lowername + "-view.fxml";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(file));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle(name);
            stage.setScene(scene);
            stage.show();
        }
        catch(FileNotFoundException e)
        {
            label.setText(name + " command not recognized.");
        }
    }
}