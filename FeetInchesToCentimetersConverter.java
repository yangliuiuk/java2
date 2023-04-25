package com.example.java2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FeetInchesToCentimetersConverter extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a GridPane layout to organize the UI elements
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        // Create the input fields for feet and inches
        Label feetLabel = new Label("Feet:");
        grid.add(feetLabel, 0, 0);
        TextField feetTextField = new TextField();
        grid.add(feetTextField, 1, 0);

        Label inchesLabel = new Label("Inches:");
        grid.add(inchesLabel, 0, 1);
        TextField inchesTextField = new TextField();
        grid.add(inchesTextField, 1, 1);

        // Create the button to initiate the conversion
        Button convertButton = new Button("Convert");
        grid.add(convertButton, 1, 2);

        // Create the label to display the result
        Label resultLabel = new Label();
        grid.add(resultLabel, 0, 3, 2, 1);

        // Set the action to be taken when the button is clicked
        convertButton.setOnAction(event -> {
            // Get the input values from the text fields
            double feet = Double.parseDouble(feetTextField.getText());
            double inches = Double.parseDouble(inchesTextField.getText());

            // Convert the values to centimeters
            double centimeters = (feet * 12 + inches) * 2.54;

            // Display the result in the label
            resultLabel.setText("Result: " + centimeters + " cm");
        });

        // Create the scene and set it on the stage
        Scene scene = new Scene(grid, 250, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Feet and Inches to Centimeters Converter");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
