/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Lecturer;
import database.DatabaseConnection;

/**
 *
 * @author Gytis
 *
 * Data Access Object (DAO) class for handling database operations related to lecturers.
 * Provides methods to retrieve all lecturers from the database and fetch a specific lecturer by their ID.
 */
public class LecturerDAO {
    
    /**
     * Retrieves a list of all lecturers from the database.
     * 
     * @return A List of Lecturer objects, each representing a lecturer found in the database.
     */
    public List<Lecturer> getAllLecturers() {
        // Initialize an empty list to store lecturer objects
        List<Lecturer> lecturers = new ArrayList<>();
        // SQL query to select all lecturers from the Lecturers table
        String sql = "SELECT * FROM Lecturers";
        
        // Using try-with-resources to ensure proper resource management
        try (Connection conn = DatabaseConnection.getConnection(); // Obtain database connection
             PreparedStatement pstmt = conn.prepareStatement(sql); // Prepare the SQL statement
             ResultSet rs = pstmt.executeQuery()) { // Execute the query and get the result set
             
            // Iterate through the result set and add Lecturer objects to the list
            while (rs.next()) {
                lecturers.add(new Lecturer(
                    rs.getInt("Lecturer_ID"),
                    rs.getString("Name"),
                    rs.getString("Email"),
                    rs.getString("Role"),
                    rs.getString("Teaching_Types"))); // Construct Lecturer object and add it to the list
            }
        } 
        catch (SQLException e) {
            // Print an error message if a SQLException occurs
            System.out.println("Error fetching lecturers: " + e.getMessage());
        }        
        return lecturers;
    }

    /**
     * Retrieves a specific lecturer by their ID from the database.
     * 
     * @param lecturerId The ID of the lecturer to retrieve.
     * @return A Lecturer object representing the lecturer found, or null if no lecturer is found with the given ID.
     */
    public Lecturer getLecturerById(int lecturerId) {
        // SQL query to select a lecturer by their ID
        String sql = "SELECT * FROM Lecturers WHERE Lecturer_ID = ?";
        Lecturer lecturer = null; // Initialize lecturer as null; will be assigned a value if a lecturer is found
        
        // Using try-with-resources for resource management
        try (Connection conn = DatabaseConnection.getConnection(); // Obtain database connection
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepare the SQL statement
            pstmt.setInt(1, lecturerId); // Set the lecturer ID parameter
            try (ResultSet rs = pstmt.executeQuery()) { // Execute the query and get the result set
                // Check if a lecturer was found and create a Lecturer object
                if (rs.next()) {
                    lecturer = new Lecturer(
                        rs.getInt("Lecturer_ID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Role"),
                        rs.getString("Teaching_Types"));
                }
            }
        } catch (SQLException e) {
            // Print an error message if a SQLException occurs
            System.out.println("Error fetching lecturer by ID: " + e.getMessage());
        }
        return lecturer;
    }
}