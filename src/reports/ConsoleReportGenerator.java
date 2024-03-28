/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reports;

import java.util.List;

/**
 *
 * @author Gytis
 *
 * Implementation of the ReportGenerator interface for generating reports that are printed to the console.
 * This class is responsible for outputting the report's name and its content lines directly to the system console.
 */
public class ConsoleReportGenerator implements ReportGenerator {
    
    /**
     * Generates and prints a report to the console.
     *
     * @param reportLines The lines of text comprising the body of the report.
     * @param reportName The name of the report being generated.
     */
    @Override
    public void generateReport(List<String> reportLines, String reportName) {
        
        // Print the report name to the console as a header
        System.out.println("Console Report: " + reportName);
        // Iterate through each line of the report content and print it to the console
        reportLines.forEach(System.out::println);
    }
}