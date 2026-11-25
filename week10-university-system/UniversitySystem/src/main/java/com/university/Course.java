package com.university;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    private String courseCode;
    private String courseName;
    private int capacity;
    private List<Student> registeredStudents;

    public Course(String courseCode, String courseName, int capacity) {

        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be empty.");
        }

        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty.");
        }

        if (capacity <= 0) {
            throw new IllegalArgumentException("Course capacity must be greater than zero.");
        }

        this.courseCode = courseCode.trim();
        this.courseName = courseName.trim();
        this.capacity = capacity;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public void setCourseName(String courseName) {

        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty.");
        }

        this.courseName = courseName.trim();
    }

    public boolean setCapacity(int capacity) {

        if (capacity < registeredStudents.size() || capacity <= 0) {
            System.out.println("Invalid capacity. It cannot be less than current registrations or zero.");
            return false;
        }

        this.capacity = capacity;
        return true;
    }

    public boolean registerStudent(Student student) {

        if (student == null) {
            System.out.println("Registration failed: Invalid student.");
            return false;
        }

        if (registeredStudents.contains(student)) {
            System.out.println("Registration failed: Student already registered.");
            return false;
        }

        if (registeredStudents.size() >= capacity) {
            System.out.println("Registration failed: Course is full.");
            return false;
        }

        registeredStudents.add(student);

        System.out.println(student.getName()
                + " successfully registered for " + courseName);

        return true;
    }

    public boolean withdrawStudent(Student student) {

        if (!registeredStudents.contains(student)) {
            System.out.println("Withdrawal failed: Student is not registered.");
            return false;
        }

        registeredStudents.remove(student);

        System.out.println(student.getName()
                + " successfully withdrawn from " + courseName);

        return true;
    }
}