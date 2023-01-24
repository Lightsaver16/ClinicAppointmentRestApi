package com.RestApi.ClinicAppointmentRestApi.ExceptionHandler;

import com.RestApi.ClinicAppointmentRestApi.Exceptions.AppointmentNotFoundException;
import com.RestApi.ClinicAppointmentRestApi.Exceptions.DoctorNotFoundException;
import com.RestApi.ClinicAppointmentRestApi.Exceptions.PatientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(AppointmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String appointmentNotFound(AppointmentNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String doctorNotFound(DoctorNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(PatientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String patientNotFound(PatientNotFoundException e) {
        return e.getMessage();
    }
}
