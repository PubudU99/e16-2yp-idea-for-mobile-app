package com.example.a225project;

public class UserPatient {
    private String name, address, phoneNumber, NIC, birthDate, email, adminID, heartrate, pressure, lungs,temperature, image, caregiverID,nurse,wardID,bedID,date;

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

    public String getHeartrate() {
        return heartrate;
    }

    public void setHeartrate(String heartrate) {
        this.heartrate = heartrate;
    }

    public String getPressure() {
        return pressure;
    }

    public String getCaregiverID() {
        return caregiverID;
    }

    public void setCaregiverID(String caregiverID) {
        this.caregiverID = caregiverID;
    }

    public String getNurse() {
        return nurse;
    }

    public void setNurse(String nurse) {
        this.nurse = nurse;
    }

    public String getWardID() {
        return wardID;
    }

    public void setWardID(String wardID) {
        this.wardID = wardID;
    }

    public String getBedID() {
        return bedID;
    }

    public void setBedID(String bedID) {
        this.bedID = bedID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getLungs() {
        return lungs;
    }

    public void setLungs(String lungs) {
        this.lungs = lungs;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UserPatient(){
    }

    public UserPatient(String name, String address, String phoneNumber, String NIC,String birthDate,String email, String adminID,String heartrate, String pressure,String lungs,String temperature, String image,String caregiverID,String nurse, String wardID, String bedID, String date){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.NIC = NIC;
        this.birthDate = birthDate;
        this.email = email;
        this.adminID = adminID;
        this.heartrate = heartrate;
        this.lungs = lungs;
        this.temperature = temperature;
        this.image = image;
        this.pressure = pressure;
        this.caregiverID = caregiverID;
        this.nurse = nurse;
        this.wardID = wardID;
        this.bedID = bedID;
        this.date = date;


    }


}
