package com.mycompany.healthapp.daos;

import com.mycompany.healthapp.models.MedicalRecords;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MedicalRecordsDao {
    private static final String SavePath = "medicalrecords-file.ser";
    private HashMap<Integer, MedicalRecords> medicalRecordHashMap;

    // Constructor
    public MedicalRecordsDao(){
        loadMedicalRecsFromFile();
        if (medicalRecordHashMap == null) {
            medicalRecordHashMap = new HashMap<>();
        }
    }

    // Load medicalRecs data from file
    private void loadMedicalRecsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SavePath))) {
            medicalRecordHashMap = (HashMap<Integer, MedicalRecords>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            medicalRecordHashMap = new HashMap<>(); // File does not exist or cannot be read, initialize a new HashMap
        }
    }

    // Save medicalRecs data to file
    private void saveMedicalRecsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SavePath))) {
            oos.writeObject(medicalRecordHashMap);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    // Find all medicalRecs
    public List<MedicalRecords> findAllMedicalRecords() {
        try {
            List<MedicalRecords> medicalRecords = new ArrayList<>(medicalRecordHashMap.values());
            return medicalRecords; // Return list of medicalRecs
        } catch (Exception e) {
            System.err.println("Error: An error occurred while retrieving medicalRecs data: " + e.getMessage());
            return Collections.emptyList(); // Return an empty list if error occurred
        }
    }

    // Find medicalRecs by ID
    public MedicalRecords findMedicalRecordById(int id) {
        try {
            return medicalRecordHashMap.get(id); // Return medicalRecs by ID
        } catch (Exception e) {
            System.err.println("Error: An error occurred while finding appointment by ID: " + e.getMessage());
            return null; // Return null if error occurred
        }
    }

    // Create a new medicalRecs
    public String createMedicalRecord(MedicalRecords medicalRecords) {
        try {
            int nextId = medicalRecordHashMap.size() + 1; // Generate the next ID
            medicalRecords.setMedicalRecordId("MED" + String.format("%03d", nextId)); // Set the medicalRecord ID
            medicalRecordHashMap.put(nextId, medicalRecords); // Add the medicalRecs to the HashMap
            if (medicalRecordHashMap.containsValue(medicalRecords)) {
                saveMedicalRecsToFile(); // Save the updated medicalRecordHashMap to the file
                return "Success: The medical Record for patient " + medicalRecords.getPatientName()+ " created successfully. Your medicalRecord id is " + medicalRecords.getMedicalRecordId(); // Return success message
            } else {
                return "Error: Failed to create medical Record."; // Return error message if failed to create medical Record
            }
        } catch (Exception e) {
            return "Error: Error occurred while creating medicalRecs: " + e.getMessage(); // Return error message if error occurred
        }
    }

    // Update medicalRec
    public String updateMedicalRecord(int id, MedicalRecords updatedMedicRec) {
        try {
            if (medicalRecordHashMap.containsKey(id)) {
                MedicalRecords existingMedicalRecords = medicalRecordHashMap.get(id);
                existingMedicalRecords.setDateTime(updatedMedicRec.getDateTime());
                existingMedicalRecords.setDoctorName(updatedMedicRec.getDoctorName());
                existingMedicalRecords.setPatientName(updatedMedicRec.getPatientName());
                existingMedicalRecords.setPatientId(updatedMedicRec.getPatientId());
                existingMedicalRecords.setDoctorId(updatedMedicRec.getDoctorId());
                existingMedicalRecords.setDetails(updatedMedicRec.getDetails());
                if(!existingMedicalRecords.equals(updatedMedicRec)){
                    saveMedicalRecsToFile(); // Save the updated HashMap to the file
                    return "Success: Medical Record with ID " + updatedMedicRec.getMedicalRecordId() + " updated successfully"; // Return success message
                } else {
                    return "Error: Failed to update medical Record. No changes detected"; // Return an error message if failed to update medical Record
                }
            } else {
                throw new IllegalArgumentException("Error: Medical Record with ID " + id + " does not exist");
            }
        } catch (Exception e) {
            return "Error: Error occurred while updating medicalRecs: " + e.getMessage(); // Return an error message if error occurred
        }
    }

    // Delete an appointment by ID
    public String deleteMedicalRecord(int id) {
        try {
            if(medicalRecordHashMap.containsKey(id)){
                medicalRecordHashMap.remove(id);
                if(!medicalRecordHashMap.containsKey(id)) {
                    saveMedicalRecsToFile(); // Save the updated patientHashMap to the file
                    return "Success: The medical Record has been deleted successfully"; // Return a success message
                } else {
                    return "Error: Failed to delete medical Record"; // Return an error message if failed to delete medical Record
                }
            } else {
                throw new IllegalArgumentException("Error: Medical Record with ID " + id + " does not exist"); // Throw an error if appointment ID does not exist
            }
        } catch (Exception e) {
            return "Error: Error occurred while deleting appointment: " + e.getMessage(); // Return an error message if error occurred
        }
    }

    // Find medical records by patientName
    public List<MedicalRecords> findMedicalRecordByPatientName(String pateintName) {
        try {
            List<MedicalRecords> medicalRecords = new ArrayList<>();
            for (MedicalRecords medicalRecord : medicalRecordHashMap.values()) {
                if (medicalRecord.getPatientName().equals(pateintName)) {
                    medicalRecords.add(medicalRecord);
                }
            }
            return medicalRecords; // Return list of medicalRecords relevant to pateintName
        } catch (Exception e) {
            System.err.println("Error: An error occurred while retrieving medical records data: " + e.getMessage());
            return Collections.emptyList(); // Return an empty list if error occurred
        }
    }
}
