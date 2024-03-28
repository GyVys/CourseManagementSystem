/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Gytis
 *
 * Represents a grade with properties such as grade ID, student ID, module ID, and the actual grade value.
 * This model class is used to store and manipulate grade data throughout the application.
 */
public class Grade {
    
    // Properties of the Grade class
    private int gradeId; // Unique identifier for the grade
    private int studentId; // Identifier for the student who received this grade
    private int moduleId; // Identifier for the module in which this grade was awarded
    private int grade; // Numeric value of the grade
    
    /**
     * Constructor to initialize a Grade object with all its properties.
     *
     * @param gradeId Unique identifier for the grade.
     * @param studentId Identifier for the student who received this grade.
     * @param moduleId Identifier for the module in which this grade was awarded.
     * @param grade Numeric value of the grade.
     */
    public Grade(int gradeId, int studentId, int moduleId, int grade) {
        this.gradeId = gradeId;
        this.studentId = studentId;
        this.moduleId = moduleId;
        this.grade = grade;
    }
    
    // Getters and Setters for each property of the Grade class

    public int getGradeId() {
        return gradeId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public int getGrade() {
        return grade;
    }
    
    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }    
}