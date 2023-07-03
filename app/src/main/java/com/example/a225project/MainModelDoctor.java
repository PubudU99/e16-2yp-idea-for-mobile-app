package com.example.a225project;


public class MainModelDoctor {
    private String adminID;
    private String name;

    public MainModelDoctor() {
        // Default constructor required for Firebase
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public MainModelDoctor(String adminID, String name) {
        this.adminID = adminID;
        this.name = name;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
