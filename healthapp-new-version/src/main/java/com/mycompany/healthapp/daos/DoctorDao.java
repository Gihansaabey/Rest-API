package com.mycompany.healthapp.daos;

import com.mycompany.healthapp.models.Doctor;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DoctorDao {
    private static final String SavePath = "doctors-file.ser";
    private HashMap<Integer, Doctor> doctorHashMap;

    // Constructor
    public DoctorDao(){
        loadDoctorFromFile(); // Load doctor data from file or initialize an empty HashMap if file doesn't exist
        if (doctorHashMap == null) {
            doctorHashMap = new HashMap<>();
        }
    }

    // Load doctor data from file
    private void loadDoctorFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SavePath))) {
            doctorHashMap = (HashMap<Integer, Doctor>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            doctorHashMap = new HashMap<>(); // Initialize a new HashMap if file does not exist or cannot be read
        }
    }

    // Save doctor data to file
    private void saveDoctorToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SavePath))) {
            oos.writeObject(doctorHashMap);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Unable to save doctor data. Please ensure all connections are stable and try again.");

        }
    }

    // Find all doctors
    public List<Doctor> findAllDoctor() {
        try {
            List<Doctor> doctors = new ArrayList<>(doctorHashMap.values());
            return doctors; // Return list of doctors
        }catch (Exception e){
            System.err.println("Error: Failed to retrieve doctor data. Reason: " + e.getMessage());
            return Collections.emptyList(); // Return an empty list if error occurred
        }
    }

    // Find doctor by ID
    public Doctor findDoctorById(int id) {
        try {
            return doctorHashMap.get(id); // Return a doctor by ID
        }catch (Exception e){
            System.err.println("Error: Failed to find doctor by ID. Reason: " + e.getMessage());
            return null; // Return null if an error occurred
        }
    }

    // Create a new doctor
    public String createDoctor(Doctor doctor) {
        try {
            int nextId = doctorHashMap.size() + 1; // Generate the next ID
            doctor.setDoctorId("DOC" + String.format("%03d", nextId)); // Set the doctor ID
            doctorHashMap.put(nextId, doctor); // Add the doctor to the HashMap
            if (doctorHashMap.containsValue(doctor)) {
                saveDoctorToFile(); // Save the updated HashMap to the file
                return "Success: The Doctor was created successfully. Doctor ID: " + doctor.getDoctorId(); // Returns a success message if doctor was created successfully
            } else {
                return "Error: Failed to create doctor."; // Returns an error message if failed to create doctor
            }
        }catch (Exception e){
            return "Error: Failed to create doctor. Reason: " + e.getMessage(); // Returns an error message if an error occurred during doctor creation
        }
    }

    // Update doctor
    public String updateDoctor(int id, Doctor updatedDoctor) {
        try {
            if (doctorHashMap.containsKey(id)) {
                Doctor existingDoctor = doctorHashMap.get(id);
                existingDoctor.setFirstName(updatedDoctor.getFirstName());
                existingDoctor.setLastName(updatedDoctor.getLastName());
                existingDoctor.setPhoneNumber(updatedDoctor.getPhoneNumber());
                existingDoctor.setAddress(updatedDoctor.getAddress());
                existingDoctor.setSpecialization(updatedDoctor.getSpecialization());
                if (!existingDoctor.equals(updatedDoctor)) {
                    saveDoctorToFile(); // Save the updated HashMap to the file
                    return "Success: The doctor with ID " + updatedDoctor.getDoctorId() + " was updated successfully."; // Returns a success message if the doctor was updated successfully
                } else {
                    return "Error: Failed to update doctor. No changes detected."; // Returns an error message if no changes detected during doctor update
                }
            } else {
                throw new IllegalArgumentException("Error: Doctor with ID " + id + " does not exist"); // Throws an error if doctor ID does not exist
            }
        } catch(Exception e){
            return "Error: Failed to update doctor. Reason: " + e.getMessage(); // Returns an error message if an error occurred during doctor update
        }
    }

    // Delete a doctor by ID
    public String deleteDoctor(int id) {
        try {
            if (doctorHashMap.containsKey(id)) {
                doctorHashMap.remove(id); // Remove specified doctor from the HashMap.
                if (!doctorHashMap.containsKey(id)) {
                    saveDoctorToFile(); // Save the updated HashMap to the file
                    return "Success: The doctor has been successfully deleted."; // Returns a success message if doctor deletion was successful
                } else {
                    return "Error: Failed to delete doctor."; // Returns an error message if doctor deletion failed
                }
            } else {
                throw new IllegalArgumentException("Error: Doctor with ID " + id + " does not exist"); // Throws an error if doctor ID does not exist
            }
        } catch (Exception e){
            return "Error: Failed to delete doctor. Reason: " + e.getMessage(); // Returns an error message if an error occurred during doctor deletion
        }
    }

    // Find doctor by doctorId
    public Doctor findDoctorsByDoctorId(String doctorId) {
        try {
            for (Doctor doctor : doctorHashMap.values()) {
                if (doctor.getDoctorId().equals(doctorId)) {
                    return doctor;
                }
            }
            throw new IllegalArgumentException("Error: Doctor with ID " + doctorId + " does not exist"); // Throws an error if doctor ID does not exist
        } catch (Exception e){
            System.err.println("Error: Failed to retrieve doctor data. Reason: " + e.getMessage());
            return null ;// Return null if error occurred
        }
    }

    // Find doctor by firstName
    public Doctor findDoctorsByDoctorName(String firstName) {
        try {
            for (Doctor doctor : doctorHashMap.values()) {
                if (doctor.getFirstName().equals(firstName)) {
                    return doctor;
                }
            }
            throw new IllegalArgumentException("Error: Doctor " + firstName + " does not exist"); // Throws an error if doctor Name does not exist
        } catch (Exception e){
            System.err.println("Error: Failed to retrieve doctor data. Reason: " + e.getMessage());
            return null;
        }
    }

    // Get all doctors by specialization
    public List<Doctor> findDoctorsBySpeicalization(String specialisation) {
        try {
            List<Doctor> doctorArrayList = new ArrayList<>();
            for (Doctor doctor : doctorHashMap.values()) {
                if (doctor.getSpecialization().equals(specialisation)) {
                    doctorArrayList.add(doctor);
                }
            }
            return doctorArrayList; // Return list of doctors relevant to specialization
        } catch (Exception e){
            System.err.println("Error: Failed to retrieve doctor data. Reason: " + e.getMessage());
            return Collections.emptyList(); // Return an empty list if error occurred
        }
    }

}
