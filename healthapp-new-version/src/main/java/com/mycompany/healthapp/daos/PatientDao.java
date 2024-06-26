package com.mycompany.healthapp.daos;

import com.mycompany.healthapp.models.Patient;

import java.io.*;
import java.util.*;

public class PatientDao {
    private static final String SavePath = "patients-file.ser";
    private HashMap<Integer, Patient> patientHashMap;

    // Constructor
    public PatientDao() {
        loadPatientFromFile(); // Load patient data from file or initialize an empty HashMap if file doesn't exist
        if (patientHashMap == null) {
            patientHashMap = new HashMap<>();
        }
    }

    // Load patient data from file
    private void loadPatientFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SavePath))) {
            patientHashMap = (HashMap<Integer, Patient>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            patientHashMap = new HashMap<>(); // Initialize a new HashMap if file does not exist or cannot be read
        }
    }

    // Save patient data to file
    private void savePatientToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SavePath))) {
            oos.writeObject(patientHashMap);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Unable to save patient data. Please ensure all connections are stable and try again.");
        }
    }

    // Find all patients
    public List<Patient> findAllPatient() {
        try {
            List<Patient> patients = new ArrayList<>(patientHashMap.values());
            return patients; // Returns a list of patients
        } catch (Exception e) {
            System.err.println("Error: Failed to retrieve patient data. Reason: " + e.getMessage());
            return Collections.emptyList(); // Returns an empty list if an error occurred
        }
    }

    // Find patient by ID
    public Patient findPatientById(int id) {
        try {
            return patientHashMap.get(id); // Returns a patient by ID
        } catch (Exception e) {
            System.err.println("Error: Failed to find patient by ID. Reason: " + e.getMessage());
            return null; // Returns null if an error occurred
        }
    }

    // Create a new patient
    public String createPatient(Patient patient) {
        try {
            int nextId = patientHashMap.size() + 1; // Generates the next patient ID
            patient.setPatientId("PAT" + String.format("%03d", nextId)); // Sets the patient ID
            patientHashMap.put(nextId, patient); // Adds the patient to the HashMap
            if (patientHashMap.containsValue(patient)) {
                savePatientToFile(); // Saves the updated HashMap to the file
                return "Success: The patient was created successfully. Patient ID: " + patient.getPatientId(); // Returns a success message if patient was created successfully
            } else {
                return "Error: Failed to create patient."; // Returns an error message if patient create failed
            }
        } catch (Exception e) {
            return "Error: Failed to create patient. Reason: " + e.getMessage(); // Returns an error message if an error occurred during patient creation
        }
    }

    // Update patient
    public String updatePatient(int id, Patient updatedPatient) {
        try {
            if (patientHashMap.containsKey(id)) {
                Patient existingPatient = patientHashMap.get(id);
                existingPatient.setFirstName(updatedPatient.getFirstName());
                existingPatient.setLastName(updatedPatient.getLastName());
                existingPatient.setPhoneNumber(updatedPatient.getPhoneNumber());
                existingPatient.setAddress(updatedPatient.getAddress());
                existingPatient.setHistory(updatedPatient.getHistory());
                existingPatient.setCurrentStatus(updatedPatient.getCurrentStatus());
                if (!existingPatient.equals(updatedPatient)) {
                    savePatientToFile(); // Saves the updated HashMap to the file
                    return "Success: The patient with ID " + updatedPatient.getPatientId() + " was updated successfully."; // Returns a success message if patient was updated successfully
                } else {
                    return "Error: Failed to update patient. No changes detected."; // Returns an error message if no changes detected
                }
            } else {
                throw new IllegalArgumentException("Error: Patient with ID " + id + " does not exist"); // Throws an error if patient ID does not exist
            }
        } catch (Exception e) {
            return "Error: Failed to update patient. Reason: " + e.getMessage(); // Returns an error message if an error occurred
        }
    }

    // Delete a patient by ID
    public String deletePatient(int id) {
        try {
            if (patientHashMap.containsKey(id)) {
                patientHashMap.remove(id); // Removes specified patient data from the HashMap.
                if (!patientHashMap.containsKey(id)) {
                    savePatientToFile(); // Saves the updated HashMap to the file
                    return "Success: The patient has been successfully deleted."; // Returns a success message if patient deletion was successful
                } else {
                    return "Error: Failed to delete patient."; // Returns an error message if patient deletion failed
                }
            } else {
                throw new IllegalArgumentException("Error: Patient with ID " + id + " does not exist"); // Throws an error if patient ID does not exist
            }
        } catch (Exception e) {
            return "Error: Failed to delete patient. Reason: " + e.getMessage(); // Returns an error message if an error occurred during patient deletion
        }
    }

    // Get patient by patientId
    public Patient findPatientByPatientId(String patientId) {
        try {
            for (Patient patient : patientHashMap.values()) {
                if (patient.getPatientId().equals(patientId)) {
                    return patient;
                }
            }
            throw new IllegalArgumentException("Error: Patient with ID " + patientId + " does not exist"); // Throws an error if patient ID does not exist
        } catch (Exception e){
            System.err.println("Error: Failed to retrieve patient data. Reason: " + e.getMessage());
            return null ;// Returns null if an error occurred
        }
    }

    // Get patient by patientName
    public Patient findPatientByPatientName(String patientName) {
        try {
            for (Patient patient : patientHashMap.values()) {
                if (patient.getFirstName().equals(patientName)) {
                    return patient;
                }
            }
            throw new IllegalArgumentException("Error: Patient " + patientName + " does not exist"); // Throws an error if patient Name does not exist
        } catch (Exception e){
            System.err.println("Error: Failed to retrieve patient data. Reason: " + e.getMessage());
            return null ;// Returns null if an error occurred
        }
    }
}
