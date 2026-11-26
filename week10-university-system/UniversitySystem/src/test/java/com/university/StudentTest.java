package com.university;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testStudentCreation() {
        Student student = new Student(
                "Alice Smith", "RU001", "Computer Science");

        assertEquals("Alice Smith", student.getName());
        assertEquals("RU001", student.getRegistrationNumber());
        assertEquals("Computer Science", student.getCourse());
    }

    @Test
    void testStudentDetailsAreTrimmed() {
        Student student = new Student(
                " Alice Smith ", " RU001 ", " Computer Science ");

        assertEquals("Alice Smith", student.getName());
        assertEquals("RU001", student.getRegistrationNumber());
        assertEquals("Computer Science", student.getCourse());
    }

    @Test
    void testEmptyStudentName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Student("", "RU001", "Computer Science"));
    }

    @Test
    void testEmptyRegistrationNumber() {
        assertThrows(IllegalArgumentException.class,
                () -> new Student("Alice", "", "Computer Science"));
    }

    @Test
    void testEmptyAcademicCourse() {
        assertThrows(IllegalArgumentException.class,
                () -> new Student("Alice", "RU001", ""));
    }

    @Test
    void testUpdateStudentName() {
        Student student = new Student(
                "Alice", "RU001", "Computer Science");

        student.setName("Charlie");

        assertEquals("Charlie", student.getName());
    }

    @Test
    void testUpdateAcademicCourse() {
        Student student = new Student(
                "Alice", "RU001", "Computer Science");

        student.setCourse("Software Engineering");

        assertEquals("Software Engineering", student.getCourse());
    }

    @Test
    void testInvalidNameUpdate() {
        Student student = new Student(
                "Alice", "RU001", "Computer Science");

        assertThrows(IllegalArgumentException.class,
                () -> student.setName(""));
    }
}