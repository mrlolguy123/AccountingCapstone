package com.team7.dfa.controller;

import com.team7.dfa.model.Graph;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.nio.file.Paths;

public class HomePageController extends ParentController {

    @FXML
    private ImageView graph1;

    @FXML
    private ImageView graph2;

    @FXML
    private Button generateGraphsButton;

    @FXML
    private void generateGraphButton() {
        generateGraphsButton.setOnAction(event -> {
            String sqlCommand = "SELECT * FROM amanFinancialRecords ORDER BY totalprofit ASC";
            String graphChoice = "2";
            String x_name = "year";
            String y_name = "totalprofit";
            String graphName = "home_profit_graph";
            Graph sample_graph = new Graph();
            sample_graph.generateGraph(sqlCommand, graphChoice, x_name, y_name, graphName);
            sample_graph.updateGraphImage(graph1, graphName);
            System.out.println("Successfully Executed");
        });
    }
}