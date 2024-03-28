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
import models.Grade;
import database.DatabaseConnection;

/**
 *
 * @author Gytis
 *
 * Data Access Object (DAO) class for handling database operations related to grades.
 * This class provides functionalities to retrieve grade details for students,
 * both as a list for all modules a student is enrolled in, and individually by module.
 */
public class GradeDAO {
    
    /**
     * Retrieves a list of Grade objects for a specific student by their ID.
     * 
     * @param studentId The ID of the student whose grades are to be fetched.
     * @return A List of Grade objects representing all grades found for the given student ID.
     */
    public List<Grade> getGradesByStudentId(int studentId) {
        // Initialize an empty list to store grade objects
        List<Grade> grades = new ArrayList<>();
        // SQL query to select all grades for a specific student ID
        String sql = "SELECT * FROM Grades WHERE Student_ID = ?";
        
        // Using try-with-resources to ensure proper resource management
        try (Connection conn = DatabaseConnection.getConnection(); // Obtain database connection
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepare the SQL statement
             
            pstmt.setInt(1, studentId); // Set the student ID parameter
            try (ResultSet rs = pstmt.executeQuery()) { // Execute the query and get the result set
                // Iterate through the result set and add Grade objects to the list
                while (rs.next()) {
                    grades.add(new Grade(
                        rs.getInt("Grade_ID"),
                        studentId,
                        rs.getInt("Module_ID"),
                        rs.getInt("Grade"))); // Construct Grade object and add it to the list
                }
            }
        } 
        catch (SQLException e) {
            // Print an error message if a SQLException occurs
            System.out.println("Error fetching grades by student ID: " + e.getMessage());
        }        
        return grades;
    }

    /**
     * Retrieves a specific Grade object based on a student ID and a module ID.
     * 
     * @param studentId The ID of the student.
     * @param moduleId The ID of the module.
     * @return A Grade object representing the grade found for the specified student and module,
     *         or null if no grade is found.
     */
    public Grade getGradeByStudentIdAndModuleId(int studentId, int moduleId) {
        // SQL query to select a specific grade by student ID and module ID
        String sql = "SELECT * FROM Grades WHERE Student_ID = ? AND Module_ID = ?";
        Grade grade = null; // Initialize grade as null; will be assigned a value if a grade is found
        
        // Using try-with-resources for resource management
        try (Connection conn = DatabaseConnection.getConnection(); // Obtain database connection
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepare the SQL statement
            pstmt.setInt(1, studentId); // Set the student ID parameter
            pstmt.setInt(2, moduleId); // Set the module ID parameter
            try (ResultSet rs = pstmt.executeQuery()) { // Execute the query and get the result set
                // Check if a grade was found and create a Grade object
                if (rs.next()) {
                    grade = new Grade(
                        rs.getInt("Grade_ID"),
                        studentId,
                        moduleId,
                        rs.getInt("Grade"));
                }
            }
        } catch (SQLException e) {
            // Print an error message if a SQLException occurs
            System.out.println("Error fetching grade: " + e.getMessage());
        }
        return grade;
    }
}