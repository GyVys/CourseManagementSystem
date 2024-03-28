/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Gytis
 *
 * Represents a course with properties such as course ID, name, description, type, and QQI level.
 * This model class is used to store and manipulate course data throughout the application.
 */
public class Course {
    
    // Properties of the Course class
    private int courseId; // Unique identifier for the course
    private String courseName; // Name of the course
    private String description; // Description of the course content
    private String courseType; // Type of the course: "in_class", "online", or "blended"
    private int qqiLevel; // QQI level of the course

    /**
     * Constructor to initialize a Course object with all its properties.
     *
     * @param courseId Unique identifier for the course.
     * @param courseName Name of the course.
     * @param description Description of the course content.
     * @param courseType Type of the course: "in_class", "online", or "blended".
     * @param qqiLevel QQI level of the course.
     */
    public Course(int courseId, String courseName, String description, String courseType, int qqiLevel) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.courseType = courseType;
        this.qqiLevel = qqiLevel;
    }

    // Getters and Setters for each property of the Course class

    public int getCourseId() { 
        return courseId; 
    }
    
    public String getCourseName() { 
        return courseName; 
    }
    
    public String getDescription() { 
        return description; 
    }
    
    public String getCourseType() { 
        return courseType; 
    }
    
    public int getQqiLevel() { 
        return qqiLevel; 
    }

    public void setCourseId(int courseId) { 
        this.courseId = courseId; 
    }
    
    public void setCourseName(String courseName) { 
        this.courseName = courseName; 
    }
    
    public void setDescription(String description) { 
        this.description = description; 
    }
    
    public void setCourseType(String courseType) { 
        this.courseType = courseType; 
    }
    
    public void setQqiLevel(int qqiLevel) { 
        this.qqiLevel = qqiLevel; 
    }
}