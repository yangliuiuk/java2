package com.example.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;


public class Pizza extends Application {
    public static void main(String[] args)
    {
        // Launch the application.
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Label crustLabel = new Label("Crust:");

        RadioButton handTossedRadio = new RadioButton("Hand Tossed");
        RadioButton homemadePanRadio = new RadioButton("Homemade Pan");
        RadioButton thinCrustRadio = new RadioButton("Thin Crust");
        handTossedRadio.setSelected(true);
        ToggleGroup crustRadioGroup = new ToggleGroup();
        handTossedRadio.setToggleGroup(crustRadioGroup);
        homemadePanRadio.setToggleGroup(crustRadioGroup);
        thinCrustRadio.setToggleGroup(crustRadioGroup);

        HBox crustRadioVBox = new HBox(10, handTossedRadio, homemadePanRadio, thinCrustRadio);
        VBox crustVBox = new VBox(10, crustLabel, crustRadioVBox);
        crustVBox.setAlignment(Pos.CENTER);

        Label sizeLabel = new Label("Size:");

        RadioButton smallRadio = new RadioButton("Small");
        RadioButton mediumRadio = new RadioButton("Medium");
        RadioButton largeRadio = new RadioButton("Large");
        smallRadio.setSelected(true);
        ToggleGroup sizeRadioGroup = new ToggleGroup();
        smallRadio.setToggleGroup(sizeRadioGroup);
        mediumRadio.setToggleGroup(sizeRadioGroup);
        largeRadio.setToggleGroup(sizeRadioGroup);

        HBox sizeRadioVBox = new HBox(10, smallRadio, mediumRadio, largeRadio);
        VBox sizeVBox = new VBox(10, sizeLabel, sizeRadioVBox);
        sizeVBox.setAlignment(Pos.CENTER);

        Label toppingsLabel = new Label("Toppings:");

        CheckBox hamCheckBox = new CheckBox("Ham");
        CheckBox salamiCheckBox = new CheckBox("Salami");
        CheckBox pepperoniCheckBox = new CheckBox("Pepperoni");

        VBox toppingsCheckBoxVBox = new VBox(10, hamCheckBox, salamiCheckBox, pepperoniCheckBox);
        VBox toppingsVBox = new VBox(10, toppingsLabel, toppingsCheckBoxVBox);
        toppingsVBox.setAlignment(Pos.CENTER);

        Button totalButton = new Button("Get Total");
        Label totalLabel = new Label("$0.00");
        VBox totalVBox = new VBox(10, totalButton, totalLabel);
        totalVBox.setAlignment(Pos.CENTER);

        totalButton.setOnAction(e->{
            double total = 0;
            if (smallRadio.isSelected()){
                total += 6;
            }
            if (mediumRadio.isSelected()){
                total += 8;
            }
            if (largeRadio.isSelected()){
                total += 10;
            }
            if (hamCheckBox.isSelected()){
                total += 2;
            }
            if (salamiCheckBox.isSelected()){
                total += 2;
            }
            if (pepperoniCheckBox.isSelected()){
                total += 2;
            }
            totalLabel.setText(String.format("$%,.2f", total));
        });

        VBox mainVBox = new VBox(10, crustVBox, sizeVBox, toppingsVBox, totalVBox);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setPadding(new Insets(10));
        
        Scene scene = new Scene(mainVBox);
        primaryStage.setTitle("Pizza Maker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
