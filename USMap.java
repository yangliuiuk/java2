package com.example.javafx;


import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Demo extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        final String STATE_ABBV_FILE = "states.txt";
        final String MAP_FILE = "usmap.txt";
        final int MAP_WIDTH = 1300;
        final int MAP_HEIGHT = 800;
        final double LATITUDE_NORTHEST = 52;
        final double LONGITUDE_WESTEST = -130;
        final double SCALE_X = 20;
        final double SCALE_Y = 25;

        HashMap<String, String> stateAbbreviations = getStateAbbreviations(STATE_ABBV_FILE);
        HashMap<String, ArrayList<double[]>> statePolygonCoordinates = getStatePolygonCoordinates(MAP_FILE);
        /**
        stateAbbreviations.forEach((state, abbreviation) -> {
            System.out.printf("%s %s \n", state, abbreviation);
        });
        statePolygonCoordinates.forEach((state, coordinates) -> {
            System.out.println(state);
            for (double[] coordinate : coordinates) {
                System.out.printf("%f %f \n", coordinate[0], coordinate[1]);
            }
        });
         */
        Pane pane = new Pane();

        statePolygonCoordinates.forEach( (state, coordinatesList) -> {
            Polygon polygon = new Polygon();
            double xFirst = -1;
            double yFirst = -1;
            for (double[] coordinates : coordinatesList) {
                double latitude = coordinates[0] ;
                double longitude = coordinates[1] ;
                double x, y;
                x = (longitude - LONGITUDE_WESTEST) * SCALE_X;
                y = (LATITUDE_NORTHEST - latitude) * SCALE_Y;
                polygon.getPoints().addAll(x, y);
                if (xFirst < 0) {
                    xFirst = x;
                    yFirst = y;
                }
            }
            polygon.getPoints().addAll(xFirst, yFirst);
            polygon.setFill(Color.WHITE);
            polygon.setStroke(Color.BLACK);
            pane.getChildren().add(polygon);

            /** display the state abbreviation at the center of the state polygon */
            double[] center = getCenter(polygon);
            String abbreviation = stateAbbreviations.get(state);
            Text abbrevationText = new Text(center[0], center[1], abbreviation);
            pane.getChildren().add(abbrevationText);

            /** add event to the state polygon */
            polygon.setOnMouseClicked( e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    polygon.setFill(Color.RED);
                }
                else if (e.getButton() == MouseButton.SECONDARY) {
                    polygon.setFill(Color.BLUE);
                }
                else if (e.getButton() == MouseButton.MIDDLE) {
                    polygon.setFill(Color.WHITE);
                }
            });
        });

        Scene scene = new Scene(pane, MAP_WIDTH, MAP_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("US Map");
        stage.show();
    }

    /** read the coordinates */
    public HashMap<String, ArrayList<double[]>> getStatePolygonCoordinates(String filePath) {
        /** key: state name, value: a list of coordinates (latitude and longitude) */
        HashMap<String, ArrayList<double[]>> statePolygonCoordinates = new HashMap<>();
        String currentState = "";
        try {
            Scanner input = new Scanner(new File(filePath));
            while (input.hasNextLine()) {
                String s = input.nextLine().strip();
                if (Character.isAlphabetic(s.charAt(0))) {
                    currentState = s;
                    statePolygonCoordinates.put(currentState, new ArrayList<double[]>());
                }
                else {
                    String[] coordinatesStrings = s.split(" ");
                    double[] coordinates = new double[2];
                    for (int i = 0; i <= 1; i++) {
                        coordinates[i] = Double.parseDouble(coordinatesStrings[i]);
                    }
                    statePolygonCoordinates.get(currentState).add(coordinates);
                }
            }
            input.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return statePolygonCoordinates;
    }

    /** read the state abbreviations */
    public HashMap<String, String> getStateAbbreviations(String filePath) {
        /**
        * @param filePath the file path to load the state abbreviation data
         * @return a hashmap
         * */
        HashMap<String, String> stateAbbreviations = new HashMap<>();
        try {
            Scanner input = new Scanner(new File(filePath));
            while (input.hasNextLine()) {
                String s = input.nextLine().strip();
                String[] strings = s.split("\t");
                String state = strings[0];
                String abbreviation = strings[1];
                if (!state.equals("State")) {
                    stateAbbreviations.put(state, abbreviation);
                }
            }
            input.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return stateAbbreviations;
    }

    /** get the center of a polygon */
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
