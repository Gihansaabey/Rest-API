package com.mycompany.healthapp.daos;

import com.mycompany.healthapp.models.Billing;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BillingDao {
    private static final String SavePath = "billing-file.ser";
    private HashMap<Integer, Billing> billingHashMap;

    // Constructor
    public BillingDao(){
        loadBilllingFromFile(); // Load billing data from file or initialize an empty HashMap if file doesn't exist
        if (billingHashMap == null) {
            billingHashMap = new HashMap<>();
        }
    }

    // Load billing data from file
    private void loadBilllingFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SavePath))) {
            billingHashMap = (HashMap<Integer, Billing>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            billingHashMap= new HashMap<>(); // File does not exist or cannot be read, initialize a new HashMap
        }
    }

    // Save billing data to file
    private void saveBillingToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SavePath))) {
            oos.writeObject(billingHashMap);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Unable to save billing data. Please ensure all connections are stable and try again.");
        }
    }

    // Find all billings
    public List<Billing> findAllBillings() {
        try {
            List<Billing> billing = new ArrayList<>(billingHashMap.values());
            return billing; // Return list of billings
        } catch (Exception e){
            System.err.println("Error: An error occurred while retrieving billing data: " + e.getMessage());
            return new ArrayList<>(); // Return an empty list if error occurred
        }
    }

    // Create a new billings
    public String createBilling(Billing billing) {
        try {
            int nextId = billingHashMap.size() + 1; // Generate the next ID
            billing.setId("BILL" + String.format("%03d", nextId)); // Set the billing ID
            billingHashMap.put(nextId, billing); // Add the bill to the HashMap
            if (billingHashMap.containsValue(billing)) {
                saveBillingToFile(); // Save the updated billingHashMap to the file
                return "Success: The billing for patient with ID " + billing.getPatientId()+ " created successfully. Your bill id is " + billing.getId(); // Return a success message
            } else {
                return "Error: Failed to create bill"; // Return an error message
            }
        } catch (Exception e) {
            return "Error: Error occurred while creating billing: " + e.getMessage(); // Return an error message if error occurred
        }
    }

    // Update billing
    public String updateBilling(int id, Billing updatedBilling) {
        try {
            if (billingHashMap.containsKey(id)) {
                Billing existingBilling = billingHashMap.get(id);
                existingBilling.setPaid(updatedBilling.isPaid());
                existingBilling.setAmount(updatedBilling.getAmount());
                existingBilling.setPatientId(updatedBilling.getPatientId());
                existingBilling.setDateTime(updatedBilling.getDateTime());
                if (!existingBilling.equals(updatedBilling)) {
                    saveBillingToFile(); // Save the updated HashMap to the file
                    return "Success: The bill with ID " + updatedBilling.getId() + " updated successfully"; // Return a success message
                } else {
                    return "Error: Failed to update bill with ID " + id; // Return error message
                }
            } else {
                throw new IllegalArgumentException("Error: Bill with ID " + id + " does not exist"); // Throw an error if Bill ID does not exist
            }
        } catch (Exception e) {
            return "Error: Error occurred while updating bill: " + e.getMessage(); // Return an error message if error occurred
        }
    }
    
    // Delete a bill by ID
    public String deleteBilling(int id) {
        try {
            if(billingHashMap.containsKey(id)) {
                billingHashMap.remove(id);
                if (!billingHashMap.containsKey(id)) {
                    saveBillingToFile(); // Save the updated billingHashMap to the file
                    return "Success: The bill has been successfully deleted."; // Return a success message
                } else {
                    return "Error: Failed to delete bill with ID "; // Return an error message
                }
            } else {
                throw new IllegalArgumentException("Error: Bill with ID " + id + " does not exist"); // Throw an error if Bill ID does not exist
            }
        } catch (Exception e) {
            return "Error: Error occurred while deleting bill: " + e.getMessage(); // Return an error message if error occurred
        }
    }

    // Find bill by patientId
    public List<Billing> findBillingByPatientId(String patientId) {
        try {
            List<Billing> billingsByPatientId = new ArrayList<>();
            for (Billing billing : billingHashMap.values()) {
                if (billing.getPatientId().equals(patientId)) {
                    billingsByPatientId.add(billing);
                }
            }
            return billingsByPatientId; // Return list of bills relevant to patientId
        } catch (Exception e) {
            System.err.println("Error: An error occurred while retrieving billing data: " + e.getMessage());
            return Collections.emptyList(); // Return an empty list if error occurred
        }
    }

}
