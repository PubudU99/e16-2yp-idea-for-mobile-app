package com.example.a225project;

public class Patient {
    private String name;
    private String adminID;

    public String getAdminID() {
        return adminID;
    }

    private String nurse;

    public Patient() {
        // Required empty constructor for Firebase
    }

    public Patient(String name, String adminID, String nurse) {
        this.name = name;
        this.adminID = adminID;
        this.nurse = nurse;
    }

    public String getName() {
        return name;
    }


    public String getNurse() {
        return nurse;
    }
}
