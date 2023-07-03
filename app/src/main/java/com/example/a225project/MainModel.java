package com.example.a225project;

public class MainModel {
    private String adminID;
    private String name;

    public MainModel() {
        // Default constructor required for Firebase
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public MainModel(String adminID, String name) {
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
