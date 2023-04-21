package com.example.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class RecursiveTree extends Application {
    final int SCENE_WIDTH = 1000;
    final int SCENE_HEIGHT = 1000;
    double startX = 500;
    double startY = 500;
    double minBranchLength = 1;
    double maxBranchLength = 100;
    double branchLengthFactor = 0.2;
    double rootAngle = Math.PI / 2;
    double branchAngle = Math.PI / 4;


    Pane pane = new Pane();

    public static void main(String[] args)
    {
        // Launch the application.
        launch(args);
    }
    @Override
    public void start(Stage stage) {

        drawBranch(startX, startY, maxBranchLength, rootAngle);
        Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setTitle("Recursive Tree");
        stage.setScene(scene);
        stage.show();
    }

    public void drawBranch(double x1, double y1, double length, double angle) {
        if (length > minBranchLength) {
            double x2 = x1 + Math.cos(angle) * length;
            double y2 = y1 - Math.sin(angle) * length;
            pane.getChildren().add(new Line(x1, y1, x2, y2));
            drawBranch(x2, y2, length * branchLengthFactor, angle + branchAngle );
            drawBranch(x2, y2, length * branchLengthFactor, angle - branchAngle );
        }
    }
}
