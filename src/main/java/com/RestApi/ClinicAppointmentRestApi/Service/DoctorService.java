package com.RestApi.ClinicAppointmentRestApi.Service;

import com.RestApi.ClinicAppointmentRestApi.Entities.Doctor;
import com.RestApi.ClinicAppointmentRestApi.Exceptions.DoctorNotFoundException;

import java.util.List;

public interface DoctorService {
    Doctor create(Doctor doctor);

    Doctor findDoctorById(Long doctorId) throws DoctorNotFoundException;

    List<Doctor> doctors();

    Doctor findDoctorByFirstNameAndLastName(String firstName, String lastName) throws DoctorNotFoundException;

    Doctor update(Long doctorId, Doctor doctor) throws DoctorNotFoundException;

    void delete(Long doctorId) throws DoctorNotFoundException;

    void unavailable(String firstName, String lastName) throws DoctorNotFoundException;

    void available(String firstName, String lastName) throws DoctorNotFoundException;
}
