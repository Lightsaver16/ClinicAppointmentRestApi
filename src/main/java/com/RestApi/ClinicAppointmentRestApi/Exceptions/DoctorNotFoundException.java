package com.RestApi.ClinicAppointmentRestApi.Exceptions;

public class DoctorNotFoundException extends Exception{
    public DoctorNotFoundException(Long doctorId) {
        super("Doctor with Id - " + doctorId + " not found.");
    }

    public DoctorNotFoundException(String message) {
        super(message);
    }
}
