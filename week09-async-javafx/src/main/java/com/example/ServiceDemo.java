package com.example;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ServiceDemo extends Application {

    @Override
    public void start(Stage primaryStage) {

        Label statusLabel = new Label("Ready");

        Button syncButton = new Button("Sync Data");
        Button cancelButton = new Button("Cancel");

        ProgressIndicator progressIndicator = new ProgressIndicator();

        progressIndicator.setVisible(false);
        cancelButton.setDisable(true);

        Service<String> syncService = new Service<>() {

            @Override
            protected Task<String> createTask() {

                return new Task<>() {

                    @Override
                    protected String call() throws Exception {

                        for (int i = 0; i <= 10; i++) {

                            if (isCancelled()) {
                                return null;
                            }

                            updateMessage("Syncing... " + (i * 10) + "%");

                            Thread.sleep(500);
                        }

                        return "Data Synced Successfully!";
                    }
                };
            }
        };

        syncButton.setOnAction(e -> {

            if (syncService.getState() == javafx.concurrent.Worker.State.READY) {
                syncService.start();
            } else {
                syncService.restart();
            }
        });

        cancelButton.setOnAction(e -> {
            syncService.cancel();
        });

        syncService.messageProperty().addListener((observable, oldValue, newValue) -> {
            statusLabel.setText(newValue);
        });

        syncService.setOnRunning(event -> {
            syncButton.setDisable(true);
            cancelButton.setDisable(false);
            progressIndicator.setVisible(true);
            statusLabel.setText("Starting sync...");
        });

        syncService.setOnSucceeded(event -> {
            statusLabel.setText(syncService.getValue());
            syncButton.setDisable(false);
            cancelButton.setDisable(true);
            progressIndicator.setVisible(false);
        });

        syncService.setOnCancelled(event -> {
            statusLabel.setText("Sync Cancelled!");
            syncButton.setDisable(false);
            cancelButton.setDisable(true);
            progressIndicator.setVisible(false);
        });

        syncService.setOnFailed(event -> {
            statusLabel.setText("Sync Failed!");
            syncButton.setDisable(false);
            cancelButton.setDisable(true);
            progressIndicator.setVisible(false);
        });

        VBox root = new VBox(
                15,
                syncButton,
                cancelButton,
                progressIndicator,
                statusLabel
        );

        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("JavaFX Service Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
