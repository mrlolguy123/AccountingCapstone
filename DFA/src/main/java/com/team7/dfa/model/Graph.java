package com.team7.dfa.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Graph {
    public String currentDir = Paths.get("").toAbsolutePath().toString();
    public String pythonScriptPath = currentDir + File.separator + "src" + File.separator + "main" + File.separator + "python" + File.separator + "scripts" + File.separator + "generate_graphs.py";
    public String graphWorkingDirectory = currentDir + File.separator + "src" + File.separator + "main" + File.separator + "python" + File.separator + "generated_graphs";

    Graph(){
        
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

    public void updateGraphImage(ImageView graph_image, String graphName) {
        graph_image.setImage(new Image("file:///"+ graphWorkingDirectory + File.separator + graphName + ".png"));
    }
}
