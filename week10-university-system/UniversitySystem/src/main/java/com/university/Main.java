package com.university;

import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static UniversitySystem university;

    public static void main(String[] args) {

        university = UniversitySystem.loadFromFile("university_data.ser");

        int choice;

        do {
            System.out.println("\n=================================");
            System.out.println("     UNIVERSITY MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Register Student for Course");
            System.out.println("4. Withdraw Student from Course");
            System.out.println("5. Search Student");
            System.out.println("6. Search Course");
            System.out.println("7. Update Student Details");
            System.out.println("8. Update Course Details");
            System.out.println("9. Delete Student");
            System.out.println("10. Delete Course");
            System.out.println("11. Display All Students");
            System.out.println("12. Display All Courses");
            System.out.println("13. Display Course Registrations");
            System.out.println("14. Display Individual Student Details");
            System.out.println("0. Exit");
            System.out.println("=================================");

            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addCourse();
                    break;
                case 3:
                    registerStudent();
                    break;
                case 4:
                    withdrawStudent();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    searchCourse();
                    break;
                case 7:
                    updateStudent();
                    break;
                case 8:
                    updateCourse();
                    break;
                case 9:
                    deleteStudent();
                    break;
                case 10:
                    deleteCourse();
                    break;
                case 11:
                    university.displayStudents();
                    break;
                case 12:
                    university.displayCourses();
                    break;
                case 13:
                    university.displayCourseRegistrations();
                    break;
                case 14:
                    displayStudentDetails();
                    break;
                case 0:
                    System.out.println("Exiting University Management System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);

        university.saveToFile("university_data.ser");
        input.close();
    }

    private static int readInt(String message) {

        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readAcademicProgramme() {

        System.out.println("\nAvailable Academic Programmes:");
        System.out.println("1. Bachelor of Computer Science");
        System.out.println("2. Diploma in IT");

        while (true) {
            int choice = readInt("Select programme (1 or 2): ");

            if (choice == 1) {
                return "Bachelor of Computer Science";
            } else if (choice == 2) {
                return "Diploma in IT";
            }

            System.out.println("Invalid selection. Choose 1 or 2.");
        }
    }

    private static void addStudent() {

        try {
            System.out.print("Enter student name: ");
            String name = input.nextLine();

            System.out.print("Enter registration number: ");
            String registrationNumber = input.nextLine();

            String academicCourse = readAcademicProgramme();

            System.out.print("Enter student email: ");
            String email = input.nextLine();

            Student student = new Student(
                    name,
                    registrationNumber,
                    academicCourse,
                    email
            );

            university.addStudent(student);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addCourse() {

        try {
            System.out.print("Enter course code: ");
            String code = input.nextLine();

            System.out.print("Enter course name: ");
            String name = input.nextLine();

            int capacity = readInt("Enter course capacity: ");

            Course course = new Course(code, name, capacity);
            university.addCourse(course);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void registerStudent() {

        System.out.print("Enter student registration number: ");
        String registrationNumber = input.nextLine();

        System.out.print("Enter course code: ");
        String courseCode = input.nextLine();

        Student student = university.searchStudent(registrationNumber);
        Course course = university.searchCourse(courseCode);

        if (student == null || course == null) {
            System.out.println("Student or course not found.");
            return;
        }

        university.registerStudentForCourse(student, course);
    }

    private static void withdrawStudent() {

        System.out.print("Enter student registration number: ");
        String registrationNumber = input.nextLine();

        System.out.print("Enter course code: ");
        String courseCode = input.nextLine();

        Student student = university.searchStudent(registrationNumber);
        Course course = university.searchCourse(courseCode);

        if (student == null || course == null) {
            System.out.println("Student or course not found.");
            return;
        }

        university.withdrawStudentFromCourse(student, course);
    }

    private static void searchStudent() {

        System.out.print("Enter registration number: ");
        String registrationNumber = input.nextLine();

        Student student = university.searchStudent(registrationNumber);

        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Student found: " + student.getName());
            System.out.println("Registration Number: "
                    + student.getRegistrationNumber());
            System.out.println("Academic Programme: " + student.getCourse());
            System.out.println("Email: " + student.getEmail());
        }
    }

    private static void searchCourse() {

        System.out.print("Enter course code: ");
        String code = input.nextLine();

        Course course = university.searchCourse(code);

        if (course == null) {
            System.out.println("Course not found.");
        } else {
            System.out.println("Course found: " + course.getCourseName());
            System.out.println("Course Code: " + course.getCourseCode());
            System.out.println("Capacity: " + course.getCapacity());
        }
    }

    private static void updateStudent() {

        System.out.print("Enter registration number: ");
        String registrationNumber = input.nextLine();

        System.out.print("Enter new student name: ");
        String newName = input.nextLine();

        String newCourse = readAcademicProgramme();

        try {
            university.updateStudentDetails(
                    registrationNumber,
                    newName,
                    newCourse
            );

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateCourse() {

        System.out.print("Enter course code: ");
        String code = input.nextLine();

        System.out.print("Enter new course name: ");
        String newName = input.nextLine();

        int newCapacity = readInt("Enter new capacity: ");

        try {
            university.updateCourseDetails(
                    code,
                    newName,
                    newCapacity
            );

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteStudent() {

        System.out.print("Enter registration number: ");
        String registrationNumber = input.nextLine();

        university.deleteStudent(registrationNumber);
    }

    private static void deleteCourse() {

        System.out.print("Enter course code: ");
        String code = input.nextLine();

        university.deleteCourse(code);
    }

    private static void displayStudentDetails() {

        System.out.print("Enter registration number: ");
        String registrationNumber = input.nextLine();

        university.displayStudentDetails(registrationNumber);
    }
}