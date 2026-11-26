# UNIVERSITY MANAGEMENT SYSTEM
## Project Report

### 1. Introduction

The University Management System is a Java-based application designed to manage student information, academic programmes, courses and course registrations.

The system uses Java for its application logic and MySQL for storing information in a relational database. It provides a menu-driven interface through which users can perform common university administration tasks.

### 2. Project Objectives

The main objectives are:

- To register and manage student information.
- To maintain academic programme records.
- To create and manage university courses.
- To register students for available courses.
- To allow students to withdraw from courses.
- To search, update and delete student and course records.
- To store information persistently in a MySQL database.

### 3. Technologies Used

| Technology | Purpose |
|---|---|
| Java | Application development |
| MySQL | Database management |
| JDBC | Communication between Java and MySQL |
| Maven | Project building and dependency management |
| JUnit | Application testing |
| Visual Studio Code | Development environment |

### 4. System Functionality

The application provides the following menu options:

1. Add Student
2. Add Course
3. Register Student for Course
4. Withdraw Student from Course
5. Search Student
6. Search Course
7. Update Student Details
8. Update Course Details
9. Delete Student
10. Delete Course
11. Display All Students
12. Display All Courses
13. Display Course Registrations
14. Display Individual Student Details

The system collects student names, registration numbers, academic programmes and email addresses.

It supports two academic programmes:
- Bachelor of Computer Science
- Diploma in IT

### 5. Database Design

The application uses a MySQL database named `university_db`.

The main database tables are:

**Students**

Stores student information, including:
- Student ID
- Registration number
- First name
- Last name
- Email
- Programme ID

**Programs**

Stores academic programme information:
- Programme ID
- Programme name
- Required units

**Course**

Stores available university courses:
- Course code
- Course title
- Course capacity

**Course Registrations**

Stores the relationship between students and courses:
- Registration ID
- Student ID
- Course code

Primary keys and foreign keys are used to maintain relationships and data integrity. Unique constraints help prevent duplicate student registration numbers, email addresses and course registrations.

### 6. Java and MySQL Integration

Java connects to MySQL through JDBC.

The `DatabaseUtil` class manages the database connection. It retrieves the database password from an environment variable rather than storing the password directly in the source code.

The application communicates with MySQL to perform database operations. Information is stored automatically in the database, so it does not depend solely on the application's temporary memory.

### 7. Testing and Results

The following tests and checks were completed:

- Maven compilation completed successfully.
- The Java-to-MySQL connection test passed.
- The application launched successfully.
- Student registration and search were tested.
- The application reported successful database persistence.
- Maven reported `BUILD SUCCESS`.

These results demonstrate that the application can run and communicate with its MySQL database.

### 8. Conclusion

The University Management System demonstrates how Java programming and relational database management can be combined to develop a practical university administration application.

The project implements student management, course management, academic programme selection and course registration. The use of MySQL provides persistent data storage, while JDBC enables communication between the application and the database.

The project also demonstrates practical understanding of object-oriented programming, database relationships, exception handling, testing and software development using Maven.

Overall, the system provides a foundation for managing basic university academic records.