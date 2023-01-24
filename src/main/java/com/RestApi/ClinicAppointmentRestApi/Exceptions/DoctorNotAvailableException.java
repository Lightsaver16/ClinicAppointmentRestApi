package com.RestApi.ClinicAppointmentRestApi.Exceptions;

public class DoctorNotAvailableException extends Exception{
    public DoctorNotAvailableException() {
        super("Doctor is not available.");
    }
}
