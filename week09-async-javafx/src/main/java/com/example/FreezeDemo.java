package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FreezeDemo extends Application {

    @Override
    public void start(Stage primaryStage) {

        Label statusLabel = new Label("Ready");
        Button freezeButton = new Button("Freeze the UI!");

        // This is the bad way.
        // Thread.sleep() blocks the JavaFX Application Thread.

        freezeButton.setOnAction(e -> {

            statusLabel.setText("Processing...");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            statusLabel.setText("Done!");
        });

        VBox root = new VBox(20, freezeButton, statusLabel);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("The Freeze Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}