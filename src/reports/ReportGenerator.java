/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package reports;

import java.util.List;

/**
 *
 * @author Gytis
 *
 * Interface defining the contract for report generation implementations within the application.
 * Implementing classes are required to provide their own implementations of the generateReport method,
 * which is responsible for generating a report based on provided content lines and a report name.
 */
public interface ReportGenerator {
    
    /**
     * Generates a report based on the provided list of content lines and the specified report name.
     * Implementations of this method handles the creation and formatting of the report in a specific format
     * (e.g., console output, CSV file, etc.) as determined by the implementing class.
     *
     * @param reportLines A List of Strings, each representing a line of content to be included in the report.
     * @param reportName A String specifying the name of the report.
     */
    void generateReport(List<String> reportLines, String reportName);
}
