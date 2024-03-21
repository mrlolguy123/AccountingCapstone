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
    private ImageView graph1;

    @FXML
    private ImageView graph2;

    @FXML
    private Button generateGraphsButton;

    public String currentDir = Paths.get("").toAbsolutePath().toString();
    public String graphWorkingDirectory = currentDir + File.separator + "src" + File.separator + "main" + File.separator + "python" + File.separator + "generated_graphs";

    public String pythonScriptPath = currentDir + File.separator + "src" + File.separator + "main" + File.separator + "python" + File.separator + "scripts" + File.separator + "generate_graphs.py";

    @FXML
    private void generateGraphButton() {
        generateGraphsButton.setOnAction(event -> {
            String sqlCommand = "SELECT * FROM amanFinancialRecords ORDER BY totalprofit ASC";
            String graphChoice = "2";
            String x_name = "year";
            String y_name = "totalprofit";
            String graphName = "home_profit_graph";
            generateGraph(sqlCommand, graphChoice, x_name, y_name, graphName);
            updateGraphImage(graphName);
            System.out.println("Successfully Executed");
        });
    }

    @FXML
    private void updateGraphImage(String graphName) {
        graph1.setImage(new Image("file:///"+ graphWorkingDirectory + File.separator + graphName + ".png"));
    }


    public void generateGraph(String sqlCommand, String graphChoice, String x_name, String y_name, String graphName){
        try{
            List<String> commands = new ArrayList<>();
            commands.add("python");
            commands.add(pythonScriptPath);
            commands.add(sqlCommand);
            commands.add(graphChoice);
            commands.add(x_name);
            commands.add(y_name);
            commands.add(graphName);

            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            Process process = processBuilder.start();
            process.waitFor();

        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
}