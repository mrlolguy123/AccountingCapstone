package com.team7.dfa.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Toggle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.IOException;

public class HomePageController {
    @FXML
    private Button logoutButton;

    @FXML
    private ScrollPane vbox_container;

    @FXML
    private ImageView graph1;

    @FXML
    private ImageView graph2;

    @FXML
    private ImageView graph3;

    @FXML
    private Button generateGraphsButton;

    @FXML
    protected void logoutClicked(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void generate_graphs() {
        generateGraphsButton.setOnAction(event -> {
            try {
                ProcessBuilder processBuilder = new ProcessBuilder("python", "C:\\Users\\Aman Sahu\\Desktop\\Capstone 2024\\AccountingCapstone\\DFA\\src\\main\\python\\scripts\\generate_graphs.py");
                processBuilder.directory(new File("C:\\Users\\Aman Sahu\\Desktop\\Capstone 2024\\AccountingCapstone\\DFA\\src\\main\\python\\scripts"));
                Process process = processBuilder.start();
                process.waitFor();
                updateGraphImages();
                System.out.println("Successfully Executed");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
//    private void generate_graphs() {
//        generateGraphsButton.setOnAction(event -> {
//            try {
//                String command = "python C:\\Users\\Aman Sahu\\Desktop\\Capstone 2024\\AccountingCapstone\\DFA\\src\\main\\python\\scripts\\generate_graphs.py";
//                Process process= Runtime.getRuntime().exec(command);
//                process.waitFor();
//                updateGraphImages();
//                System.out.println("Successfully Executed");
//
//            } catch (IOException | InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//    }

    private void updateGraphImages() {
        //graph1.setImage(new Image("file:///C:/Users/Aman%20Sahu/Desktop/Capstone%202024/AccountingCapstone/DFA/src/main/python/generated_graphs/sample_BoxPlot.png"));
        graph2.setImage(new Image("file:///C:/Users/Aman%20Sahu/Desktop/Capstone%202024/AccountingCapstone/DFA/src/main/python/generated_graphs/sample_BarGraph.png"));
        graph3.setImage(new Image("file:///C:/Users/Aman%20Sahu/Desktop/Capstone%202024/AccountingCapstone/DFA/src/main/python/generated_graphs/sample_ScatterPlot.png"));
    }
}