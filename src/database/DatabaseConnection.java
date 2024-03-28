/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Gytis
 * 
 * Handles database connections and initialization for the Course Management System.
 * Ensures that the necessary database and tables are created if they do not exist.
 */
public class DatabaseConnection {
    
    // Constants for the database connection details
    private static final String ROOT_URL = "jdbc:mysql://localhost"; // URL for MySQL server
    private static final String DB_URL = "jdbc:mysql://localhost/cms_db"; // URL for the specific database
    private static final String USER = "pooa2024"; // Database username
    private static final String PASSWORD = "pooa2024"; // Database password
    
    // Static initializer to set up the database on class loading
    static {
        initializeDatabase();
    }
    
    /**
     * Provides a connection to the database.
     * 
     * @return A Connection object to the database.
     * @throws SQLException if a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    /**
     * Initializes the database and tables required by the application.
     * Creates the database and users table if they do not exist.
     */
    private static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(ROOT_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            // Create the database if it doesn't exist
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS cms_db");
            
            // Connect to the newly created or existing database
            try (Connection dbConn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                 Statement dbStmt = dbConn.createStatement()) {
                
                // SQL statement to create the users table if it doesn't exist
                String sqlCreateUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                        "user_id INT AUTO_INCREMENT PRIMARY KEY," +
                        "username VARCHAR(50) NOT NULL UNIQUE," +
                        "password VARCHAR(50) NOT NULL," +
                        "role VARCHAR(30) NOT NULL)";
                
                // Execute the statement to create the table
                dbStmt.executeUpdate(sqlCreateUsersTable);
            }

        } catch (SQLException e) {
            // Log any SQL exceptions that occur during database initialization
            System.out.println("Database initialization error: " + e.getMessage());
        }
    }
}