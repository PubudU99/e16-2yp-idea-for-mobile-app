package com.example.a225project;

public class UserCaregiver{

    private String name;
    private String address;
    private String phoneNumber;
    private String NIC;
    private String birthDate;
    private String email;
    private String adminID;

    private String patientID;


    public UserCaregiver(){
    }

    public UserCaregiver(String name, String address, String phoneNumber, String NIC,String birthDate,String email, String adminID, String patientID){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.NIC = NIC;
        this.birthDate = birthDate;
        this.email = email;
        this.adminID = adminID;
        this.patientID = patientID;


    }

    public String getAssignedWard() {
        return patientID;
    }

    public void setAssignedWard(String assignedWard) {
        this.patientID = assignedWard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }



}
