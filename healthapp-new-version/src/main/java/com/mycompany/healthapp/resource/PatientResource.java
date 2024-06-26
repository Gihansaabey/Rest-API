package com.mycompany.healthapp.resource;


import com.mycompany.healthapp.models.Patient;
import com.mycompany.healthapp.daos.PatientDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/patients")
public class PatientResource {
    PatientDao patientService = new PatientDao();

    // Find all patients
    @GET
    @Path("/findall")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Patient> getPatients() {
        return (ArrayList<Patient>) patientService.findAllPatient();
    }

    // Find patient by ID
    @GET
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Patient getPatientById(@PathParam("id") int id) {
        return patientService.findPatientById(id);
    }

    // Create a new patient
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPatient(Patient patient) {
        String message = patientService.createPatient(patient); // Invoke the method in PatientService to create a new patient
        if (message.contains("successfully")) {
            return Response.status(Response.Status.CREATED).entity(message).build(); // Return a success response
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(message).build(); // Return an error response
        }
    }

    // Update patient
    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePatient(@PathParam("id") int id, Patient updatedPatient) {
        try {
            String message = patientService.updatePatient(id, updatedPatient); // Invoke the method in PatientService to update patient
            if (message.contains("successfully")) {
                return Response.status(Response.Status.OK).entity(message).build(); // Return a success response
            } else {
                return Response.status(Response.Status.OK).entity(message).build(); // Return a success response with message
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build(); // Return an error response for not found exception
        }
    }

    // Delete a patient
    @DELETE
    @Path("/delete/{id}")
    public Response deletePatient(@PathParam("id") int id) {
        try {
            String message = patientService.deletePatient(id); // Invoke method in PatientService to delete a patient
            if (message.contains("successfully")) {
                return Response.status(Response.Status.OK).entity(message).build(); // Return a success response
            }
            else {
                return Response.status(Response.Status.BAD_REQUEST).entity(message).build(); // Return an error response
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build(); // Return an error response for not found exception
        }
    }

    // Find patient by patientId
    @GET
    @Path("/patientId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatientByPatientId(@PathParam("id") String id) {
        Patient patient = patientService.findPatientByPatientId(id);
        if(patient == null){
            return Response.status(Response.Status.OK).entity("Error: Patient with ID " + id + " does not exist").build();
        }
        return Response.status(Response.Status.OK).entity(patient).build();
    }

    // Find patient by patientName
    @GET
    @Path("/patientName/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatientByPatientName(@PathParam("name") String name) {
        Patient patient = patientService.findPatientByPatientName(name);
        if(patient == null){
            return Response.status(Response.Status.OK).entity("Error: Patient " + name + " does not exist").build();
        }
        return Response.status(Response.Status.OK).entity(patient).build();
    }

}
