package com.example.a225project;

public class TodayAppointmentsModel {
    private String Appointment_type;
    private String Appointment_time;
    private String Doctor_name;
    private String Doctor_title;
    int Doctor_profilePic;

    public TodayAppointmentsModel(String appointment_type, String appointment_time, String doctor_name, String doctor_title, int doctor_profilePic) {
        Appointment_type = appointment_type;
        Appointment_time = appointment_time;
        Doctor_name = doctor_name;
        Doctor_title = doctor_title;
        Doctor_profilePic = doctor_profilePic;
    }

    public String getAppointment_type() {
        return Appointment_type;
    }

    public String getAppointment_time() {
        return Appointment_time;
    }

    public String getDoctor_name() {
        return Doctor_name;
    }

    public String getDoctor_title() {
        return Doctor_title;
    }

    public int getDoctor_profilePic() {
        return Doctor_profilePic;
    }
}
