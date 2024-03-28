/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.*;
import java.util.ArrayList;
import java.util.List;
import models.*;
import reports.ConsoleReportGenerator;
import reports.CsvReportGenerator;
import reports.TxtReportGenerator;

/**
 *
 * @author Gytis
 *
 * Service class for generating different types of reports related to courses,
 * students, and lecturers. It uses DAO classes to fetch necessary data from the
 * database and report generator classes to output reports in various formats
 * (text, CSV, console).
 */
public class ReportService {

    // DAO instances for accessing data related to courses, modules, lecturers, students, enrolments, and grades
    private CourseDAO courseDAO = new CourseDAO();
    private ModuleDAO moduleDAO = new ModuleDAO();
    private LecturerDAO lecturerDAO = new LecturerDAO();
    private StudentDAO studentDAO = new StudentDAO();
    private EnrolmentDAO enrolmentDAO = new EnrolmentDAO();
    private GradeDAO gradeDAO = new GradeDAO();
    // Add FeedbackDAO if needed for report generation or other operations

    /**
     * Helper method to generate a report in the specified format with the given
     * content and filename prefix.
     *
     * @param reportLines List of strings, each representing a line in the
     * report.
     * @param format The format of the report (txt, csv, console).
     * @param filenamePrefix Prefix for the filename, used for reports.
     */
    private void generateReport(List<String> reportLines, String format, String filenamePrefix) {
        switch (format.toLowerCase()) {
            case "txt":
                new TxtReportGenerator().generateReport(reportLines, filenamePrefix + System.currentTimeMillis());
                break;
            case "csv":
                new CsvReportGenerator().generateReport(reportLines, filenamePrefix + System.currentTimeMillis());
                break;
            case "console":
                new ConsoleReportGenerator().generateReport(reportLines, filenamePrefix + System.currentTimeMillis());
                break;
            default:
                // Fallback to printing lines to console if format is not recognized
                reportLines.forEach(System.out::println);
                break;
        }
    }

    /**
     * Generates a course report including all courses, their modules, enrolled
     * students per module, and the lecturers.
     *
     * @param format The desired format of the report (e.g., "txt", "csv",
     * "console").
     */
    public void generateCourseReport(String format) {
        List<String> reportLines = new ArrayList<>();
        List<Course> courses = courseDAO.getAllCourses();
        for (Course course : courses) {
            reportLines.add("Course: " + course.getCourseName());
            List<Module> modules = moduleDAO.getModulesByCourseId(course.getCourseId());
            for (Module module : modules) {
                Lecturer lecturer = lecturerDAO.getLecturerById(module.getLecturerId());
                int enrolledStudents = enrolmentDAO.countEnrolmentsByModuleId(module.getModuleId());
                reportLines.add("\tModule: " + module.getModuleName()
                        + ", Enrolled Students: " + enrolledStudents
                        + ", Lecturer: " + lecturer.getName()
                        + ", Room: " + module.getRoom());
            }
        }
        generateReport(reportLines, format, "CourseReport_");
    }

    /**
     * Generates a report for a specific student including their course,
     * modules, and grades.
     *
     * @param studentId The ID of the student for whom the report is generated.
     * @param format The desired format of the report (e.g., "txt", "csv",
     * "console").
     */
    public void generateStudentReport(int studentId, String format) {
        List<String> reportLines = new ArrayList<>();
        Student student = studentDAO.getStudentById(studentId);
        if (student == null) {
            reportLines.add("Student not found.");
            generateReport(reportLines, format, "StudentReport_NotFound_");
            return;
        }
        Course course = courseDAO.getCourseById(student.getCourseId());
        String courseName = course == null ? "Course not found for student." : course.getCourseName();
        reportLines.add("Student: " + student.getName() + ", Student ID: " + student.getStudentId() + ", Course: " + courseName);

        List<Enrolment> enrolments = enrolmentDAO.getEnrolmentsByStudentId(studentId);
        for (Enrolment enrolment : enrolments) {
            Module module = moduleDAO.getModuleById(enrolment.getModuleId());
            String line = "Module: " + module.getModuleName() + ", Status: " + enrolment.getStatus();
            if ("completed".equals(enrolment.getStatus())) {
                Grade grade = gradeDAO.getGradeByStudentIdAndModuleId(studentId, module.getModuleId());
                line += ", Grade: " + (grade != null ? grade.getGrade() : "N/A");
            }
            reportLines.add(line);
        }
        generateReport(reportLines, format, "StudentReport_" + studentId + "_");
    }

    /**
     * Generates a report for a specific lecturer including their modules and
     * enrolled students per module.
     *
     * @param lecturerId The ID of the lecturer for whom the report is
     * generated.
     * @param format The desired format of the report (e.g., "txt", "csv",
     * "console").
     */
    public void generateLecturerReport(int lecturerId, String format) {
        List<String> reportLines = new ArrayList<>();
        Lecturer lecturer = lecturerDAO.getLecturerById(lecturerId);
        reportLines.add("Lecturer: " + lecturer.getName() + ", Role: " + lecturer.getRole() + ", Teaching Classes: " + lecturer.getTeachingTypes());
        List<Module> modules = moduleDAO.getModulesByLecturerId(lecturerId);
        for (Module module : modules) {
            int enrolledStudents = enrolmentDAO.countEnrolmentsByModuleId(module.getModuleId());
            reportLines.add("Module: " + module.getModuleName() + ", Enrolled Students: " + enrolledStudents);
        }
        generateReport(reportLines, format, "LecturerReport_" + lecturerId + "_");
    }

    /**
     * Generates a self-report for a lecturer, listing the modules they teach
     * and enrolled students per module.
     *
     * @param lecturerId The ID of the lecturer generating the self-report.
     * @param format The desired format of the report (e.g., "txt", "csv",
     * "console").
     */
    public void generateLecturerReportForSelf(int lecturerId, String format) {
        List<String> reportLines = new ArrayList<>();
        Lecturer lecturer = lecturerDAO.getLecturerById(lecturerId);
        if (lecturer != null) {
            reportLines.add("Lecturer Report for: " + lecturer.getName());
            reportLines.add("Role: " + lecturer.getRole());
            reportLines.add("Teaching Classes: " + lecturer.getTeachingTypes());

            List<Module> modules = moduleDAO.getModulesByLecturerId(lecturerId);
            for (Module module : modules) {
                int enrolledStudents = enrolmentDAO.countEnrolmentsByModuleId(module.getModuleId());
                reportLines.add("Module: " + module.getModuleName() + ", Enrolled Students: " + enrolledStudents + ", Room: " + module.getRoom());
            }
        }
        generateReport(reportLines, format, "LecturerSelfReport_" + lecturerId + "_");
    }
}
