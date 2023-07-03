package com.example.a225project;

public class admin_ViewAllPatients_Model {
    String PatientName;
    String PatientID;

    public admin_ViewAllPatients_Model(String patientName, String patientID) {
        this.PatientName = patientName;
        this.PatientID = patientID;
    }

    public String getPatientName() {
        return PatientName;
    }

    public String getPatientID() {
        return PatientID;
    }
}
