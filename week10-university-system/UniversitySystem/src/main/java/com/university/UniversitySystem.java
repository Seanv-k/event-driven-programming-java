package com.university;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UniversitySystem {

    private List<Student> students;
    private List<Course> courses;
    private boolean databaseAvailable = true;

    public UniversitySystem() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
    }

    // LOAD DATA FROM MYSQL
    public static UniversitySystem loadFromFile(String fileName) {

        UniversitySystem system = new UniversitySystem();
        system.loadFromDatabase();

        return system;
    }

    private void loadFromDatabase() {

        try (Connection connection = DatabaseUtil.getConnection()) {

            students.clear();
            courses.clear();

            String studentSQL =
                    "SELECT s.registration_number, s.first_name, " +
                    "s.last_name, s.email, p.program_name " +
                    "FROM students s LEFT JOIN programs p " +
                    "ON s.program_id = p.program_id";

            try (Statement statement = connection.createStatement();
                 ResultSet results = statement.executeQuery(studentSQL)) {

                while (results.next()) {

                    String name = results.getString("first_name") +
                            " " + results.getString("last_name");

                    String program = results.getString("program_name");

                    if (program == null) {
                        program = "Unassigned";
                    }

                    Student student = new Student(
                            name,
                            results.getString("registration_number"),
                            program,
                            results.getString("email")
                    );

                    students.add(student);
                }
            }

            String courseSQL =
                    "SELECT course_code, title, capacity FROM course";

            try (Statement statement = connection.createStatement();
                 ResultSet results = statement.executeQuery(courseSQL)) {

                while (results.next()) {

                    courses.add(new Course(
                            results.getString("course_code"),
                            results.getString("title"),
                            results.getInt("capacity")
                    ));
                }
            }

            String registrationSQL =
                    "SELECT s.registration_number, cr.course_code " +
                    "FROM course_registrations cr " +
                    "JOIN students s ON cr.student_id = s.student_id";

            try (Statement statement = connection.createStatement();
                 ResultSet results = statement.executeQuery(registrationSQL)) {

                while (results.next()) {

                    Student student = searchStudent(
                            results.getString("registration_number")
                    );

                    Course course = searchCourse(
                            results.getString("course_code")
                    );

                    if (student != null && course != null) {
                        course.registerStudent(student);
                    }
                }
            }

            databaseAvailable = true;
            System.out.println("University data loaded from MySQL successfully.");

        } catch (SQLException e) {

            databaseAvailable = false;

            System.out.println("Database loading failed: " + e.getMessage());
        }
    }

    private boolean checkDatabase() {

        if (!databaseAvailable) {
            System.out.println("Database unavailable. Operation cancelled.");
            return false;
        }

        return true;
    }

    // FIND PROGRAMME ID
    private int findProgramId(Connection connection, String programName)
            throws SQLException {

        String sql =
                "SELECT program_id FROM programs WHERE program_name = ?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, programName);

            try (ResultSet results = statement.executeQuery()) {

                if (results.next()) {
                    return results.getInt("program_id");
                }
            }
        }

        return -1;
    }

    // SPLIT STUDENT NAME
    private String[] splitName(String fullName) {

        String name = fullName.trim();
        int space = name.indexOf(' ');

        if (space == -1) {
            return new String[]{name, "N/A"};
        }

        return new String[]{
                name.substring(0, space),
                name.substring(space + 1).trim()
        };
    }

    // ADD STUDENT
    public boolean addStudent(Student student) {

        if (!checkDatabase()) return false;

        if (student == null || student.getEmail() == null) {
            System.out.println("Student email is required.");
            return false;
        }

        if (searchStudent(student.getRegistrationNumber()) != null) {
            System.out.println("Student already exists.");
            return false;
        }

        try (Connection connection = DatabaseUtil.getConnection()) {

            int programId = findProgramId(connection, student.getCourse());

            if (programId == -1) {
                System.out.println("Academic programme not found.");
                return false;
            }

            String[] names = splitName(student.getName());

            String sql =
                    "INSERT INTO students " +
                    "(registration_number, first_name, last_name, email, program_id) " +
                    "VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement statement =
                         connection.prepareStatement(sql)) {

                statement.setString(1, student.getRegistrationNumber());
                statement.setString(2, names[0]);
                statement.setString(3, names[1]);
                statement.setString(4, student.getEmail());
                statement.setInt(5, programId);

                statement.executeUpdate();
            }

            students.add(student);

            System.out.println("Student added successfully.");
            return true;

        } catch (SQLException e) {

            System.out.println("Error adding student: " + e.getMessage());
            return false;
        }
    }

    // ADD COURSE
    public boolean addCourse(Course course) {

        if (!checkDatabase()) return false;

        if (course == null || searchCourse(course.getCourseCode()) != null) {
            System.out.println("Course already exists or is invalid.");
            return false;
        }

        String sql =
                "INSERT INTO course (course_code, title, capacity) " +
                "VALUES (?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, course.getCourseCode());
            statement.setString(2, course.getCourseName());
            statement.setInt(3, course.getCapacity());

            statement.executeUpdate();

            courses.add(course);

            System.out.println("Course added successfully.");
            return true;

        } catch (SQLException e) {

            System.out.println("Error adding course: " + e.getMessage());
            return false;
        }
    }

    // REGISTER STUDENT FOR COURSE
    public void registerStudentForCourse(Student student, Course course) {

        if (!checkDatabase()) return;

        if (!students.contains(student) || !courses.contains(course)) {
            System.out.println("Student or course not found.");
            return;
        }

        if (course.getRegisteredStudents().contains(student)) {
            System.out.println("Student is already registered.");
            return;
        }

        String sql =
                "INSERT INTO course_registrations (student_id, course_code) " +
                "SELECT student_id, ? FROM students " +
                "WHERE registration_number = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, course.getCourseCode());
            statement.setString(2, student.getRegistrationNumber());

            if (statement.executeUpdate() == 0) {
                System.out.println("Student registration failed.");
                return;
            }

            course.registerStudent(student);

        } catch (SQLException e) {

            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    // WITHDRAW STUDENT FROM COURSE
    public void withdrawStudentFromCourse(Student student, Course course) {

        if (!checkDatabase()) return;

        if (!students.contains(student) || !courses.contains(course)) {
            System.out.println("Student or course not found.");
            return;
        }

        String sql =
                "DELETE cr FROM course_registrations cr " +
                "JOIN students s ON cr.student_id = s.student_id " +
                "WHERE s.registration_number = ? AND cr.course_code = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getRegistrationNumber());
            statement.setString(2, course.getCourseCode());

            int deleted = statement.executeUpdate();

            if (deleted > 0) {
                course.withdrawStudent(student);
            } else {
                System.out.println("Student is not registered for this course.");
            }

        } catch (SQLException e) {

            System.out.println("Withdrawal failed: " + e.getMessage());
        }
    }

    // DISPLAY ALL STUDENTS
    public void displayStudents() {

        System.out.println("\nRegistered Students:");

        for (Student student : students) {

            System.out.println(
                    student.getName() + " - " +
                    student.getRegistrationNumber() + " - " +
                    student.getCourse() + " - " +
                    student.getEmail()
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
        System.out.println("Registration Number: " +
                student.getRegistrationNumber());
        System.out.println("Academic Programme: " + student.getCourse());
        System.out.println("Email: " + student.getEmail());

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

        System.out.println("\nAvailable Courses:");

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

        if (registrationNumber == null) return null;

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

        if (courseCode == null) return null;

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

        if (!checkDatabase()) return false;

        Student student = searchStudent(registrationNumber);

        if (student == null) {
            System.out.println("Student not found.");
            return false;
        }

        try (Connection connection = DatabaseUtil.getConnection()) {

            int programId = findProgramId(connection, newCourse);

            if (programId == -1) {
                System.out.println("Academic programme not found.");
                return false;
            }

            String[] names = splitName(newName);

            String sql =
                    "UPDATE students SET first_name = ?, " +
                    "last_name = ?, program_id = ? " +
                    "WHERE registration_number = ?";

            try (PreparedStatement statement =
                         connection.prepareStatement(sql)) {

                statement.setString(1, names[0]);
                statement.setString(2, names[1]);
                statement.setInt(3, programId);
                statement.setString(4, registrationNumber);

                statement.executeUpdate();
            }

            student.setName(newName);
            student.setCourse(newCourse);

            System.out.println("Student details updated successfully.");
            return true;

        } catch (SQLException | IllegalArgumentException e) {

            System.out.println("Update failed: " + e.getMessage());
            return false;
        }
    }

    // UPDATE COURSE DETAILS
    public boolean updateCourseDetails(
            String courseCode,
            String newName,
            int newCapacity) {

        if (!checkDatabase()) return false;

        Course course = searchCourse(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return false;
        }

        if (newName == null || newName.trim().isEmpty()) {
            System.out.println("Course name cannot be empty.");
            return false;
        }

        if (newCapacity <= 0 ||
                newCapacity < course.getRegisteredStudents().size()) {
            System.out.println("Invalid course capacity.");
            return false;
        }

        String sql =
                "UPDATE course SET title = ?, capacity = ? " +
                "WHERE course_code = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newName);
            statement.setInt(2, newCapacity);
            statement.setString(3, courseCode);

            statement.executeUpdate();

            course.setCourseName(newName);
            course.setCapacity(newCapacity);

            System.out.println("Course details updated successfully.");
            return true;

        } catch (SQLException e) {

            System.out.println("Course update failed: " + e.getMessage());
            return false;
        }
    }

    // DELETE STUDENT
    public boolean deleteStudent(String registrationNumber) {

        if (!checkDatabase()) return false;

        Student student = searchStudent(registrationNumber);

        if (student == null) {
            System.out.println("Student not found.");
            return false;
        }

        String sql =
                "DELETE FROM students WHERE registration_number = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, registrationNumber);

            if (statement.executeUpdate() == 0) {
                return false;
            }

            for (Course course : courses) {
                course.withdrawStudent(student);
            }

            students.remove(student);

            System.out.println("Student deleted successfully.");
            return true;

        } catch (SQLException e) {

            System.out.println("Student deletion failed: " + e.getMessage());
            return false;
        }
    }

    // DELETE COURSE
    public boolean deleteCourse(String courseCode) {

        if (!checkDatabase()) return false;

        Course course = searchCourse(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return false;
        }

        String sql =
                "DELETE FROM course WHERE course_code = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, courseCode);

            if (statement.executeUpdate() == 0) {
                return false;
            }

            courses.remove(course);

            System.out.println("Course deleted successfully.");
            return true;

        } catch (SQLException e) {

            System.out.println("Course deletion failed: " + e.getMessage());
            return false;
        }
    }

    // MYSQL REPLACES FILE-BASED SAVING
    public void saveToFile(String fileName) {
        System.out.println("Data is stored automatically in MySQL.");
    }
}
