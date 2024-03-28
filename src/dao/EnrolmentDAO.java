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
import models.Enrolment;
import database.DatabaseConnection;

/**
 *
 * @author Gytis
 *
 * Data Access Object (DAO) class for handling database operations for enrolments.
 * This class provides functionalities to retrieve enrolment details and to count enrolments for modules.
 */
public class EnrolmentDAO {
    
    /**
     * Retrieves a list of Enrolment objects for a specific student by their ID.
     * 
     * @param studentId The ID of the student whose enrolments are to be fetched.
     * @return A List of Enrolment objects representing all enrolments found for the given student ID.
     */
    public List<Enrolment> getEnrolmentsByStudentId(int studentId) {
        // Initialize an empty list to store enrolment objects
        List<Enrolment> enrolments = new ArrayList<>();
        // SQL query to select enrolments based on a specific student ID
        String sql = "SELECT * FROM Enrolments WHERE Student_ID = ?";
        
        // Using try-with-resources to ensure proper resource management
        try (Connection conn = DatabaseConnection.getConnection(); // Obtain database connection
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepare the SQL statement
             
            pstmt.setInt(1, studentId); // Set the student ID parameter
            try (ResultSet rs = pstmt.executeQuery()) { // Execute the query
                // Iterate through the result set and add Enrolment objects to the list
                while (rs.next()) {
                    enrolments.add(new Enrolment(
                        rs.getInt("Enrolment_ID"),
                        studentId,
                        rs.getInt("Module_ID"),
                        rs.getString("Status")));
                }
            }
        } 
        catch (SQLException e) {
            // Print an error message if a SQLException occurs
            System.out.println("Error fetching enrolments by student ID: " + e.getMessage());
        }        
        return enrolments;
    }

    /**
     * Counts the number of enrolments for a specific module by its ID.
     * 
     * @param moduleId The ID of the module for which to count enrolments.
     * @return An integer representing the count of enrolments for the specified module ID.
     */
    public int countEnrolmentsByModuleId(int moduleId) {
        // SQL query to count enrolments for a given module ID
        String sql = "SELECT COUNT(*) AS enrolment_count FROM Enrolments WHERE Module_ID = ?";
        int count = 0; // Initialize the count to 0
        
        // Using try-with-resources for resource management
        try (Connection conn = DatabaseConnection.getConnection(); // Obtain database connection
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepare the SQL statement
             
            pstmt.setInt(1, moduleId); // Set the module ID parameter
            ResultSet rs = pstmt.executeQuery(); // Execute the query
            
            // If a result is found, retrieve the count from the result set
            if (rs.next()) {
                count = rs.getInt("enrolment_count");
            }
        } 
        catch (SQLException e) {
            // Print an error message if a SQLException occurs
            System.out.println("Error counting enrolments by module ID: " + e.getMessage());
        }        
        return count;
    }
}