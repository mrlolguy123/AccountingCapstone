package com.team7.dfa.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
    private Button generateGraphsButton;

    public String currentDir = Paths.get("").toAbsolutePath().toString();
    public String graphWorkingDirectory = currentDir + File.separator + "src" + File.separator + "main" + File.separator + "python" + File.separator + "generated_graphs";

    public String pythonScriptPath = currentDir + File.separator + "src" + File.separator + "main" + File.separator + "python" + File.separator + "scripts" + File.separator + "generate_graphs.py";

    @FXML
    private void generateGraphOnClick() {
        generateGraphsButton.setOnAction(event -> {
            String sqlCommand = "SELECT * FROM amanFinancialRecords ORDER BY totalprofit ASC";
            String graphName = "home_profit_graph";
            String graphChoice = "2";
            generateGraph(sqlCommand, graphName, graphChoice);
            updateGraphImages(graphName);
            System.out.println("Successfully Executed");
        });
    }

    public void generateGraph(String sqlCommand, String graphName, String graphChoice){
        try{
            List<String> commands = new ArrayList<>();
            commands.add("python");
            commands.add(pythonScriptPath);
            commands.add(sqlCommand);
            commands.add(graphName);
            commands.add(graphChoice);

            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            Process process = processBuilder.start();
            process.waitFor();

        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void updateGraphImages(String graphName) {
        graph1.setImage(new Image("file:///"+ graphWorkingDirectory + File.separator + graphName + ".png"));
        //graph2.setImage(new Image("file:///"+ graphWorkingDirectory + File.separator + "testingscatter.png"));
    }

}