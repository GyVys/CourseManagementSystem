/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.User;
import database.DatabaseConnection;

/**
 *
 * @author Gytis
 *
 * Service class for managing user-related operations in the application.
 * It handles adding, updating, and deleting users, as well as fetching users from the database.
 */
public class UserManager {
    
    /**
     * Constructor for UserManager.
     * Ensures that an admin user exists in the system upon initialization.
     */
    public UserManager() {
        ensureAdminUser();
    }

    /**
     * Ensures that an admin user exists in the system. If not, adds an admin user.
     */
    public void ensureAdminUser() {
        // Check if an admin user exists; if not, create one with default credentials
        if (!doesUserExist("admin")) {
            addUser("admin", "java", "Admin");
        }
    }

    /**
     * Adds a new user to the system with the provided username, password, and role.
     * 
     * @param username The username for the new user.
     * @param password The password for the new user.
     * @param role The role of the new user (e.g., Admin, Office, Lecturer).
     * @return A User object representing the newly added user or null if the operation fails.
     */
    public User addUser(String username, String password, String role) {
        // SQL statement to insert a new user into the database
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // Return a new User object with the generated key (userId)
                        return new User(generatedKeys.getInt(1), username, password, role);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
        return null; // Return null if user creation failed
    }

    /**
     * Updates a user in the system identified by userId with new username, password, and role.
     * This method is typically used by admins to update other users.
     * 
     * @param userId The ID of the user to update.
     * @param newUsername The new username for the user.
     * @param newPassword The new password for the user.
     * @param newRole The new role for the user.
     * @return true if the update was successful; false otherwise.
     */
    public boolean updateUserByAdmin(int userId, String newUsername, String newPassword, String newRole) {
        return updateUser(userId, newUsername, newPassword, newRole);
    }

    /**
     * Updates the credentials (username and password) of a user identified by userId.
     * The user's role is not changed.
     * 
     * @param userId The ID of the user whose credentials are to be updated.
     * @param newUsername The new username for the user.
     * @param newPassword The new password for the user.
     * @return true if the update was successful; false otherwise.
     */
    public boolean updateUserCredentials(int userId, String newUsername, String newPassword) {
        User user = fetchUserById(userId);
        
        if (user != null) {
            // Use the existing role of the user for the update
            return updateUser(userId, newUsername, newPassword, user.getRole());
        }
        return false;
    }

    /**
     * Deletes a user from the system identified by userId.
     * 
     * @param userId The ID of the user to delete.
     * @return true if the deletion was successful; false otherwise.
     */
    public boolean deleteUser(int userId) {
        // SQL statement to delete a user from the database
        String sql = "DELETE FROM users WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0; // Return true if at least one row was affected
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks if a user exists in the database based on the provided username.
     *
     * @param username The username to check for existence.
     * @return true if the user exists, false otherwise.
     */
    private boolean doesUserExist(String username) {
        String sql = "SELECT user_id FROM users WHERE username = ?";
        try ( Connection conn = DatabaseConnection.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if there is at least one result, indicating user existence
        } catch (SQLException e) {
            System.out.println("Error checking user existence: " + e.getMessage());
            return false;
        }
    }

    /**
     * Fetches a user from the database by their user ID.
     *
     * @param userId The ID of the user to fetch.
     * @return A User object if found, null otherwise.
     */
    private User fetchUserById(int userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try ( Connection conn = DatabaseConnection.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("username"),
                        rs.getString("password"), rs.getString("role"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching user: " + e.getMessage());
        }
        return null;
    }

    /**
     * Fetches a user from the database by their username.
     *
     * @param username The username of the user to fetch.
     * @return A User object if found, null otherwise.
     */
    public User fetchUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try ( Connection conn = DatabaseConnection.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("username"),
                        rs.getString("password"), rs.getString("role"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching user by username: " + e.getMessage());
        }
        return null;
    }

    /**
     * Updates a user's information in the database.
     *
     * @param userId The ID of the user to update.
     * @param newUsername The new username for the user.
     * @param newPassword The new password for the user.
     * @param newRole The new role for the user.
     * @return true if the update was successful, false otherwise.
     */
    private boolean updateUser(int userId, String newUsername, String newPassword, String newRole) {
        String sql = "UPDATE users SET username = ?, password = ?, role = ? WHERE user_id = ?";
        try ( Connection conn = DatabaseConnection.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newUsername);
            pstmt.setString(2, newPassword);
            pstmt.setString(3, newRole);
            pstmt.setInt(4, userId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0; // Returns true if at least one row was updated
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
            return false;
        }
    }

    /**
     * Fetches all users from the database.
     *
     * @return A List of User objects representing all users.
     */
    public List<User> fetchAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, role FROM users";
        try ( Connection conn = DatabaseConnection.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql);  ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                // Password is intentionally not fetched for security reasons
                users.add(new User(rs.getInt("user_id"), rs.getString("username"), null, rs.getString("role")));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching users: " + e.getMessage());
        }
        return users;
    }
}