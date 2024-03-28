/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import java.util.Scanner;
import models.User;
import services.AuthenticationService;
import services.ReportService;
import services.UserManager;

/**
 *
 * @author Gytis
 *
 * A menu system for lecturers, offering functionalities to generate reports and update personal information.
 */
public class LecturerMenu {
    
    private AuthenticationService authService;
    private UserManager userManager;
    private ReportService reportService;
    private Scanner input;

    /**
     * Constructs a LecturerMenu with necessary services and input scanner.
     *
     * @param reportService The service for generating reports.
     * @param input A Scanner object for reading user input.
     * @param authService The authentication service for user authentication tasks.
     * @param userManager The user management service for user-related operations.
     */
    public LecturerMenu(ReportService reportService, Scanner input, AuthenticationService authService, UserManager userManager) {
        this.reportService = reportService;
        this.input = input;
        this.authService = authService;
        this.userManager = userManager;
    }

    /**
     * Displays the menu for lecturer users and handles their interactions.
     *
     * @param user The User object representing the currently logged-in lecturer.
     */
    public void display(User user) {
        boolean exit = false;
        
        while(!exit){
            // Display the menu options
            System.out.println("\nWelcome " + user.getUsername() + "!\n");
            System.out.println("Lecturer Panel");
            System.out.println("\n1. Generate My Lecturer Report");
            System.out.println("2. Change Own Username/Password");
            System.out.println("0. Logout");

            // Handle the user's choice
            System.out.print("Enter your choice: ");
            int choice = input.nextInt(); 
            input.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    // Report generation
                    System.out.println("Select report format: [1] Console, [2] TXT, [3] CSV");
                    int formatChoice = input.nextInt();
                    input.nextLine(); // Consume newline
                    String format = getFormatFromChoice(formatChoice);
                    reportService.generateLecturerReportForSelf(user.getUserId(), format);
                    break;
                case 2:
                    // Modify self credentials
                    modifySelf();
                    break;
                case 0:
                    // Exit the menu
                    System.out.println("Good bye...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option selected.");
                    break;
            }
        }
    }

    /**
     * Allows the currently logged-in lecturer to change their own username and password.
     */
    private void modifySelf() {
        System.out.print("Change Your Username and Password: \n");
        int userId = authService.getCurrentUser().getUserId();
        System.out.print("Enter new username: ");
        String newUsername = input.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = input.nextLine();

        // Attempt to update credentials
        boolean success = userManager.updateUserCredentials(userId, newUsername, newPassword);
        
        if (success) {
            System.out.println("Username and password updated successfully.");
        } else {
            System.out.println("Failed to update username and password.");
        }
    }
    
    /**
     * Determines the report format based on the user's choice.
     *
     * @param choice The user's choice of format.
     * @return The format as a string (e.g., "console", "txt", "csv").
     */
    private String getFormatFromChoice(int choice) {
        switch (choice) {
            case 1: return "console";
            case 2: return "txt";
            case 3: return "csv";
            default:
                System.out.println("Invalid format selected. Defaulting to console.");
                return "console";
        }
    }
}