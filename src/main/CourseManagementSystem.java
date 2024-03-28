/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.Scanner;
import models.User;
import services.AuthenticationService;
import services.ReportService;
import services.UserManager;
import ui.AdminMenu;
import ui.LecturerMenu;
import ui.OfficeMenu;

/**
 *
 * @author Gytis
 * 
 * GitHub link:
 * 
 * 
 */

/**
 * Main class for the Course Management System application.
 * This class handles user authentication and directs users to the appropriate
 * menu based on their role (Admin, Office, or Lecturer).
 */
public class CourseManagementSystem {
    
    // Instance of AuthenticationService to handle login operations
    private static AuthenticationService authService = new AuthenticationService();
    // Instance of UserManager to manage user-related operations
    private static UserManager userManager = new UserManager();
    // Scanner object for reading input from the console
    private static Scanner input = new Scanner(System.in);
    
    /**
     * Main method - entry point of the application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        
        // Welcome message
        System.out.println("Welcome to the Course Management System");
        // Prompt for username
        System.out.print("Username: ");
        String username = input.nextLine();
        // Prompt for password
        System.out.print("Password: ");
        String password = input.nextLine();

        // Attempt to login with the provided credentials
        if (authService.login(username, password)) {
            // On successful login, show a welcome message and proceed to the role-specific menu
            System.out.println("\nLogin successful!");
            System.out.println("-------------------------------------");
            handleLoggedInUser(authService.getCurrentUser());
        } else {
            // If login fails, inform the user
            System.out.println("Login failed. Please check your credentials.");
        }
        // Close the Scanner object to free resources
        input.close();
    }
    
    /**
     * Directs the logged-in user to the appropriate menu based on their role.
     * @param user The user object representing the currently logged-in user.
     */
    private static void handleLoggedInUser(User user) {
        // Create a ReportService instance for report-related operations
        ReportService reportService = new ReportService();        
        // Determine the user's role and instantiate the corresponding menu class
        switch (user.getRole()) {
            case "Admin":
                // Admin users are directed to the AdminMenu
                new AdminMenu(userManager, input, authService).display(user);
                break;
            case "Office":
                // Office staff are directed to the OfficeMenu
                new OfficeMenu(reportService, input, authService, userManager).display(user);
                break;
            case "Lecturer":
                // Lecturers are directed to the LecturerMenu
                new LecturerMenu(reportService, input, authService, userManager).display(user);
                break;
            default:
                // If the role is not recognized, inform the user
                System.out.println("Invalid role.");
                break;
        }
    }    
}