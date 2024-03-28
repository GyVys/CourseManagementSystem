/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import models.User;

/**
 *
 * @author Gytis
 *
 * Provides authentication services for the application, including user login handling.
 * It utilizes the UserManager to verify user credentials and to fetch user details.
 */
public class AuthenticationService {
    
    private User currentUser; // Holds a reference to the currently logged-in user

    // Reference to UserManager for accessing user operations and verification
    private UserManager userManager = new UserManager();

    /**
     * Attempts to log in a user with the provided username and password.
     * 
     * @param username The username provided by the user attempting to log in.
     * @param password The password provided by the user attempting to log in.
     * @return true if the login is successful (i.e., if the provided credentials match an existing user),
     *         false otherwise.
     */
    public boolean login(String username, String password) {
        
        // Attempt to fetch the user by their username
        User user = userManager.fetchUserByUsername(username);
        
        // Check if the user exists and the provided password matches the user's password
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user; // If match is found, set currentUser and indicate success
            return true;
        }
        return false; // If no matching user is found, or if the password doesn't match, login fails
    }

    /**
     * Retrieves the currently logged-in user.
     * 
     * @return The User object representing the currently logged-in user, or null if no user is logged in.
     */
    public User getCurrentUser() {
        return currentUser;
    }
}
