package com.eventdriven.week08;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {

    private final StringProperty name =
            new SimpleStringProperty("");

    private final IntegerProperty age =
            new SimpleIntegerProperty(0);

    // Name getter
    public String getName() {
        return name.get();
    }

    // Name setter
    public void setName(String value) {
        name.set(value);
    }

    // Age getter
    public int getAge() {
        return age.get();
    }

    // Age setter
    public void setAge(int value) {
        age.set(value);
    }

    // Property getters
    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty ageProperty() {
        return age;
    }
}