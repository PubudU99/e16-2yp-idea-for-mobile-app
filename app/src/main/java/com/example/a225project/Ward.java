package com.example.a225project;

public class Ward {
    private String name;
    private String id;
    private String doctor;
    private String nurse;
    private String beds;


    public Ward(String name, String id, String doctor, String nurse, String beds) {
        this.name=name;
        this.id = id;
        this.doctor=doctor;
        this.nurse=nurse;
        this.beds=beds;

    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getNurse() {
        return nurse;
    }

    public String getBeds() {
        return beds;
    }
}
