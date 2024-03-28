/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Gytis
 *
 * Represents an academic module with properties such as module ID, course ID, module name, lecturer ID, and room.
 * This model class is used to store and manipulate module data throughout the application.
 */
public class Module {
    
    // Properties of the Module class
    private int moduleId; // Unique identifier for the module
    private int courseId; // Identifier for the course this module is associated with
    private String moduleName; // Name of the module
    private int lecturerId; // Identifier for the lecturer responsible for this module
    private String room; // Room where the module takes place

    /**
     * Constructor to initialize a Module object with all its properties.
     *
     * @param moduleId Unique identifier for the module.
     * @param courseId Identifier for the course this module is associated with.
     * @param moduleName Name of the module.
     * @param lecturerId Identifier for the lecturer responsible for this module.
     * @param room Room where the module takes place.
     */
    public Module(int moduleId, int courseId, String moduleName, int lecturerId, String room) {
        this.moduleId = moduleId;
        this.courseId = courseId;
        this.moduleName = moduleName;
        this.lecturerId = lecturerId;
        this.room = room;
    }
    
    // Getters and Setters for each property of the Module class

    public int getModuleId() {
        return moduleId;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public String getRoom() {
        return room;
    }
    
    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}