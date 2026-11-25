package com.example;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProgressDemo extends Application {

    @Override
    public void start(Stage primaryStage) {

        ProgressBar progressBar = new ProgressBar(0);
        Label statusLabel = new Label("Ready");
        Button startButton = new Button("Start Download");

        progressBar.setPrefWidth(250);

        startButton.setOnAction(e -> {

            Task<Void> task = new Task<>() {

                @Override
                protected Void call() throws Exception {

                    for (int i = 0; i <= 100; i++) {

                        Thread.sleep(50);

                        updateProgress(i, 100);
                        updateMessage("Downloading... " + i + "%");
                    }

                    return null;
                }
            };

            progressBar.progressProperty().bind(task.progressProperty());
            statusLabel.textProperty().bind(task.messageProperty());

            startButton.setDisable(true);

            task.setOnSucceeded(event -> {
                progressBar.progressProperty().unbind();
                statusLabel.textProperty().unbind();

                progressBar.setProgress(1);
                statusLabel.setText("Download Complete!");

                startButton.setDisable(false);
            });

            task.setOnFailed(event -> {
                progressBar.progressProperty().unbind();
                statusLabel.textProperty().unbind();

                statusLabel.setText("Download Failed!");
                startButton.setDisable(false);
            });

            Thread backgroundThread = new Thread(task);
            backgroundThread.setDaemon(true);
            backgroundThread.start();
        });

        VBox root = new VBox(20, startButton, progressBar, statusLabel);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("JavaFX Progress Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}