package com.mycompany.healthapp.models;

import java.io.Serializable;
import java.util.Map;

public class Prescription implements Serializable {
    private String prescriptionId;
    private String patientName;
    private String doctorName;
    private String dateTime;
    private String diagnosis;
    private Map<String,String> medications;
    
    public Prescription(){}
    
    public Prescription(String prescriptionId, String patientName, String doctorName, String dateTime, String diagnosis, Map<String, String> medications) {
        this.prescriptionId = prescriptionId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.dateTime = dateTime;
        this.diagnosis = diagnosis;
        this.medications = medications;
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Map<String, String> getMedications() {
        return medications;
    }

    public void setMedications(Map<String, String> medications) {
        this.medications = medications;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "patientName='" + patientName + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", medications=" + medications +
                '}';
    }
}
