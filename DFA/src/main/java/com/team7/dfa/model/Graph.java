package com.team7.dfa.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Graph {
    private String sqlCommand;
    private String graphChoice;
    private String x_name;
    private String y_name;
    private String col_name;
    private String graphName;
    public String currentDir = Paths.get("").toAbsolutePath().toString();
    public String pythonScriptPath = currentDir + File.separator + "src" + File.separator + "main" + File.separator + "python" + File.separator + "scripts" + File.separator + "generate_graphs.py";
    public String graphWorkingDirectory = currentDir + File.separator + "src" + File.separator + "main" + File.separator + "python" + File.separator + "generated_graphs";

    public Graph(String sqlCommand, String graphChoice, String x_name, String y_name, String graphName) {
        this.sqlCommand = sqlCommand;
        this.graphChoice = graphChoice;
        this.x_name = x_name;
        this.y_name = y_name;
        this.graphName = graphName;
        generateGraph();
    }

    public Graph(String sqlCommand, String graphChoice, String col_name, String graphName){
        this.sqlCommand = sqlCommand;
        this.graphChoice = graphChoice;
        this.col_name = col_name;
        this.graphName = graphName;
        generateGraph();
    }

    private void generateGraph(){
        try{
            List<String> commands = new ArrayList<>();
            commands.add("python");
            commands.add(pythonScriptPath);
            commands.add(this.sqlCommand);
            commands.add(this.graphChoice);
            commands.add(this.x_name);
            commands.add(this.y_name);
            commands.add(this.graphName);
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            Process process = processBuilder.start();
            process.waitFor();

        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

    public void updateGraphImage(ImageView graph_image) {
        graph_image.setImage(new Image("file:///"+ graphWorkingDirectory + File.separator + this.graphName + ".png"));
    }
}
