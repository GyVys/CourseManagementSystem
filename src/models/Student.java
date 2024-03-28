/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Gytis
 *
 * Represents a student with properties such as student ID, name, email, and course ID.
 * This model class is used to store and manipulate student data throughout the application.
 */
public class Student {
    
    // Properties of the Student class
    private int studentId; // Unique identifier for the student
    private String name; // Name of the student
    private String email; // Email address of the student
    private int courseId; // Identifier for the course the student is enrolled in

    /**
     * Constructor to initialize a Student object with all its properties.
     *
     * @param studentId Unique identifier for the student.
     * @param name Name of the student.
     * @param email Email address of the student.
     * @param courseId Identifier for the course the student is enrolled in.
     */
    public Student(int studentId, String name, String email, int courseId) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.courseId = courseId;
    }
    
    // Getters and Setters for each property of the Student class

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getCourseId() {
        return courseId;
    }
    
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }    
}