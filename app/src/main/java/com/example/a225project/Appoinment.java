package com.example.a225project;

public class Appoinment{
    private String adminID;
    private String doctorID;
    private String recommendedDoctor;
    private String date;
    private String time;
    private String notes;

    // Default constructor
    public Appoinment() {
    }

    // Constructor with parameters
    public Appoinment(String adminID, String doctorID, String recommendedDoctor, String date, String time, String notes) {
        this.adminID = adminID;
        this.doctorID = doctorID;
        this.recommendedDoctor = recommendedDoctor;
        this.date = date;
        this.time = time;
        this.notes = notes;
    }

    // Getter and Setter methods for adminID
    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    // Getter and Setter methods for doctorID
    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    // Getter and Setter methods for recommendedDoctor
    public String getRecommendedDoctor() {
        return recommendedDoctor;
    }

    public void setRecommendedDoctor(String recommendedDoctor) {
        this.recommendedDoctor = recommendedDoctor;
    }

    // Getter and Setter methods for date
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Getter and Setter methods for time
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    // Getter and Setter methods for notes
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
