package com.mycompany.healthapp.models;

import java.io.Serializable;

public class Person implements Serializable {
    private String FirstName;
    private String LastName;
    private String PhoneNumber;
    private String Address;
    public Person() {
    }
    public Person(String firstName, String lastName, String phoneNumber, String address) {
        super();
        FirstName = firstName;
        LastName = lastName;
        PhoneNumber = phoneNumber;
        Address = address;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", Address='" + Address + '\'' +
                '}';
    }
}
