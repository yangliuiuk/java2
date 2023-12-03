/*
Course Project: Create a Contact Management System with Graphical User Interface

You are required to create a JavaFX program for a Contact Management System (CMS). The program should follow the provided requirements:

1. Define a Contact class with the following attributes and methods:

Class attributes:
    name (String)
    phoneNumber (String)
Class methods:
    Constructor(s)
    Get and set method for each attribute
    A toString() method

2. Create a main class that extends the Application class and implements the CMS functionality.

3. Create the user interface 

Create a main window with a menu bar at the top.
Add a menu named "Menu" to the menu bar.
Add the following menu items to the menu bar: "Add Contact," "Search Contact," "Delete Contact," and "Exit."
Add a ListView below the menu bar to display the list of contacts.
The user interface should look like the following:

4. Implement event handlers for the menu items as follows:

"Add Contact": Show a dialog box to let the user add a contact by name and phone number. After clicking the "Add" button, display an alert box to inform the user that the contact has been added. Refresh the contact list in the main window.
"Search Contact": Show a dialog box to let the user search for a contact by name. After clicking the "Search" button, display an alert box with the contact's information if found. If not found, display an alert informing the user that the contact doesn't exist.
"Delete Contact": Show a dialog box to let the user delete a contact by name. After clicking the "Delete" button, if the contact exists, delete it and show an alert indicating that the contact has been deleted. Refresh the contact list in the main window. If the contact doesn't exist, show an alert informing the user that the contact doesn't exist.
"Exit": Exit the program when this menu item is clicked.

5. Bonus Task: Creativity/Extra Features

A maximum of 10 Extra points will be given on implementing additional features or enhancements to improve the user experience or functionality of the CMS. For example:

Use CSS to enhance the user interface.
Use a file to store contact list for backup.
Allow users to categorize contacts into groups (e.g., family, friends, work).
Allow users to edit the details of existing contacts, not just phone numbers. This could include modifying contact names or adding additional information like email addresses or addresses.

Submission:

Java source code
Screenshots of output / A short video showing the workflow
Note:

To create an alert box, use javafx.scene.control.Alert class. Below is a sample code:

import javafx.scene.control.Alert;

Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setHeaderText("Success");
alert.setContentText("Contact " + name + " added!");
alert.showAndWait();
More information can be referred from: https://www.geeksforgeeks.org/javafx-alert-with-examples/Links to an external site. 
*/

package com.example.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import java.util.HashMap;

class Contact {
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return name + "\t: " + phoneNumber;
    }
}

public class CMS extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane mainPane = new BorderPane();

        MenuBar menuBar = new MenuBar();

        Menu menu = new Menu("Menu");

        MenuItem addItem = new MenuItem("Add Contact");
        addItem.getStyleClass().add("menu-item");
        MenuItem searchItem = new MenuItem("Search Contact");
        MenuItem deleteItem = new MenuItem("Delete Contact");
        MenuItem exitItem = new MenuItem("Exit");

        menu.getItems().addAll(addItem, searchItem, deleteItem, new SeparatorMenuItem(), exitItem);
        menuBar.getMenus().add(menu);

        mainPane.setTop(menuBar);

        Label contactLabel = new Label("Contact List");
        contactLabel.setStyle("-fx-font-weight: bold;");

        ListView<String> contactListView = new ListView<>();

        VBox centerBox = new VBox(contactLabel, contactListView);

        mainPane.setCenter(centerBox);

        Scene mainScene = new Scene(mainPane, 400, 300);
        mainScene.getStylesheets().add("style.css");
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Contact Management System");
        primaryStage.show();

        HashMap<String, Contact> contacts = new HashMap<>();

        addItem.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Add a Contact");

            Label nameLabel = new Label("Contact Name:");
            Label numberLabel = new Label("Phone Number:");

            TextField nameField = new TextField();
            TextField numberField = new TextField();

            Button addButton = new Button("Add");

            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);

            gridPane.addRow(0, nameLabel, nameField);
            gridPane.addRow(1, numberLabel, numberField);
            gridPane.add(addButton, 1, 2);

            Scene scene = new Scene(gridPane, 300, 200);

            stage.setScene(scene);
            stage.show();

            addButton.setOnAction(addEvent -> {
                String name = nameField.getText();
                String number = numberField.getText();

                Contact contact = new Contact(name, number);
                contacts.put(name, contact);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Success");
                alert.setContentText("Contact " + name + " added!");
                alert.showAndWait();

                stage.close();

                contactListView.getItems().clear();

                for (Contact c: contacts.values()) {
                    contactListView.getItems().add(c.toString());
                }
            });
        });

        deleteItem.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Delete Contact");

            Label nameLabel = new Label("Contact Name:");
            TextField nameField = new TextField();

            Button deleteButton = new Button("Delete");

            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);

            gridPane.addRow(0, nameLabel, nameField);
            gridPane.add(deleteButton, 1, 1);


            Scene scene = new Scene(gridPane, 300, 200);
            stage.setScene(scene);
            stage.show();

            deleteButton.setOnAction(deleteEvent -> {
                String name = nameField.getText();
                if (contacts.containsKey(name)) {
                    contacts.remove(name);
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Success");
                    alert.setContentText("Contact " + name + " deleted!");
                    alert.showAndWait();
                    contactListView.getItems().clear();

                    for (Contact c: contacts.values()) {
                        contactListView.getItems().add(c.toString());
                    }

                    stage.close();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Contact " + name + " doesn't exist.");
                    alert.showAndWait();

                }

            });

        });

        searchItem.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Search Contact");

            Label nameLabel = new Label("Contact Name:");
            TextField nameField = new TextField();

            Button searchButton = new Button("Search");

            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);

            gridPane.addRow(0, nameLabel, nameField);
            gridPane.add(searchButton, 1, 1);

            Scene scene = new Scene(gridPane, 300, 200);
            stage.setScene(scene);
            stage.show();

            searchButton.setOnAction(searchEvent -> {
                String name = nameField.getText();
                if (contacts.containsKey(name)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Contact Found!");
                    alert.setContentText(contacts.get(name).toString());
                    alert.showAndWait();
                    stage.close();


                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error");
                    alert.setContentText("Contact " + name + " doesn't exist.");
                    alert.showAndWait();

                }

            });
        });

        exitItem.setOnAction(event -> {
            primaryStage.close();
        });
    }
}
