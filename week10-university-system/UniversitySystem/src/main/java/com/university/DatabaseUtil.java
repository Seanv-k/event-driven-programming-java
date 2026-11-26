package com.university;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String URL =
            "jdbc:mysql://localhost:3306/university_db";

    private static final String USER = "root";

    public static Connection getConnection() throws SQLException {

        String password = System.getenv("DB_PASSWORD");

        if (password == null || password.isEmpty()) {
            throw new SQLException(
                    "Database password not configured. Set DB_PASSWORD first."
            );
        }

        return DriverManager.getConnection(URL, USER, password);
    }
}