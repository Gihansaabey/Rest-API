package com.mycompany.healthapp.resource;

import com.mycompany.healthapp.models.Billing;
import com.mycompany.healthapp.daos.BillingDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/billing")
public class BillingResource {
    // Instantiate billingService
    BillingDao billingService = new BillingDao();

    // Find all billings
    @GET
    @Path("/findall")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Billing> getBillings() {
        return (ArrayList<Billing>) billingService.findAllBillings();
    }

    // Create a new billing
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBilling(Billing billing) {
        String message = billingService.createBilling(billing);
        if (message.contains("successfully")) {
            return Response.status(Response.Status.CREATED).entity(message).build(); // Return a success response
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(message).build(); // Return an error response
        }
    }

    // Update billing information
    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatedBilling(@PathParam("id") int id, Billing updatedBilling) {
        try {
            String message = billingService.updateBilling(id, updatedBilling);
            if (message.contains("successfully")) {
                return Response.status(Response.Status.OK).entity(message).build(); // Return a success response
            } else {
                return Response.status(Response.Status.OK).entity(message).build(); // Return a success response with message
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    // Delete a billing by ID
    @DELETE
    @Path("/delete/{id}")
    public Response deleteBilling(@PathParam("id") int id) {
        try {
            String message = billingService.deleteBilling(id);
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

    // Find billings by patient ID
    @GET
    @Path("/patient/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBillingsByPatientId(@PathParam("patientId") String patientId) {
        List<Billing> appointments = billingService.findBillingByPatientId(patientId);
        if (appointments.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Error: No billings found for patient ID: " + patientId).build();
        }
        return Response.ok(appointments).build();
    }
}
