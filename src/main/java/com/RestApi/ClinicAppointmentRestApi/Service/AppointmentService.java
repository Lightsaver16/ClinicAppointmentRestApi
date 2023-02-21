package com.RestApi.ClinicAppointmentRestApi.Service;

import com.RestApi.ClinicAppointmentRestApi.DTOs.AppointmentDTO;
import com.RestApi.ClinicAppointmentRestApi.Entities.Appointment;
import com.RestApi.ClinicAppointmentRestApi.Exceptions.*;
import com.RestApi.ClinicAppointmentRestApi.DTOs.AppointmentDateAndTimeRequest;
import com.RestApi.ClinicAppointmentRestApi.DTOs.AppointmentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AppointmentService {
    AppointmentDTO create(AppointmentRequest request) throws DoctorNotFoundException, DoctorNotAvailableException, PatientNotFoundException;
    AppointmentDTO findAppointmentById(Long AppointmentId) throws AppointmentNotFoundException;
    List<AppointmentDTO> findAllAppointments(Pageable pageable);
    List<AppointmentDTO> findAppointmentsByPatientName(String firstName, String lastName) throws AppointmentNotFoundException;
    List<AppointmentDTO> findByAppointmentDate(String appointmentDate) throws AppointmentNotFoundException;
    void cancel(Long appointmentId) throws AppointmentNotFoundException;
    AppointmentDTO changeDateAndTime(Long appointmentId, AppointmentDateAndTimeRequest request) throws AppointmentNotFoundException, AppointmentCannotBeUpdatedException, PatientNotFoundException;
    AppointmentDTO changeDoctor(Long appointmentId, Long doctorId) throws AppointmentNotFoundException, DoctorNotFoundException;
}
