package com.RestApi.ClinicAppointmentRestApi.Service;

import com.RestApi.ClinicAppointmentRestApi.DTOs.AppointmentDTOInPatient;
import com.RestApi.ClinicAppointmentRestApi.Entities.Appointment;
import com.RestApi.ClinicAppointmentRestApi.Entities.Patient;
import com.RestApi.ClinicAppointmentRestApi.Exceptions.PatientNotFoundException;

import java.util.List;

public interface PatientService {
    Patient create(Patient patient);

    Patient findPatientById(Long patientId) throws PatientNotFoundException;

    List<Patient> patients();

    Patient findByFirstNameAndLastName(String firstName, String lastName) throws PatientNotFoundException;

    Patient update(Long patientId, Patient patient) throws PatientNotFoundException;

    void delete(Long patientId) throws PatientNotFoundException;
    List<AppointmentDTOInPatient> appointmentsByPatient(Long patientId) throws PatientNotFoundException;

}
