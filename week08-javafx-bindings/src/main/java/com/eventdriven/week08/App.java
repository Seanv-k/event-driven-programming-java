package com.eventdriven.week08;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("Week 8: JavaFX Properties and Bindings");
        Label message = new Label("Welcome to JavaFX!");

        VBox root = new VBox(15);
        root.getChildren().addAll(title, message);

        Scene scene = new Scene(root, 500, 300);

        stage.setTitle("Week 8 - Properties and Bindings");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}