package com.eventdriven.week08;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class BidirectionalBindingDemo extends Application {

    @Override
    public void start(Stage stage) {

        Label instruction = new Label("Adjust the slider or enter a value:");

        Slider slider = new Slider(0, 100, 50);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);

        TextField valueField = new TextField("50");

        // Connect the slider and text field in both directions
        Bindings.bindBidirectional(
            valueField.textProperty(),
            slider.valueProperty(),
            new NumberStringConverter()
        );

        VBox root = new VBox(15);
        root.getChildren().addAll(
            instruction,
            slider,
            valueField
        );

        Scene scene = new Scene(root, 450, 200);

        stage.setTitle("Bidirectional Binding Demo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}