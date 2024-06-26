package com.mycompany.healthapp.models;

import java.io.Serializable;

public class Appointments implements Serializable {
    private String appointmentId;
    private String doctorId;
    private String patientId;
    private String doctorName;

    private String patientName;
    private String dateTime;

    public Appointments(){}
    public Appointments(String appointmentId,String doctorId, String patientId, String doctorName, String patientName, String dateTime) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.dateTime = dateTime;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }


    @Override
    public String toString() {
        return "Appointments{" +
                "appointmentId='"+appointmentId + '\'' +
                "doctorId='" + doctorId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", patientName='" + patientName + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
