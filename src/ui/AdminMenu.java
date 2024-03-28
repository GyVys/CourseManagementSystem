/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import java.util.List;
import java.util.Scanner;
import models.User;
import services.AuthenticationService;
import services.UserManager;

/**
 *
 * @author Gytis
 *
 * Provides an interactive command-line menu for admin users, offering functionalities such as 
 * adding, modifying, and deleting users, changing own credentials, and printing a list of all users.
 */
public class AdminMenu {
    
    private AuthenticationService authService;
    private UserManager userManager;
    private Scanner input;

    /**
     * Constructs an AdminMenu with dependencies on UserManager, Scanner, and AuthenticationService.
     *
     * @param userManager The UserManager service for user management operations.
     * @param input The Scanner object for reading user input from the console.
     * @param authService The AuthenticationService for accessing authentication-related functionalities.
     */
    public AdminMenu(UserManager userManager, Scanner input, AuthenticationService authService) {
        this.userManager = userManager;
        this.input = input;
        this.authService = authService;
    }

    /**
     * Displays the admin menu and handles user interactions.
     *
     * @param user The User object representing the currently logged-in admin.
     */
    public void display(User user) {
        boolean exit = false;
        
        while(!exit){
            // Print the menu options
            System.out.println("\nWelcome " + user.getUsername() + "!\n");
            System.out.println("Admin Panel");
            System.out.println("\n1. Add User");
            System.out.println("2. Modify User");
            System.out.println("3. Delete User");
            System.out.println("4. Change Own Username/Password");
            System.out.println("5. Print All Users");
            System.out.println("0. Logout");

            System.out.print("Enter your choice: ");
            int choice = input.nextInt(); 
            input.nextLine(); // Consume newline
            
            // Handle the user's menu choice
            switch (choice) {
                case 1: 
                    addUser(); 
                    break;
                case 2: 
                    modifyUser(); 
                    break;
                case 3: 
                    deleteUser(); 
                    break;
                case 4: 
                    modifySelf(); 
                    break;
                case 5: 
                    printAllUsers(); 
                    break;
                case 0: 
                    System.out.println("Good bye..."); 
                    exit = true; 
                    break; // Exit the admin menu loop
                default: 
                    System.out.println("Invalid option selected."); 
                    break;
            }
        }
    }

    /**
     * Handles the addition of a new user to the system.
     */
    private void addUser() {
        // Gather user details
        System.out.println("Adding new user...\n");
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();
        System.out.print("Enter role (Admin/Office/Lecturer): ");
        String role = input.nextLine();

        // Attempt to add the user
        User user = userManager.addUser(username, password, role);
        if (user != null) {
            System.out.println("User added successfully.");
        } else {
            System.out.println("Failed to add user.");
        }
    }
    
    /**
     * Handles modifications to an existing user's information.
     */
    private void modifyUser() {
        // Gather information for modifying the user
        System.out.print("Enter user ID to modify: ");
        int userId = input.nextInt(); 
        input.nextLine(); // Consume newline
        System.out.print("Enter new username: ");
        String newUsername = input.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = input.nextLine();
        System.out.print("Enter new role (Admin/Office/Lecturer): ");
        String newRole = input.nextLine();

        // Attempt to update the user
        boolean success = userManager.updateUserByAdmin(userId, newUsername, newPassword, newRole);
        if (success) {
            System.out.println("User modified successfully.");
        } else {
            System.out.println("Failed to modify user.");
        }
    }
    
    /**
     * Handles the deletion of a user from the system.
     */
    private void deleteUser() {
        
        System.out.print("Enter user ID to delete: ");
        int userId = input.nextInt();

        // Attempt to delete the user
        boolean success = userManager.deleteUser(userId);
        if (success) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("Failed to delete user.");
        }
    }
    
    /**
     * Allows the currently logged-in user to change their own username and password.
     */
    private void modifySelf() {
        
        System.out.print("Change Your Username and Password: \n");
        int userId = authService.getCurrentUser().getUserId();
        System.out.print("Enter new username: ");
        String newUsername = input.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = input.nextLine();

        // Attempt to update the user's credentials
        boolean success = userManager.updateUserCredentials(userId, newUsername, newPassword);
        if (success) {
            System.out.println("Username and password updated successfully.");
        } else {
            System.out.println("Failed to update username and password.");
        }
    }
     
    /**
     * Prints a list of all users in the system, displaying their IDs, usernames, and roles.
     */
    private void printAllUsers() {
        
        List<User> users = userManager.fetchAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("\nList of Users:");
            System.out.printf("%-10s %-15s %s\n", "User ID", "Username", "Role");
            for (User user : users) {
                System.out.printf("%-10d %-15s %s\n", user.getUserId(), user.getUsername(), user.getRole());
            }
        }
    }
}