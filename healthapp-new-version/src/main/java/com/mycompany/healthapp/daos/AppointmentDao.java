package com.mycompany.healthapp.daos;

import com.mycompany.healthapp.models.Appointments;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AppointmentDao {
    private static final String SavePath = "appointments-file.ser";
    private HashMap<Integer, Appointments> appointmentsList;

    // Constructor
    public AppointmentDao(){
        loadAppointmentFromFile(); // Load appointment data from file or initialize an empty HashMap if file doesn't exist
        if (appointmentsList == null) {
            appointmentsList = new HashMap<>();
        }
    }

    // Load appointment data from file
    private void loadAppointmentFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SavePath))) {
            appointmentsList = (HashMap<Integer, Appointments>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            appointmentsList = new HashMap<>(); // File does not exist or cannot be read, initialize a new HashMap
        }
    }

    // Save appointment data to file
    private void saveAppointmentToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SavePath))) {
            oos.writeObject(appointmentsList);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Unable to save appointment data. Please ensure all connections are stable and try again.");
        }
    }

    // Find all appointments
    public List<Appointments> findAllAppointments() {
        try {
            List<Appointments> appointments = new ArrayList<>(appointmentsList.values());
            return appointments; // Return list of appointments
        } catch (Exception e){
            System.err.println("Error: Failed to retrieve appointment data. Reason: " + e.getMessage());
            return Collections.emptyList(); // Return an empty list if error occurred
        }
    }

    // Find appointment by ID
    public Appointments findAppointmentById(int id) {
        try {
            return appointmentsList.get(id); // Return appointment by ID
        } catch (Exception e){
            System.err.println("Error: Failed to find appointment by ID. Reason: " + e.getMessage());
            return null; // Return null if error occurred
        }
    }

    // Create a new appointment
    public String createAppointment(Appointments appointment) {
        try {
            int nextId = appointmentsList.size() + 1; // Generate the next ID
            appointment.setAppointmentId("APO" + String.format("%03d", nextId)); // Set the appointment ID
            appointmentsList.put(nextId, appointment); // Add the appointment to the HashMap
            if (appointmentsList.containsValue(appointment)){
                saveAppointmentToFile(); // Save the updated appointmentHashMap to the file
                return "Success: The appointment for patient with ID " + appointment.getPatientId() + " was created successfully. Appointment ID: " + appointment.getAppointmentId(); // Returns a success message
            } else {
                return "Error: Failed to create appointment."; // Returns an error message if failed to create appointment
            }
        } catch (Exception e){
            return "Error: Failed to create appointment. Reason: " + e.getMessage(); // Returns an error message if an error occurred during appointment creation
        }
    }

    // Update appointment
    public String updateAppointment(int id, Appointments updatedAppointment) {
        try {
            if (appointmentsList.containsKey(id)) {
                Appointments existingAppointment = appointmentsList.get(id);
                existingAppointment.setDateTime(updatedAppointment.getDateTime());
                existingAppointment.setDoctorName(updatedAppointment.getDoctorName());
                existingAppointment.setPatientName(updatedAppointment.getPatientName());
                existingAppointment.setPatientId(updatedAppointment.getPatientId());
                existingAppointment.setDoctorId(updatedAppointment.getDoctorId());
                if (!existingAppointment.equals(updatedAppointment)) {
                    saveAppointmentToFile(); // Save the updated appointmentHashMap to the file
                    return "Success: The appointment with ID " + updatedAppointment.getAppointmentId() + " was updated successfully."; // Returns a success message
                } else {
                    return "Error: Failed to update appointment. No changes detected."; // Returns an error message if no changes detected during appointment update
                }
            } else {
                throw new IllegalArgumentException("Error: Appointment with ID " + id + " does not exist"); // Throws an error if appointment ID does not exist
            }
        } catch (Exception e) {
            return "Error: Failed to update appointment. Reason: " + e.getMessage(); // Returns an error message if an error occurred during appointment update
        }
    }

    // Delete an appointment by ID
    public String deleteAppointment(int id) {
        try {
            if (appointmentsList.containsKey(id)) {
                appointmentsList.remove(id);
                if (!appointmentsList.containsKey(id)) {
                    saveAppointmentToFile(); // Save the updated appointmentHashMap to the file
                    return "Success: The appointment has been successfully deleted."; // Returns a success message if appointment deletion was successful
                } else {
                    return "Error: Failed to delete appointment."; // Returns an error message if appointment deletion failed
                }
            } else {
                throw new IllegalArgumentException("Error: Appointment with ID " + id + " does not exist"); // Throws an error if appointment ID does not exist
            }
        } catch (Exception e){
            return "Error: Failed to delete appointment. Reason: " + e.getMessage(); // Returns an error message if an error occurred during appointment deletion
        }
    }

    // Find appointments by patientId
    public List<Appointments> findAppointmentsByPatientId(String patientId) {
        try {
            List<Appointments> appointmentsByPatientId = new ArrayList<>();
            for (Appointments appointment : appointmentsList.values()) {
                if (appointment.getPatientId().equals(patientId)) {
                    appointmentsByPatientId.add(appointment);
                }
            }
            return appointmentsByPatientId; // Return list of appointments relevant to patientId
        } catch (Exception e){
            System.err.println("Error: Failed to retrieve appointment data. Reason: " + e.getMessage());
            return Collections.emptyList(); // Return an empty list if error occurred
        }
    }

    // Find appointments by doctorId
    public List<Appointments> findAppointmentsByDoctorId(String doctorId) {
        try {
            List<Appointments> appointmentsByDoctorId = new ArrayList<>();
            for (Appointments appointment : appointmentsList.values()) {
                if (appointment.getDoctorId().equals(doctorId)) {
                    appointmentsByDoctorId.add(appointment);
                }
            }
            return appointmentsByDoctorId; // Return list of appointments relevant to doctorId
        } catch (Exception e){
            System.err.println("Error: Failed to retrieve appointment data. Reason: " + e.getMessage());
            return Collections.emptyList(); // Return an empty list if error occurred
        }
    }
}
