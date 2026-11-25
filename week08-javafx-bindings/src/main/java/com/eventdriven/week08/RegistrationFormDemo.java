package com.eventdriven.week08;

import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegistrationFormDemo extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("Student Registration Form");

        // Name field
        Label nameLabel = new Label("Full Name:");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");

        // Email field
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");

        // Age field
        Label ageLabel = new Label("Age:");
        TextField ageField = new TextField();
        ageField.setPromptText("Enter your age");

        // Registration button
        Button registerButton = new Button("Register");

        // Feedback label
        Label feedbackLabel = new Label();

        // Check whether the email is valid
        BooleanBinding invalidEmail =
            javafx.beans.binding.Bindings.createBooleanBinding(
                () -> !emailField.getText().matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
                ),
                emailField.textProperty()
            );

        // Check whether the age is valid
        BooleanBinding invalidAge =
            javafx.beans.binding.Bindings.createBooleanBinding(
                () -> {
                    try {
                        int age = Integer.parseInt(ageField.getText());
                        return age < 1 || age > 120;
                    } catch (NumberFormatException e) {
                        return true;
                    }
                },
                ageField.textProperty()
            );

        // Check whether the name is empty
        BooleanBinding emptyName =
            nameField.textProperty().isEmpty();

        // Combine all validation conditions
        registerButton.disableProperty().bind(
            emptyName.or(invalidEmail).or(invalidAge)
        );

        // Button action
        registerButton.setOnAction(event -> {
            feedbackLabel.setText(
                "Registration successful! Welcome, "
                + nameField.getText() + "!"
            );
        });

        VBox root = new VBox(10);

        root.getChildren().addAll(
            title,
            nameLabel,
            nameField,
            emailLabel,
            emailField,
            ageLabel,
            ageField,
            registerButton,
            feedbackLabel
        );

        Scene scene = new Scene(root, 400, 450);

        stage.setTitle("Reactive Registration Form");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
