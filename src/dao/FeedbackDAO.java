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
import models.Feedback;
import database.DatabaseConnection;

/**
 *
 * @author Gytis
 *
 * Data Access Object (DAO) class for handling database operations related to feedback.
 * Provides functionality to retrieve all feedback entries for a specific course by its ID.
 */
public class FeedbackDAO {
    
    /**
     * Retrieves a list of Feedback objects for a specific course identified by the course ID.
     * 
     * @param courseId The ID of the course for which feedback is being fetched.
     * @return A List of Feedback objects representing all feedback found for the given course ID.
     */
    public List<Feedback> getFeedbackByCourseId(int courseId) {
        // Initialize an empty list to store feedback objects
        List<Feedback> feedbacks = new ArrayList<>();
        // SQL query to select feedback entries based on a specific course ID
        String sql = "SELECT * FROM Feedback WHERE Course_ID = ?";
        
        // Using try-with-resources to ensure proper resource management
        try (Connection conn = DatabaseConnection.getConnection(); // Obtain database connection
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepare the SQL statement
             
            pstmt.setInt(1, courseId); // Set the course ID parameter
            try (ResultSet rs = pstmt.executeQuery()) { // Execute the query and get the result set
                // Iterate through the result set and add Feedback objects to the list
                while (rs.next()) {
                    feedbacks.add(new Feedback(
                        rs.getInt("Feedback_ID"),
                        courseId,
                        rs.getInt("Student_ID"),
                        rs.getInt("Rating"),
                        rs.getString("Comment"),
                        rs.getDate("Date"))); // Construct Feedback object and add it to the list
                }
            }
        } 
        catch (SQLException e) {
            // Print an error message if a SQLException occurs
            System.out.println("Error fetching feedback by course ID: " + e.getMessage());
        }        
        return feedbacks;
    }
}