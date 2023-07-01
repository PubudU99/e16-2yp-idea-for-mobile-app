package com.example.a225project;

public class DoctorModel {


    private String adminID;
    private String name;

    public DoctorModel() {
        // Default constructor required for Firebase
    }

    public DoctorModel(String adminID, String name) {
        this.adminID = adminID;
        this.name = name;
    }


    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
