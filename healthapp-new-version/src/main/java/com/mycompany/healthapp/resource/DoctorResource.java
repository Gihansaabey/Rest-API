package com.mycompany.healthapp.resource;

import com.mycompany.healthapp.models.Doctor;
import com.mycompany.healthapp.daos.DoctorDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/doctors")
public class DoctorResource {
    DoctorDao doctorService = new DoctorDao();

    // Find all doctors
    @GET
    @Path("/findall")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Doctor> getDoctors() {
        return (ArrayList<Doctor>) doctorService.findAllDoctor();
    }

    // Find doctor by ID
    @GET
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Doctor getDoctorById(@PathParam("id") int id) {
        return doctorService.findDoctorById(id);
    }

    // Create a new doctor
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDoctor(Doctor doctor) {
        String message = doctorService.createDoctor(doctor);
        if (message.contains("successfully")) {
            return Response.status(Response.Status.CREATED).entity(message).build(); // Return a success response
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(message).build(); // Return an error response
        }
    }

    // Update doctor
    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDoctor(@PathParam("id") int id, Doctor updatedDoctor) {
        try {
            String message = doctorService.updateDoctor(id, updatedDoctor);
            if (message.contains("successfully")) {
                return Response.status(Response.Status.OK).entity(message).build(); // Return a success response
            } else {
                return Response.status(Response.Status.OK).entity(message).build(); // Return a success response with message
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    // Delete a doctor
    @DELETE
    @Path("/delete/{id}")
    public Response deleteDoctor(@PathParam("id") int id) {
        try {
            String message = doctorService.deleteDoctor(id);
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

    // Get doctor by doctorId
    @GET
    @Path("/doctorId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorByDoctorId(@PathParam("id") String id) {
        Doctor doctor = doctorService.findDoctorsByDoctorId(id);
        if(doctor == null){
            return Response.status(Response.Status.OK).entity("Error: Doctor with ID " + id + " does not exist").build();
        }
        return Response.status(Response.Status.OK).entity(doctor).build();

    }

    // Get doctor by firstName
    @GET
    @Path("/doctorName/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorByDoctorName(@PathParam("name") String name) {
        Doctor doctor = doctorService.findDoctorsByDoctorName(name);
        if(doctor == null){
            return Response.status(Response.Status.OK).entity("Error: Doctor " + name + " does not exist").build();
        }
        return Response.status(Response.Status.OK).entity(doctor).build();
    }

    // Get all doctor by specialisation
    @GET
    @Path("/specialisation/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorBySpecialisation(@PathParam("name") String specialisation) {
        List<Doctor> doctors = doctorService.findDoctorsBySpeicalization(specialisation);
        if (doctors.isEmpty()) {
            return Response.status(Response.Status.OK).entity("Error: No doctors found for specialization " + specialisation).build();
        }
        return Response.ok(doctors).build();
    }

}
