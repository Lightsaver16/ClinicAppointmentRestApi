package com.RestApi.ClinicAppointmentRestApi.Exceptions;

public class PatientNotFoundException extends Exception{
    public PatientNotFoundException(Long patientId) {
        super("Patient with ID - " + patientId + " not found.");
    }
    public PatientNotFoundException(String message) {
        super(message);
    }
}
