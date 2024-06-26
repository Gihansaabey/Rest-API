package com.mycompany.healthapp.resource;

import com.mycompany.healthapp.models.MedicalRecords;
import com.mycompany.healthapp.daos.MedicalRecordsDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/medical-records")
public class MedicalRecordResource {
    // Instantiate medicalRecordsService
    MedicalRecordsDao medicalRecordsService = new MedicalRecordsDao();

    // Find all medical records
    @GET
    @Path("/findall")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<MedicalRecords> getAppointments() {
        return (ArrayList<MedicalRecords>) medicalRecordsService.findAllMedicalRecords();
    }

    // Find medical records by ID
    @GET
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MedicalRecords getMediclaRecordById(@PathParam("id") int id) {
        return medicalRecordsService.findMedicalRecordById(id);
    }

    // Create a new medical record
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMedicalRecord(MedicalRecords medicalRecords) {
        String message = medicalRecordsService.createMedicalRecord(medicalRecords);
        if (message.contains("successfully")) {
            return Response.status(Response.Status.CREATED).entity(message).build(); // Return success response
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(message).build(); // Return error response
        }
    }

    // Update medical record
    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatedMedicalRecord(@PathParam("id") int id, MedicalRecords updatedMedicalRecord) {
        try {
            String message = medicalRecordsService.updateMedicalRecord(id, updatedMedicalRecord);
            if (message.contains("successfully")) {
                return Response.status(Response.Status.OK).entity(message).build(); // Return a success response
            } else {
                return Response.status(Response.Status.OK).entity(message).build(); // Return a success response with message
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build(); // Return an error response for not found exception
        }
    }

    // Delete medical record
    @DELETE
    @Path("/delete/{id}")
    public Response deleteMedicalRecord(@PathParam("id") int id) {
        try {
            String message = medicalRecordsService.deleteMedicalRecord(id);
            if (message.contains("successfully")) {
                return Response.status(Response.Status.OK).entity(message).build(); // Return success response
            }
            else {
                return Response.status(Response.Status.BAD_REQUEST).entity(message).build(); // Return error response
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build(); // Return error response for not found exception
        }
    }

    // Get Medical Record by patientName
    @GET
    @Path("/patient/{patientName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicalRecordByPatientName(@PathParam("patientName") String patientName) {
        List<MedicalRecords> medicalRecords = medicalRecordsService.findMedicalRecordByPatientName(patientName);
        if (medicalRecords.isEmpty()) {
            return Response.status(Response.Status.OK).entity("Error: No medical record found for patient name: " + patientName).build();
        }
        return Response.ok(medicalRecords).build();
    }
}
