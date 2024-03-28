/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reports;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Gytis
 *
 * Implementation of the ReportGenerator interface for generating reports in text format.
 * This class handles the creation of a text file and writes the content of the report to it,
 * line by line. The name of the report is used to name the text file.
 */
public class TxtReportGenerator implements ReportGenerator {
    
    /**
     * Generates a report in text format and writes it to a text file.
     *
     * @param reportLines The lines of text comprising the body of the report. Each string in this list
     *                    represents a single line in the report.
     * @param reportName The name of the report being generated. This name is used to create the text file,
     *                   appending ".txt" as the file extension.
     */
    @Override
    public void generateReport(List<String> reportLines, String reportName) {
        // Use BufferedWriter in try-catch
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportName + ".txt"))) {
            // Iterate over each line in the reportLines list and write it to the file
            for (String line : reportLines) {
                writer.write(line); // Write the line to the file
                writer.newLine(); // Move to the next line
            }
            // Confirmation message upon successful creation of the text file
            System.out.println("Text report successfully created!");
        } 
        catch (IOException e) {
            // Error handling in case of problems writing to the file
            System.out.println("Error writing to TXT file: " + e.getMessage());
        }
    }
}