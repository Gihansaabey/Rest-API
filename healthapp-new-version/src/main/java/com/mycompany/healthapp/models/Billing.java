package com.mycompany.healthapp.models;

import java.io.Serializable;

public class Billing implements Serializable {
    private String id;
    private String patientId;
    private double amount;
    private boolean paid;
    private String dateTime;
    
    public Billing(){}

    public Billing(String id, String patientId, double amount, boolean paid,String dateTime) {
        this.id = id;
        this.patientId = patientId;
        this.amount = amount;
        this.paid = paid;
        this.dateTime =dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    

    @Override
    public String toString() {
        return "Billing{" +
                "id='" + id + '\'' +
                ", patientId='" + patientId + '\'' +
                ", amount=" + amount +
                ", paid=" + paid +
                '}';
    }
}
