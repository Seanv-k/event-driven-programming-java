package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EventFilterDemo extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("JavaFX Event Filter Demo");
        Label message = new Label("Click the button.");

        Button button = new Button("Click Me");

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(button);

        VBox root = new VBox(15);
        root.getChildren().addAll(title, stackPane, message);

        // Parent event filter
        root.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("1. VBox: Capturing event.");
        });

        // StackPane event filter
        stackPane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("2. StackPane: Capturing event.");
        });

        // Button event filter
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("3. Button: Event filter executed.");
        });

        // Button event handler
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("4. Button: Event handler executed.");
            message.setText("Event handled successfully!");
        });

        Scene scene = new Scene(root, 450, 250);

        stage.setTitle("Week 7 - Event Filters");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}