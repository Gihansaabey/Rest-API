package com.mycompany.healthapp.models;

import java.io.Serializable;

public class MedicalRecords implements Serializable {
    private String medicalRecordId;
    private String patientId;
    private String doctorId;
    private String patientName;
    private String doctorName;
    private String dateTime;
    private String details;
    
    public MedicalRecords(){}
    
    public MedicalRecords(String medicalRecordId, String patientId, String doctorId, String patientName, String doctorName, String dateTime, String details) {
        this.medicalRecordId = medicalRecordId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.dateTime = dateTime;
        this.details = details;
    }

    public String getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(String medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "MedicalRecords{" +
                "patientId='" + patientId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", patientFirstName='" + patientName + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
