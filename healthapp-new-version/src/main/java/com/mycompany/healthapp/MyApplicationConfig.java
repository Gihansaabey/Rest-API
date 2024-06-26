/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthapp;
import com.mycompany.healthapp.config.LoggingConfig;
import com.mycompany.healthapp.resource.*;
import com.mycompany.healthapp.filters.RequestLoggingFilter;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;


public class MyApplicationConfig extends ResourceConfig {
public MyApplicationConfig() {
    LoggingConfig.configure();
    register(RequestLoggingFilter.class);
    register(LoggingFeature.class);
    register(PatientResource.class);
    register(DoctorResource.class);
    register(AppointmentResource.class);
    register(PrescriptionResource.class);
    register(MedicalRecordResource.class);
    register(BillingResource.class);

    }
}
