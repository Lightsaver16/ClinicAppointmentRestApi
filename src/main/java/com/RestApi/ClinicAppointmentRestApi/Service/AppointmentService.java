package com.RestApi.ClinicAppointmentRestApi.Service;

import com.RestApi.ClinicAppointmentRestApi.Entities.Appointment;
import com.RestApi.ClinicAppointmentRestApi.Exceptions.*;
import com.RestApi.ClinicAppointmentRestApi.Request.AppointmentDateAndTimeRequest;
import com.RestApi.ClinicAppointmentRestApi.Request.AppointmentRequest;

import java.util.List;

public interface AppointmentService {
    Appointment create(AppointmentRequest request) throws DoctorNotFoundException, DoctorNotAvailableException, PatientNotFoundException;
    Appointment findAppointmentById(Long AppointmentId) throws AppointmentNotFoundException;
    List<Appointment> appointments();

    List<Appointment> findAppointmentsByPatientName(String firstName, String lastName) throws AppointmentNotFoundException;
    List<Appointment> findByAppointmentDate(String appointmentDate) throws AppointmentNotFoundException;
    void cancel(Long appointmentId) throws AppointmentNotFoundException;
    Appointment changeDateAndTime(Long appointmentId, AppointmentDateAndTimeRequest request) throws AppointmentNotFoundException, AppointmentCannotBeUpdatedException, PatientNotFoundException;

    Appointment changeDoctor(Long appointmentId, Long doctorId) throws AppointmentNotFoundException, DoctorNotFoundException;
}
