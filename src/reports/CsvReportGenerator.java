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
 * Implementation of the ReportGenerator interface for generating reports in CSV format.
 * This class is responsible for writing the report's content to a CSV file, with each line of the report
 * written as a single line in the file. The name of the report is used as the filename.
 */
public class CsvReportGenerator implements ReportGenerator {
    
    /**
     * Generates a report in CSV format and writes it to a file.
     *
     * @param reportLines The lines of text comprising the body of the report.
     * @param reportName The name of the report being generated. This name is used as the filename.
     */
    @Override
    public void generateReport(List<String> reportLines, String reportName) {
        // Try-with-resources statement to ensure the BufferedWriter is closed properly
        try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter(reportName + ".csv"))) {
            for (String line : reportLines) {
                csvWriter.write(line); // Write each line of the report to the CSV file
                csvWriter.newLine(); // Move to the next line in the file
            }
            // Confirmation message upon successful creation of the CSV file
            System.out.println("CSV report successfully created!");
        } 
        catch (IOException e) {
            // Error handling in case of problems writing to the file
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}