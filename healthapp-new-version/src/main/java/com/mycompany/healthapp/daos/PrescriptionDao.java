package com.mycompany.healthapp.daos;

import com.mycompany.healthapp.models.Prescription;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PrescriptionDao {
    private static final String SavePath = "prescription-file.ser";
    private HashMap<Integer, Prescription> prescriptionHashMap;

    // Constructor
    public PrescriptionDao(){
        loadPrescriptionFromFile(); // Load Prescription data from file or initialize an empty HashMap if file doesn't exist
        if (prescriptionHashMap == null) {
            prescriptionHashMap = new HashMap<>();
        }
    }

    // Load prescription data from file
    private void loadPrescriptionFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SavePath))) {
            prescriptionHashMap = (HashMap<Integer, Prescription>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            prescriptionHashMap = new HashMap<>(); // File does not exist or cannot be read, initialize a new HashMap
        }
    }

    // Save prescription data to file
    private void savePrescriptionToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SavePath))) {
            oos.writeObject(prescriptionHashMap);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Unable to save patient data. Please ensure all connections are stable and try again.");
        }
    }

    // Find all prescriptions
    public List<Prescription> findAllPrescription() {
        try {
            List<Prescription> prescriptions = new ArrayList<>(prescriptionHashMap.values());
            return prescriptions; // Return list of prescriptions
        } catch (Exception e) {
            System.err.println("Error: Failed to retrieve prescription data. Reason: " + e.getMessage());
            return Collections.emptyList(); // Return an empty list if error occurred
        }
    }

    // Find prescription by ID
    public Prescription findPrescriptionById(int id) {
        try {
            return prescriptionHashMap.get(id); // Return prescription by ID
        } catch (Exception e) {
            System.err.println("An error occurred while finding prescription by ID: " + e.getMessage());
            return null; // Return null if error occurred
        }
    }

    // Create a new prescription
    public String createPrescription(Prescription prescription) {
        try {
            int nextId = prescriptionHashMap.size() + 1; // Generate the next ID
            prescription.setPrescriptionId("PRE" + String.format("%03d", nextId)); // Set the prescription ID
            prescriptionHashMap.put(nextId, prescription); // Add the prescription to the HashMap
            if (prescriptionHashMap.containsValue(prescription)) {
                savePrescriptionToFile(); // Save the updated prescriptionHashMap to the file
                return "Success: The prescription for patient " + prescription.getPatientName()+ " created successfully. Your prescription id is " + prescription.getPrescriptionId(); // Return a success message
            } else {
                return "Error: Failed to create prescription."; // Return error message if failed to create prescription
            }
        } catch (Exception e) {
            return "Error: Error occurred while creating prescription: " + e.getMessage(); // Return error message if error occurred
        }
    }

    // Update patient information
    public String updatePrescription(int id, Prescription updatedPrescription) {
        try {
            if (prescriptionHashMap.containsKey(id)) {
                Prescription existingPrescription = prescriptionHashMap.get(id);
                existingPrescription.setDateTime(updatedPrescription.getDateTime());
                existingPrescription.setDoctorName(updatedPrescription.getDoctorName());
                existingPrescription.setPatientName(updatedPrescription.getPatientName());
                existingPrescription.setDateTime(updatedPrescription.getDateTime());
                existingPrescription.setMedications(updatedPrescription.getMedications());
                existingPrescription.setDiagnosis(updatedPrescription.getDiagnosis());
                if(!existingPrescription.equals(updatedPrescription)) {
                    savePrescriptionToFile(); // Save the updated prescriptionHashMap to the file
                    return "Success: The prescription with ID "+ updatedPrescription.getPrescriptionId() + " updated successfully."; // Return success message
                } else {
                    return "Error: Failed to update prescription. No changes detected."; // Return an error message if no changes detected
                }
            } else {
                throw new IllegalArgumentException("Error: Prescription with ID " + id + " does not exist"); // Throw an error if prescription ID does not exist
            }
        } catch (Exception e) {
            return "Error: Error occurred while updating prescription: " + e.getMessage(); // Return error message if error occurred
        }
    }

    // Delete a prescription by ID
    public String deletePrescription(int id) {
         try {
        if (prescriptionHashMap.containsKey(id)) {
            prescriptionHashMap.remove(id);
            if (!prescriptionHashMap.containsKey(id)) { 
                savePrescriptionToFile(); // Save the updated prescriptionHashMap to the file
                return "Success: The prescription has been successfully deleted."; // Return success message
            } else {
                return "Error: Failed to delete prescription."; // Return an error message if failed to delete prescription
            }
        } else {
            throw new IllegalArgumentException("Error: Prescription with ID " + id + " does not exist"); // Throw an error if prescription ID does not exist
        }
    } catch (Exception e) {
        return "Error: Error occurred while deleting prescription: " + e.getMessage(); // Return error message if error occurred
    }
    }

    // Find prescriptions by patientName
    public List<Prescription> findPrescriptionByPatientName(String patientName) {
        try {
            List<Prescription> prescriptionByPatientName = new ArrayList<>();
            for (Prescription prescription : prescriptionHashMap.values()) {
                if (prescription.getPatientName().equals(patientName)) {
                    prescriptionByPatientName.add(prescription);
                }
            }
            return prescriptionByPatientName; // Return list of prescriptions relevant to patientName
        } catch (Exception e) {
            System.err.println("Error: An error occurred while retrieving prescription data: " + e.getMessage());
            return Collections.emptyList(); // Return an empty list if error occurred
        }
    }
}
