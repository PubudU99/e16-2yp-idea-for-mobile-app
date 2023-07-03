package com.example.a225project;

public class MedicineModel {
    private String prescription_id;

    private String medicine;
    private String weight;
    private String dose;
    private String days;
    private String perday;

    public MedicineModel(String patient_id,  String medicine, String weight, String dose, String days, String perday) {
        this.prescription_id = patient_id;
        this.medicine = medicine;
        this.weight = weight;
        this.dose = dose;
        this.days = days;
        this.perday = perday;
    }

    public String getPrescription_id() {
        return prescription_id;
    }

    public String getMedicine() {
        return medicine;
    }

    public String getWeight() {
        return weight;
    }

    public String getDose() {
        return dose;
    }

    public String getDays() {
        return days;
    }

    public String getPerday() {
        return perday;
    }
}
