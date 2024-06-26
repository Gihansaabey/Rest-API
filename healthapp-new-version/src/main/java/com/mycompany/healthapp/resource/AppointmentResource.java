package com.mycompany.healthapp.resource;

import com.mycompany.healthapp.models.Appointments;
import com.mycompany.healthapp.daos.AppointmentDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/appointments")
public class AppointmentResource {
    // Instantiate appointmentService
    AppointmentDao appointmentService = new AppointmentDao();

    // Find all appointments
    @GET
    @Path("/findall")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Appointments> getAppointments() {
        return (ArrayList<Appointments>) appointmentService.findAllAppointments();
    }

    // Get appointment by ID
    @GET
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Appointments getAppointmentById(@PathParam("id") int id) {
        return appointmentService.findAppointmentById(id);
    }

    // Create a new appointment
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDoctor(Appointments appointments) {
        String message = appointmentService.createAppointment(appointments);
        if (message.contains("successfully")) {
            return Response.status(Response.Status.CREATED).entity(message).build(); // Return a success response
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(message).build(); // Return an error response
        }
    }

    // Update appointment information
    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatedAppointment(@PathParam("id") int id, Appointments updatedAppointment) {
        try {
            String message = appointmentService.updateAppointment(id, updatedAppointment);
            if (message.contains("successfully")) {
                return Response.status(Response.Status.OK).entity(message).build(); // Return a success response
            } else {
                return Response.status(Response.Status.OK).entity(message).build(); // Return a success response with message
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build(); // Return an error response for not found exception
        }
    }

    // Delete a appointment
    @DELETE
    @Path("/delete/{id}")
    public Response deleteAppointment(@PathParam("id") int id) {
        try {
            String message = appointmentService.deleteAppointment(id);
            if (message.contains("successfully")) {
                return Response.status(Response.Status.OK).entity(message).build(); // Return a success response
            }
            else {
                return Response.status(Response.Status.BAD_REQUEST).entity(message).build(); // Return a error response
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build(); // Return an error response for not found exception
        }
    }

    // Find Appointment by patientId
    @GET
    @Path("/patient/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentsByPatientId(@PathParam("patientId") String patientId) {
        List<Appointments> appointments = appointmentService.findAppointmentsByPatientId(patientId);
        if (appointments.isEmpty()) {
            return Response.status(Response.Status.OK).entity("Error: No appointments found for patient ID: " + patientId).build();
        }
        return Response.ok(appointments).build();
    }

    // Find Appointment by doctorId
    @GET
    @Path("/doctor/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentsByDoctorId(@PathParam("doctorId") String doctorId) {
        List<Appointments> appointments = appointmentService.findAppointmentsByDoctorId(doctorId);
        if (appointments.isEmpty()) {
            return Response.status(Response.Status.OK).entity("Error: No appointments found for doctor ID: " + doctorId).build();
        }
        return Response.ok(appointments).build();
    }


}
