package com.eventdriven.week08;

import javafx.application.Application;
import javafx.stage.Stage;

public class ListenerDemo extends Application {

    @Override
    public void start(Stage primaryStage) {

        Person person = new Person();

        // 1. InvalidationListener
        person.nameProperty().addListener(observable -> {
            System.out.println(
                "1. [InvalidationListener] Name was changed!"
            );
        });

        // 2. ChangeListener
        person.nameProperty().addListener(
            (observable, oldValue, newValue) -> {
                System.out.println(
                    "2. [ChangeListener] Name changed from '"
                    + oldValue + "' to '" + newValue + "'"
                );
            }
        );

        // Trigger changes
        System.out.println("--- Setting name to Alice ---");
        person.setName("Alice");

        System.out.println("\n--- Setting name to Bob ---");
        person.setName("Bob");

        System.out.println("\n--- Setting name to Bob (same value) ---");
        person.setName("Bob");

        primaryStage.setTitle("Listener Demo");
        primaryStage.setWidth(400);
        primaryStage.setHeight(200);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}