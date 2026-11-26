package com.example;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskDemo extends Application {

    @Override
    public void start(Stage primaryStage) {

        Label statusLabel = new Label("Ready");
        Button startButton = new Button("Start Task");

        startButton.setOnAction(e -> {

            Task<Void> task = new Task<>() {

                @Override
                protected Void call() throws Exception {

                    Thread.sleep(5000);

                    return null;
                }
            };

            startButton.setDisable(true);
            statusLabel.setText("Processing...");

            task.setOnSucceeded(event -> {
                statusLabel.setText("Done!");
                startButton.setDisable(false);
            });

            task.setOnFailed(event -> {
                statusLabel.setText("Task failed!");
                startButton.setDisable(false);
            });

            Thread backgroundThread = new Thread(task);
            backgroundThread.setDaemon(true);
            backgroundThread.start();
        });

        VBox root = new VBox(20, startButton, statusLabel);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("JavaFX Task Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}