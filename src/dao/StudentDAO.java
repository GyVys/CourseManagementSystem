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
import models.Student;
import database.DatabaseConnection;

/**
 *
 * @author Gytis
 *
 * Data Access Object (DAO) class for handling database operations related to students.
 * This class provides functionalities to retrieve all students from the database
 * and to fetch a specific student by their ID.
 */
public class StudentDAO {
    
    /**
     * Retrieves a list of all students from the database.
     * 
     * @return A List of Student objects, each representing a student found in the database.
     */
    public List<Student> getAllStudents() {
        // Initialize an empty list to store student objects
        List<Student> students = new ArrayList<>();
        // SQL query to select all students from the Students table
        String sql = "SELECT * FROM Students";
        
        // Using try-with-resources to ensure proper resource management
        try (Connection conn = DatabaseConnection.getConnection(); // Obtain database connection
             PreparedStatement pstmt = conn.prepareStatement(sql); // Prepare the SQL statement
             ResultSet rs = pstmt.executeQuery()) { // Execute the query and get the result set
             
            // Iterate through the result set and add Student objects to the list
            while (rs.next()) {
                students.add(new Student(
                    rs.getInt("Student_ID"),
                    rs.getString("Name"),
                    rs.getString("Email"),
                    rs.getInt("Course_ID"))); // Construct Student object and add it to the list
            }
        } 
        catch (SQLException e) {
            // Print an error message if a SQLException occurs
            System.out.println("Error fetching students: " + e.getMessage());
        }        
        return students;
    }

    /**
     * Retrieves a specific student by their ID from the database.
     * 
     * @param studentId The ID of the student to retrieve.
     * @return A Student object representing the student found, or null if no student is found with the given ID.
     */
    public Student getStudentById(int studentId) {
        // SQL query to select a specific student by their ID
        String sql = "SELECT * FROM Students WHERE Student_ID = ?";
        Student student = null; // Initialize student as null; will be assigned a value if a student is found
        
        // Using try-with-resources for resource management
        try (Connection conn = DatabaseConnection.getConnection(); // Obtain database connection
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepare the SQL statement
             
            pstmt.setInt(1, studentId); // Set the student ID parameter
            ResultSet rs = pstmt.executeQuery(); // Execute the query
            
            // Check if a student was found and create a Student object
            if (rs.next()) {
                student = new Student(
                        rs.getInt("Student_ID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getInt("Course_ID"));
            }
        } 
        catch (SQLException e) {
            // Print an error message if a SQLException occurs
            System.out.println("Error fetching student by ID: " + e.getMessage());
        }        
        return student;
    }
}