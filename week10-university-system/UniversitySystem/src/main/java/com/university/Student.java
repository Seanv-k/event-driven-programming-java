package com.university;

import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String registrationNumber;
    private String course;
    private String email;

    // Original constructor retained for compatibility.
    public Student(String name, String registrationNumber, String course) {
        this(name, registrationNumber, course, null);
    }

    // Constructor including email for database integration.
    public Student(String name, String registrationNumber,
                   String course, String email) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }

        if (registrationNumber == null || registrationNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Registration number cannot be empty.");
        }

        if (course == null || course.trim().isEmpty()) {
            throw new IllegalArgumentException("Academic course cannot be empty.");
        }

        if (email != null &&
                (email.trim().isEmpty() || !email.contains("@"))) {
            throw new IllegalArgumentException("Invalid email address.");
        }

        this.name = name.trim();
        this.registrationNumber = registrationNumber.trim();
        this.course = course.trim();
        this.email = email == null ? null : email.trim();
    }

    public String getName() {
        return name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getCourse() {
        return course;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }

        this.name = name.trim();
    }

    public void setCourse(String course) {

        if (course == null || course.trim().isEmpty()) {
            throw new IllegalArgumentException("Academic course cannot be empty.");
        }

        this.course = course.trim();
    }
}
