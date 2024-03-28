/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Gytis
 *
 * Represents a user with properties such as user ID, username, password, and role.
 * This model class is used to store and manipulate user data throughout the application.
 * The role attribute defines the user's role within the system (e.g., Admin, Office, Lecturer).
 */
public class User {
    
    // Properties of the User class
    private int userId; // Unique identifier for the user
    private String username; // Username of the user
    private String password; // Password of the user
    private String role; // Role of the user (e.g., Admin, Office, Lecturer)

    /**
     * Constructor to initialize a User object with all its properties.
     *
     * @param userId Unique identifier for the user.
     * @param username Username of the user.
     * @param password Password of the user.
     * @param role Role of the user within the system.
     */
    public User(int userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters for each property of the User class

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    // Note: Password should ideally be hashed for security in a real-life application
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}