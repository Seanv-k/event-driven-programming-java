package com.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EventPropagationDemo extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("JavaFX Event Propagation Demo");
        Label message = new Label("Click the button to observe event propagation.");

        Button button = new Button("Click Me");

        VBox root = new VBox(15);

        // Parent event handler
        root.addEventHandler(ActionEvent.ACTION, event -> {
            System.out.println("Parent: Event received.");
        });

        // Child event handler
        button.addEventHandler(ActionEvent.ACTION, event -> {
            System.out.println("Button: Event handled.");
            message.setText("Event handled successfully!");
        });

        root.getChildren().addAll(title, button, message);

        Scene scene = new Scene(root, 450, 250);

        stage.setTitle("Week 7 - Event Propagation");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}