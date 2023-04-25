package com.example.java2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FeetInchesToCentimetersConverter2 extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create the input fields for feet and inches
        Label feetLabel = new Label("Feet:");
        TextField feetTextField = new TextField();
        HBox feetHBox = new HBox(feetLabel, feetTextField);

        Label inchesLabel = new Label("Inches:");
        TextField inchesTextField = new TextField();
        HBox inchesHBox = new HBox(inchesLabel, inchesTextField);

        // Create the button to initiate the conversion
        Button convertButton = new Button("Convert");
        // Create the label to display the result
        Label resultLabel = new Label();

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

        VBox mainVBox = new VBox(5, feetHBox, inchesHBox, convertButton, resultLabel);
        mainVBox.setPadding(new Insets(10, 10, 10, 10));

        // Create the scene and set it on the stage
        Scene scene = new Scene(mainVBox, 250, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Feet and Inches to Centimeters Converter");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
