package com.mycompany.healthapp.models;

public class Patient extends Person {
    private String patientId;
    private String  history;
    private String currentStatus;

    // Default constructor

    public Patient() {
    }

    public Patient(String firstName, String lastName, String phoneNumber, String address, String patientId, String history, String currentStatus) {
        super(firstName, lastName, phoneNumber, address);
        this.patientId = patientId;
        this.history = history;
        this.currentStatus = currentStatus;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId='" + patientId + '\'' +
                ", history='" + history + '\'' +
                ", currentStatus='" + currentStatus + '\'' +
                '}';
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }
}

