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
import models.Course;
import database.DatabaseConnection;

/**
 *
 * @author Gytis
 *
 * Data Access Object (DAO) class for handling database operations related to courses.
 * It provides methods to retrieve all courses from the database and fetch a single course by its ID.
 */
public class CourseDAO {
    
    /**
     * Retrieves all courses from the database.
     * 
     * @return A list of Course objects representing all courses found in the database.
     */
    public List<Course> getAllCourses() {
        // List to hold Course objects
        List<Course> courses = new ArrayList<>();
        // SQL query to select all courses from the Courses table
        String sql = "SELECT * FROM Courses";
        
        // Try-with-resources to automatically close Connection, PreparedStatement, and ResultSet
        try (Connection conn = DatabaseConnection.getConnection(); // Obtain database connection
             PreparedStatement pstmt = conn.prepareStatement(sql); // Create PreparedStatement
             ResultSet rs = pstmt.executeQuery()) { // Execute query and get result set
             
            // Iterate over the ResultSet to create Course objects and add them to the list
            while (rs.next()) {
                courses.add(new Course(
                    rs.getInt("Course_ID"),
                    rs.getString("Course_Name"),
                    rs.getString("Description"),
                    rs.getString("Course_Type"),
                    rs.getInt("QQI_Level")));
            }
        } 
        catch (SQLException e) {
            // Handle SQL exceptions by printing error message
            System.out.println("Error fetching courses: " + e.getMessage());
        }        
        return courses;
    }

    /**
     * Retrieves a specific course by its ID from the database.
     * 
     * @param courseId The ID of the course to retrieve.
     * @return A Course object representing the course found, or null if no course is found with the given ID.
     */
    public Course getCourseById(int courseId) {
        // SQL query to select a course by its ID
        String sql = "SELECT * FROM Courses WHERE Course_ID = ?";
        // Initialize course as null; will be assigned a value if a course is found
        Course course = null;
        
        // Try-with-resources to automatically close Connection and PreparedStatement
        try (Connection conn = DatabaseConnection.getConnection(); // Obtain database connection
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Create PreparedStatement
            pstmt.setInt(1, courseId); // Set the course ID parameter in the SQL query
            try (ResultSet rs = pstmt.executeQuery()) { // Execute query and get result set
                // Check if a course was found and create a Course object
                if (rs.next()) {
                    course = new Course(
                        rs.getInt("Course_ID"),
                        rs.getString("Course_Name"),
                        rs.getString("Description"),
                        rs.getString("Course_Type"),
                        rs.getInt("QQI_Level"));
                }
            }
        } 
        catch (SQLException e) {
            // Handle SQL exceptions by printing error message
            System.out.println("Error fetching course by ID: " + e.getMessage());
        }
        return course;
    }
}