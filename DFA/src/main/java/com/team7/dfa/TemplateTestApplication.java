package com.team7.dfa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

/**
 * This class is the class that runs the application.
 * It extends the Application class from JavaFX.
 */
public class TemplateTestApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TemplateTestApplication.class.getResource("homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 900);
        stage.setTitle("DFA");
        stage.setMinHeight(900);
        stage.setMinWidth(1600);
        stage.setScene(scene);
        stage.show();

        URL iconURL = getClass().getResource("icon.png");
        assert iconURL != null;
        Image icon = new Image(iconURL.toString());
        stage.getIcons().add(icon);
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}