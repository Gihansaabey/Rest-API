package com.mycompany.healthapp.resource;

import com.mycompany.healthapp.models.Prescription;
import com.mycompany.healthapp.daos.PrescriptionDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/prescription")
public class PrescriptionResource {
    // Instantiate prescriptionService
    PrescriptionDao prescriptionService = new PrescriptionDao();

    // Find all prescription
    @GET
    @Path("/findall")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Prescription> getPrescriptoins() {
        return (ArrayList<Prescription>) prescriptionService.findAllPrescription();
    }

    // Find prescription by ID
    @GET
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Prescription getGetPrescriptionById(@PathParam("id") int id) {
        return prescriptionService.findPrescriptionById(id);
    }

    // Create a new prescription
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPrecription(Prescription prescription) {
        String message = prescriptionService.createPrescription(prescription);
        if (message.contains("successfully")) {
            return Response.status(Response.Status.CREATED).entity(message).build(); // Return a success response
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(message).build(); // Return an error response
        }
    }

    // Update prescription
    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatedPrescription(@PathParam("id") int id, Prescription updatedPrescription) {
        try {
            String message = prescriptionService.updatePrescription(id, updatedPrescription);
            if (message.contains("successfully")) {
                return Response.status(Response.Status.OK).entity(message).build(); // Return a success response
            } else {
                return Response.status(Response.Status.OK).entity(message).build(); // Return a success response with message
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    // Get prescription by patientName
    @GET
    @Path("/patient/{patientName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrescriptionByPatientId(@PathParam("patientName") String patientName) {
        List<Prescription> prescriptions = prescriptionService.findPrescriptionByPatientName(patientName);
        if (prescriptions.isEmpty()) {
            return Response.status(Response.Status.OK).entity("Error: No prescription found for patient name: " + patientName).build();
        }
        return Response.ok(prescriptions).build();
    }

    // Delete a prescription
    @DELETE
    @Path("/delete/{id}")
    public Response deletePrescription(@PathParam("id") int id) {
        try {
            String message = prescriptionService.deletePrescription(id);
            if (message.contains("successfully")) {
                return Response.status(Response.Status.OK).entity(message).build(); // Return a success response
            }
            else {
                return Response.status(Response.Status.BAD_REQUEST).entity(message).build(); // Return an error response
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
