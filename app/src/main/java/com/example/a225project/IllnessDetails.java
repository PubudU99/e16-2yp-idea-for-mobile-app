package com.example.a225project;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class IllnessDetails {
    private String PatientId;
    private String DocId;
    private String illness;
    private LocalDateTime date;
    private String notes;

    public IllnessDetails(String patientID, String docId, String illness, LocalDateTime date, String notes) {
        this.PatientId = patientID;
        this.DocId = docId;
        this.illness = illness;
        this.date = date;
        this.notes = notes;
    }
    public String getPatientID() {
        return PatientId;
    }

    public String getDocId() {
        return DocId;
    }

    public String getIllness() {
        return illness;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }


}

