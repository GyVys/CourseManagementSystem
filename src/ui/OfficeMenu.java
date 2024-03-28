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
 * The OfficeMenu class provides an interactive command-line interface for users
 * to access various functionalities within the office context, such as generating
 * reports and changing user credentials.
 */
public class OfficeMenu {
    
    private AuthenticationService authService;
    private UserManager userManager;
    private ReportService reportService;
    private Scanner input;

    /**
     * Constructs an OfficeMenu with necessary service dependencies and input scanner.
     * 
     * @param reportService The service to handle report generation.
     * @param input The Scanner object for reading user input.
     * @param authService The service for authentication-related operations.
     * @param userManager The service for user management operations.
     */
    public OfficeMenu(ReportService reportService, Scanner input, AuthenticationService authService, UserManager userManager) {
        this.reportService = reportService;
        this.input = input;
        this.authService = authService;
        this.userManager = userManager;
    }

    /**
     * Displays the office menu to the user and handles the user's menu selections.
     * This method facilitates various functionalities including generating different types
     * of reports and changing the current user's username and password.
     * 
     * @param user The currently logged-in user.
     */
    public void display(User user) {
        
        boolean exit = false;
        
        while(!exit){
            // Display menu options
            System.out.println("\nWelcome " + user.getUsername() + "!\n");
            System.out.println("Office Panel");
            System.out.println("\n1. Generate Course Report");
            System.out.println("2. Generate Student Report");
            System.out.println("3. Generate Lecturer Report");
            System.out.println("4. Change Own Username/Password");
            System.out.println("0. Logout");
            // Get user choice
            System.out.print("Enter your choice: ");
            int choice = input.nextInt(); 
            input.nextLine(); // Consume newline
            
            // Handling user's choice
            switch (choice) {
                case 1:
                    // Report generation
                    System.out.println("Select report format: [1] Console, [2] TXT, [3] CSV");
                    int formatChoice = input.nextInt();
                    input.nextLine(); // Consume newline
                    String format = getFormatFromChoice(formatChoice);
                    // Course report
                    reportService.generateCourseReport(format);
                    break;
                case 2:
                    // Report generation
                    System.out.println("Select report format: [1] Console, [2] TXT, [3] CSV");
                    formatChoice = input.nextInt();
                    input.nextLine(); // Consume newline
                    format = getFormatFromChoice(formatChoice);
                    System.out.print("Enter student ID: ");
                    int studentID = input.nextInt();
                    // Student report
                    reportService.generateStudentReport(studentID, format);
                    break;
                case 3:
                    // Report generation
                    System.out.println("Select report format: [1] Console, [2] TXT, [3] CSV");
                    formatChoice = input.nextInt();
                    input.nextLine(); // Consume newline
                    format = getFormatFromChoice(formatChoice);
                    System.out.print("Enter lecturer ID: ");
                    int lecturerID = input.nextInt();
                    // Lecturer report
                    reportService.generateLecturerReport(lecturerID, format);
                    break;
                case 4:
                    // Modify self credentials
                    modifySelf();
                    break;
                case 0:
                    System.out.println("Good bye...");
                    exit = true; // Exit the office menu loop
                    break;
                default:
                    System.out.println("Invalid option selected.");
                    break;
            }
        }
    }

    /**
     * Allows the current user to change their username and password.
     * This method prompts the user for new credentials and updates them if valid.
     */
    private void modifySelf() {
        
        System.out.print("Change Your Username and Password: \n");
        int userId = authService.getCurrentUser().getUserId();
        // Prompting for new username and password
        System.out.print("Enter new username: ");
        String newUsername = input.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = input.nextLine();

        // Attempt to update the user credentials
        boolean success = userManager.updateUserCredentials(userId, newUsername, newPassword);  
        // Feedback to the user
        if (success) {
            System.out.println("Username and password updated successfully.");
        } 
        else {
            System.out.println("Failed to update username and password.");
        }
    }
    
    /**
     * Translates a numerical choice into a report format string.
     * 
     * @param choice The user's choice for the report format.
     * @return The string representing the selected format.
     */
    private String getFormatFromChoice(int choice) {
        
        switch (choice) {
            case 1: 
                return "console";
            case 2: 
                return "txt";
            case 3: 
                return "csv";
            default:
                System.out.println("Invalid format selected. Defaulting to console.");
                return "console";
        }
    }
}