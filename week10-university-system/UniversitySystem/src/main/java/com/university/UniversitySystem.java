package com.university;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UniversitySystem implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Student> students;
    private List<Course> courses;

    public UniversitySystem() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
    }

    // SAVE UNIVERSITY DATA
    public void saveToFile(String fileName) {

        try (ObjectOutputStream output =
                     new ObjectOutputStream(new FileOutputStream(fileName))) {

            output.writeObject(this);
            System.out.println("University data saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // LOAD UNIVERSITY DATA
    public static UniversitySystem loadFromFile(String fileName) {

        try (ObjectInputStream input =
                     new ObjectInputStream(new FileInputStream(fileName))) {

            UniversitySystem system =
                    (UniversitySystem) input.readObject();

            System.out.println("University data loaded successfully.");
            return system;

        } catch (FileNotFoundException e) {

            System.out.println("No saved data found. Starting a new system.");

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error loading data: " + e.getMessage());
        }

        return new UniversitySystem();
    }

    // ADD STUDENT
    public boolean addStudent(Student student) {

        if (student == null) {
            System.out.println("Cannot add an empty student.");
            return false;
        }

        if (searchStudent(student.getRegistrationNumber()) != null) {
            System.out.println("Student already exists.");
            return false;
        }

        students.add(student);
        System.out.println("Student added successfully.");
        return true;
    }

    // ADD COURSE
    public boolean addCourse(Course course) {

        if (course == null) {
            System.out.println("Cannot add an empty course.");
            return false;
        }

        if (searchCourse(course.getCourseCode()) != null) {
            System.out.println("Course already exists.");
            return false;
        }

        courses.add(course);
        System.out.println("Course added successfully.");
        return true;
    }

    // REGISTER STUDENT FOR COURSE
    public void registerStudentForCourse(Student student, Course course) {

        if (!students.contains(student)) {
            System.out.println("Student not found in university records.");
            return;
        }

        if (!courses.contains(course)) {
            System.out.println("Course not found in university records.");
            return;
        }

        course.registerStudent(student);
    }

    // WITHDRAW STUDENT FROM COURSE
    public void withdrawStudentFromCourse(Student student, Course course) {

        if (!students.contains(student)) {
            System.out.println("Student not found in university records.");
            return;
        }

        if (!courses.contains(course)) {
            System.out.println("Course not found in university records.");
            return;
        }

        course.withdrawStudent(student);
    }

    // DISPLAY ALL STUDENTS
    public void displayStudents() {

        System.out.println("Registered Students:");

        for (Student student : students) {
            System.out.println(
                    student.getName() + " - " +
                    student.getRegistrationNumber() + " - " +
                    student.getCourse()
            );
        }
    }

    // DISPLAY INDIVIDUAL STUDENT DETAILS
    public void displayStudentDetails(String registrationNumber) {

        Student student = searchStudent(registrationNumber);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("\nStudent Details:");
        System.out.println("Name: " + student.getName());
        System.out.println("Registration Number: "
                + student.getRegistrationNumber());
        System.out.println("Academic Course: " + student.getCourse());

        System.out.println("Registered University Courses:");

        boolean found = false;

        for (Course course : courses) {

            if (course.getRegisteredStudents().contains(student)) {
                System.out.println("- " + course.getCourseName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No course registrations found.");
        }
    }

    // DISPLAY ALL COURSES
    public void displayCourses() {

        System.out.println("Available Courses:");

        for (Course course : courses) {
            System.out.println(
                    course.getCourseCode() + " - " +
                    course.getCourseName() + " - Capacity: " +
                    course.getCapacity()
            );
        }
    }

    // DISPLAY COURSE REGISTRATIONS
    public void displayCourseRegistrations() {

        System.out.println("\nCourse Registrations:");

        for (Course course : courses) {

            System.out.println("\n" + course.getCourseName());

            for (Student student : course.getRegisteredStudents()) {
                System.out.println("- " + student.getName());
            }
        }
    }

    // SEARCH STUDENT
    public Student searchStudent(String registrationNumber) {

        if (registrationNumber == null) {
            return null;
        }

        for (Student student : students) {

            if (student.getRegistrationNumber()
                    .equalsIgnoreCase(registrationNumber)) {
                return student;
            }
        }

        return null;
    }

    // SEARCH COURSE
    public Course searchCourse(String courseCode) {

        if (courseCode == null) {
            return null;
        }

        for (Course course : courses) {

            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }

        return null;
    }

    // UPDATE STUDENT DETAILS
    public boolean updateStudentDetails(
            String registrationNumber,
            String newName,
            String newCourse) {

        Student student = searchStudent(registrationNumber);

        if (student == null) {
            System.out.println("Student not found. Update failed.");
            return false;
        }

        student.setName(newName);
        student.setCourse(newCourse);

        System.out.println("Student details updated successfully.");
        return true;
    }

    // UPDATE COURSE DETAILS
    public boolean updateCourseDetails(
            String courseCode,
            String newName,
            int newCapacity) {

        Course course = searchCourse(courseCode);

        if (course == null) {
            System.out.println("Course not found. Update failed.");
            return false;
        }

        if (newName == null || newName.trim().isEmpty()) {
            System.out.println("Course name cannot be empty.");
            return false;
        }

        if (!course.setCapacity(newCapacity)) {
            return false;
        }

        course.setCourseName(newName);

        System.out.println("Course details updated successfully.");
        return true;
    }

    // DELETE STUDENT
    public boolean deleteStudent(String registrationNumber) {

        Student student = searchStudent(registrationNumber);

        if (student == null) {
            System.out.println("Student not found. Deletion failed.");
            return false;
        }

        for (Course course : courses) {

            if (course.getRegisteredStudents().contains(student)) {
                course.withdrawStudent(student);
            }
        }

        students.remove(student);

        System.out.println("Student deleted successfully.");
        return true;
    }

    // DELETE COURSE
    public boolean deleteCourse(String courseCode) {

        Course courseToDelete = searchCourse(courseCode);

        if (courseToDelete == null) {
            System.out.println("Course not found. Deletion failed.");
            return false;
        }

        courses.remove(courseToDelete);

        System.out.println("Course deleted successfully.");
        return true;
    }
}