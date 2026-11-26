package com.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EventBubblingDemo extends Application {

    @Override
    public void start(Stage stage) {

        Button button = new Button("Click Me");

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(button);

        VBox root = new VBox(15);
        root.getChildren().add(stackPane);

        button.addEventHandler(ActionEvent.ACTION, event -> {
            System.out.println("1. Button: Event received");
        });

        stackPane.addEventHandler(ActionEvent.ACTION, event -> {
            System.out.println("2. StackPane: Event bubbled");
        });

        root.addEventHandler(ActionEvent.ACTION, event -> {
            System.out.println("3. VBox: Event bubbled");
        });

        Scene scene = new Scene(root, 450, 250);

        stage.setTitle("Week 7 - Event Bubbling");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}