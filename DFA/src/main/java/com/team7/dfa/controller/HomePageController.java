package com.team7.dfa.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class HomePageController extends ParentController {
    @FXML
    private Button homeButton;

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

    public String currentDir = Paths.get("").toAbsolutePath().toString();
    public String graphWorkingDirectory = currentDir + File.separator + "src" + File.separator + "main" + File.separator + "python" + File.separator + "generated_graphs";


    private void runScript(){
        try{
            String pythonScriptPath = currentDir + File.separator + "src" + File.separator + "main" + File.separator + "python" + File.separator + "scripts" + File.separator + "generate_graphs.py";
            ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath);
            //processBuilder.directory(new File(pythonWorkingDirectory)); //this is unused??
            Process process = processBuilder.start();
            process.waitFor();

        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

    private void updateGraphImages() {
        //graph1.setImage(new Image("file:///C:/Users/Aman%20Sahu/Desktop/Capstone%202024/AccountingCapstone/DFA/src/main/python/generated_graphs/sample_BoxPlot.png"));
        graph2.setImage(new Image("file:///"+ graphWorkingDirectory + File.separator + "testingbar.png"));
        graph3.setImage(new Image("file:///"+ graphWorkingDirectory + File.separator + "testingscatter.png"));
    }

    @FXML
    private void generateGraphs() {
        generateGraphsButton.setOnAction(event -> {
            System.out.println(graphWorkingDirectory);
            runScript();
            updateGraphImages();
            System.out.println("Successfully Executed");
        });
    }
}