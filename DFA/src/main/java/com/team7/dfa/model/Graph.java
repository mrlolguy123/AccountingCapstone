package com.team7.dfa.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The Graph class represents a graph generator for the DFA application.
 * It generates graphs based on specified parameters and updates the graph image.
 * The types of graphs you can generate are:
 * "1" - Scatter Plot
 * "2" -
 */
public class Graph {
    private final String sqlCommand; // SQL Command for data retrieval
    private final String graphChoice; // Type of graph to generate
    private final String x_name; // X-axis label in sqlCommand table
    private final String y_name; // Y-axis label in sqlCommand table
    private final String graphName; // Name of the graph generated

    // Paths for Python scripts and generated graphs
    public String currentDir = Paths.get("").toAbsolutePath().toString();
    public String pythonScriptPath = currentDir + File.separator + "src" + File.separator + "main" + File.separator + "python" + File.separator + "scripts" + File.separator + "generate_graphs.py";
    public String graphWorkingDirectory = currentDir + File.separator + "src" + File.separator + "main" + File.separator + "python" + File.separator + "generated_graphs";


    /**
     * Constructs a Graph object with specified parameters and generates the graph.
     * @param sqlCommand SQL command for data retrieval
     * @param graphChoice Type of graph to generate
     * @param x_name X-axis label in sqlCommand table
     * @param y_name Y-axis label in sqlCommand table
     * @param graphName Name of the generated graph
     */
    public Graph(String sqlCommand, String graphChoice, String x_name, String y_name, String graphName) {
        this.sqlCommand = sqlCommand;
        this.graphChoice = graphChoice;
        this.x_name = x_name;
        this.y_name = y_name;
        this.graphName = graphName;
        generateGraph();
    }

    /**
     * Constructs a Graph object with specified parameters and generates the pie graph.
     * @param sqlCommand SQL command for data retrieval
     * @param col_name Column name for pie-graph generation
     * @param graphName Name of the generated graph
     */
    public Graph(String sqlCommand, String col_name, String graphName){
        this.sqlCommand = sqlCommand;
        this.graphChoice = "4";
        this.x_name = col_name;
        this.y_name = "";
        this.graphName = graphName;
        generateGraph();
    }

    /**
     * Generates the graph based on the specified parameters using a Python script.
     */
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

    /**
     * Updates the graph image displayed in the application.
     * @param graph_image ImageView component to display the graph image
     */
    public void updateGraphImage(ImageView graph_image) {
        graph_image.setImage(new Image("file:///"+ graphWorkingDirectory + File.separator + this.graphName + ".png"));
    }
}
