package com.eventdriven.week08;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UnidirectionalBindingDemo extends Application {

    @Override
    public void start(Stage stage) {

        Label instruction = new Label("Enter your name:");

        TextField nameField = new TextField();

        Label greetingLabel = new Label();

        // Bind the greeting to the text field
        greetingLabel.textProperty().bind(
            nameField.textProperty().concat(", welcome!")
        );

        VBox root = new VBox(15);
        root.getChildren().addAll(
            instruction,
            nameField,
            greetingLabel
        );

        Scene scene = new Scene(root, 400, 200);

        stage.setTitle("Unidirectional Binding Demo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}