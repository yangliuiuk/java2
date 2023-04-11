package com.example.javafx;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class USMap extends Application {
    public static void main(String[] args)
    {
        // Launch the application.
        launch(args);
    }
    @Override
    public void start(Stage stage) {

        int MAP_WIDTH = 1300;
        int MAP_HEIGHT = 600;
        double SCALE = 20.0;
        double MAX_LATITUDE = 50.0;
        double MIN_LONGITUDE = -130;
        int FONT_SIZE = 10;

        Pane pane = new Pane();
        Scene scene = new Scene(pane, MAP_WIDTH, MAP_HEIGHT);
        stage.setTitle("US Map");
        stage.setScene(scene);
        stage.show();

        HashMap<String, ArrayList<double[]>> statePolygonCoordinates = getStatePolygonCoordinates();
        HashMap<String, String> stateAbbreviations = getStateAbbreviations();

        statePolygonCoordinates.forEach((state, coordinates) -> {
            Polygon polygon = new Polygon();
            for (double[] coordinate : coordinates) {
                double x = (coordinate[1] - MIN_LONGITUDE) * SCALE;
                double y = (MAX_LATITUDE - coordinate[0]) * SCALE;
                polygon.getPoints().addAll(x, y);
            }
            polygon.getPoints().addAll((coordinates.get(0)[1] - MIN_LONGITUDE)* SCALE, (MAX_LATITUDE - coordinates.get(0)[0]) * SCALE);
            polygon.setFill(Color.WHITE);
            polygon.setStroke(Color.BLACK);
            polygon.setStrokeWidth(1);

            polygon.setOnMouseClicked(e->{
                if (e.getButton() == MouseButton.PRIMARY) {
                    polygon.setFill(Color.RED);
                }
                else if (e.getButton() == MouseButton.SECONDARY) {
                    polygon.setFill(Color.BLUE);
                }
                else {
                    polygon.setFill(Color.WHITE);
                }
            });

            pane.getChildren().add(polygon);

            String abbreviation = stateAbbreviations.get(state);
            double[] center = getCenter(polygon);
            Text text = new Text(center[0], center[1], abbreviation);
            text.setFont(new Font("Arial", FONT_SIZE));
            pane.getChildren().add(text);

        });
    }

    public HashMap<String, ArrayList<double[]>> getStatePolygonCoordinates() {
        HashMap<String, ArrayList<double[]>> statePolygonCoordinates = new HashMap<>();
        try {
            Scanner input = new Scanner(new File("usmap.txt"));
            String currentState = "";
            while (input.hasNext()) {
                String s = input.nextLine().strip();
                if (Character.isAlphabetic(s.charAt(0))) {
                    currentState = s;
                    statePolygonCoordinates.put(currentState, new ArrayList<double[]>());
                }
                else {
                    String[] coordinatesStrings = s.split(" ");
                    double[] coordinate = new double[2];
                    for (int i = 0; i < 2; i++){
                        coordinate[i] = Double.parseDouble(coordinatesStrings[i]);
                    }
                    statePolygonCoordinates.get(currentState).add(coordinate);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        /**
         statePolygonCoordinates.forEach((state, coordinates) -> {
            System.out.println(state);
            for (double[] coordinate : coordinates) {
                System.out.printf("%f %f \n", coordinate[0], coordinate[1]);
            }
        });
        */
        return statePolygonCoordinates;
    }

    public HashMap<String, String> getStateAbbreviations() {
        HashMap<String, String> stateAbbreviations = new HashMap<>();
        try {
            Scanner input = new Scanner(new File("states.txt"));
            while (input.hasNext()) {
                String s = input.nextLine().strip();
                String[] strings = s.split("\t");
                String state = strings[0];
                String abbreviation = strings[1];
                if (!state.equals("State")) {
                    stateAbbreviations.put(state, abbreviation);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        /**
        stateAbbreviations.forEach((state, abbreviation) -> {
            System.out.printf("%s %s \n", state, abbreviation);
        });
         */
        return stateAbbreviations;
    }

    public double[] getCenter(Polygon polygon) {
        Bounds bounds = polygon.getBoundsInLocal();
        double centerX = (bounds.getMinX() + bounds.getMaxX()) / 2;
        double centerY = (bounds.getMinY() + bounds.getMaxY()) / 2;
        double[] center = new double[2];
        center[0] = centerX;
        center[1] = centerY;
        return center;
    }
    
}
