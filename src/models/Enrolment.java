/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Gytis
 *
 * Represents an enrolment with properties such as enrolment ID, student ID, module ID, and status.
 * This model class is used to store and manipulate enrolment data throughout the application.
 */
public class Enrolment {
    
    // Properties of the Enrolment class
    private int enrolmentId; // Unique identifier for the enrolment
    private int studentId; // Identifier for the student associated with this enrolment
    private int moduleId; // Identifier for the module associated with this enrolment
    private String status; // Status of the enrolment (e.g., "enrolled", "completed")

    /**
     * Constructor to initialize an Enrolment object with all its properties.
     *
     * @param enrolmentId Unique identifier for the enrolment.
     * @param studentId Identifier for the student associated with this enrolment.
     * @param moduleId Identifier for the module associated with this enrolment.
     * @param status Status of the enrolment (e.g., "enrolled", "completed").
     */
    public Enrolment(int enrolmentId, int studentId, int moduleId, String status) {
        this.enrolmentId = enrolmentId;
        this.studentId = studentId;
        this.moduleId = moduleId;
        this.status = status;
    }

    // Getters and Setters for each property of the Enrolment class

    public int getEnrolmentId() {
        return enrolmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public String getStatus() {
        return status;
    }

    public void setEnrolmentId(int enrolmentId) {
        this.enrolmentId = enrolmentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}