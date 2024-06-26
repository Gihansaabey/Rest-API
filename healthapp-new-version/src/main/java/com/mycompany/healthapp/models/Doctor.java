package com.mycompany.healthapp.models;

public class Doctor extends Person{
    private String doctorId;
    private String specialization;


    public Doctor(){}
    public Doctor(String firstName, String lastName, String phoneNumber, String address, String doctorId, String specialization, String mobileNumber) {
        super(firstName, lastName, phoneNumber, address);
        this.doctorId = doctorId;
        this.specialization = specialization;

    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }



    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId='" + doctorId + '\'' +
                ", specialization='" + specialization + '\''+
                '}';
    }


}
