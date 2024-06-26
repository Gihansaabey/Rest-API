/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.healthapp;
import com.mycompany.healthapp.resource.*;
import com.mycompany.healthapp.filters.RequestLoggingFilter;
import org.glassfish.jersey.logging.LoggingFeature;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("rest")
public class MyApplication extends Application {
@Override
public Set<Class<?>> getClasses() {
    Set<Class<?>> classes = new HashSet<>();
    classes.add(PatientResource.class);
    classes.add(DoctorResource.class);
    classes.add(RequestLoggingFilter.class);
    classes.add(LoggingFeature.class);
    classes.add(AppointmentResource.class);
    classes.add(PrescriptionResource.class);
    classes.add(MedicalRecordResource.class);
    classes.add(BillingResource.class);
    return classes;
    }
}
