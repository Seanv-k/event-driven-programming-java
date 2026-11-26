package com.university;

import org.junit.jupiter.api.Test;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    @Test
    void testMySQLConnection() throws Exception {

        try (Connection connection = DatabaseUtil.getConnection()) {

            assertNotNull(connection);
            assertTrue(connection.isValid(2));

            System.out.println(
                    "SUCCESS: Java connected to MySQL!"
            );
        }
    }
}
