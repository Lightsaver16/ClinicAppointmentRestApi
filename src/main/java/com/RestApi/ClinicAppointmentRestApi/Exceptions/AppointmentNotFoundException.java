package com.RestApi.ClinicAppointmentRestApi.Exceptions;

public class AppointmentNotFoundException extends Exception{

    public AppointmentNotFoundException(Long appointmentId) {
        super("Appointment with ID - " + appointmentId + " not found.");
    }

    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
