/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Date;

/**
 *
 * @author Gytis
 *
 * Represents feedback with various properties such as feedback ID, course ID, student ID, rating, comment, and date.
 * This model class is used to store and manipulate feedback data throughout the application.
 */
public class Feedback {
    
    // Properties of the Feedback class
    private int feedbackId; // Unique identifier for the feedback
    private int courseId; // Identifier for the course associated with this feedback
    private int studentId; // Identifier for the student who provided this feedback
    private int rating; // Numeric rating given as part of the feedback
    private String comment; // Textual comment given as part of the feedback
    private Date date; // Date the feedback was provided
    
    /**
     * Constructor to initialize a Feedback object with all its properties.
     *
     * @param feedbackId Unique identifier for the feedback.
     * @param courseId Identifier for the course associated with this feedback.
     * @param studentId Identifier for the student who provided this feedback.
     * @param rating Numeric rating given as part of the feedback.
     * @param comment Textual comment given as part of the feedback.
     * @param date Date the feedback was provided.
     */
    public Feedback(int feedbackId, int courseId, int studentId, int rating, String comment, Date date) {
        this.feedbackId = feedbackId;
        this.courseId = courseId;
        this.studentId = studentId;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }
    
    // Getters and Setters for each property of the Feedback class

    public int getFeedbackId() {
        return feedbackId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }
    
    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(Date date) {
        this.date = date;
    }    
}