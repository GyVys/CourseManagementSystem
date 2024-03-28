/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Gytis
 *
 * Represents a lecturer with properties such as lecturer ID, name, email, role, and teaching types.
 * This model class is used to store and manipulate lecturer data throughout the application.
 */
public class Lecturer {
    
    // Properties of the Lecturer class
    private int lecturerId; // Unique identifier for the lecturer
    private String name; // Name of the lecturer
    private String email; // Email address of the lecturer
    private String role; // Role of the lecturer within the institution
    private String teachingTypes; // Types of teaching the lecturer is involved in (e.g., "lecture", "tutorial")
    
    /**
     * Constructor to initialize a Lecturer object with all its properties.
     *
     * @param lecturerId Unique identifier for the lecturer.
     * @param name Name of the lecturer.
     * @param email Email address of the lecturer.
     * @param role Role of the lecturer within the institution.
     * @param teachingTypes Types of teaching the lecturer is involved in.
     */
    public Lecturer(int lecturerId, String name, String email, String role, String teachingTypes) {
        this.lecturerId = lecturerId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.teachingTypes = teachingTypes;
    }
    
    // Getters and Setters for each property of the Lecturer class

    public int getLecturerId() {
        return lecturerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getTeachingTypes() {
        return teachingTypes;
    }
    
    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setTeachingTypes(String teachingTypes) {
        this.teachingTypes = teachingTypes;
    }    
}