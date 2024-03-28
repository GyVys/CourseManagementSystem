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
import models.Module;
import database.DatabaseConnection;

/**
 *
 * @author Gytis
 *
 * Data Access Object (DAO) class for handling database operations related to modules.
 * Provides methods to retrieve modules based on course ID, fetch a specific module by its ID,
 * and get all modules taught by a specific lecturer.
 */
public class ModuleDAO {

    /**
     * Retrieves a list of Module objects associated with a specific course, identified by course ID.
     * 
     * @param courseId The ID of the course for which modules are being fetched.
     * @return A List of Module objects representing all modules found for the given course ID.
     */
    public List<Module> getModulesByCourseId(int courseId) {
        // Initialize an empty list to store module objects
        List<Module> modules = new ArrayList<>();
        // SQL query to select all modules associated with a specific course ID
        String sql = "SELECT * FROM Modules WHERE Course_ID = ?";

        // Using try-with-resources to ensure proper resource management
        try (Connection conn = DatabaseConnection.getConnection();  // Obtain database connection
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepare the SQL statement

            pstmt.setInt(1, courseId); // Set the course ID parameter
            try (ResultSet rs = pstmt.executeQuery()) { // Execute the query and get the result set
                // Iterate through the result set and add Module objects to the list
                while (rs.next()) {
                    modules.add(new Module(
                        rs.getInt("Module_ID"),
                        courseId,
                        rs.getString("Module_Name"),
                        rs.getInt("Lecturer_ID"),
                        rs.getString("Room"))); // Construct Module object and add it to the list
                }
            }
        } 
        catch (SQLException e) {
            // Print an error message if a SQLException occurs
            System.out.println("Error fetching modules by course ID: " + e.getMessage());
        }
        return modules;
    }

    /**
     * Retrieves a specific module by its ID.
     * 
     * @param moduleId The ID of the module to retrieve.
     * @return A Module object representing the module found, or null if no module is found with the given ID.
     */
    public Module getModuleById(int moduleId) {
        // SQL query to select a specific module by its ID
        String sql = "SELECT * FROM Modules WHERE Module_ID = ?";
        Module module = null; // Initialize module as null; will be assigned a value if a module is found

        // Using try-with-resources for resource management
        try (Connection conn = DatabaseConnection.getConnection(); // Obtain database connection
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepare the SQL statement

            pstmt.setInt(1, moduleId); // Set the module ID parameter
            ResultSet rs = pstmt.executeQuery(); // Execute the query

            // Check if a module was found and create a Module object
            if (rs.next()) {
                module = new Module(
                        rs.getInt("Module_ID"),
                        rs.getInt("Course_ID"),
                        rs.getString("Module_Name"),
                        rs.getInt("Lecturer_ID"),
                        rs.getString("Room"));
            }
        } 
        catch (SQLException e) {
            // Print an error message if a SQLException occurs
            System.out.println("Error fetching module by ID: " + e.getMessage());
        }
        return module;
    }

    /**
     * Retrieves a list of Module objects taught by a specific lecturer, identified by lecturer ID.
     * 
     * @param lecturerId The ID of the lecturer for which modules are being fetched.
     * @return A List of Module objects representing all modules taught by the given lecturer.
     */
    public List<Module> getModulesByLecturerId(int lecturerId) {
        // Initialize an empty list to store module objects
        List<Module> modules = new ArrayList<>();
        // SQL query to select all modules taught by a specific lecturer ID
        String sql = "SELECT * FROM Modules WHERE Lecturer_ID = ?";

        // Using try-with-resources for resource management
        try (Connection conn = DatabaseConnection.getConnection(); // Obtain database connection
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepare the SQL statement

            pstmt.setInt(1, lecturerId); // Set the lecturer ID parameter
            ResultSet rs = pstmt.executeQuery(); // Execute the query

            // Iterate through the result set and add Module objects to the list
            while (rs.next()) {
                modules.add(new Module(
                        rs.getInt("Module_ID"),
                        rs.getInt("Course_ID"),
                        rs.getString("Module_Name"),
                        lecturerId,
                        rs.getString("Room"))); // Construct Module object and add it to the list
            }
        } 
        catch (SQLException e) {
            // Print an error message if a SQLException occurs
            System.out.println("Error fetching modules by lecturer ID: " + e.getMessage());
        }

        return modules;
    }
}