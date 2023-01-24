package com.RestApi.ClinicAppointmentRestApi.Service.Implementations;

import com.RestApi.ClinicAppointmentRestApi.Entities.Appointment;
import com.RestApi.ClinicAppointmentRestApi.Entities.Patient;
import com.RestApi.ClinicAppointmentRestApi.Exceptions.PatientNotFoundException;
import com.RestApi.ClinicAppointmentRestApi.Repositories.PatientRepository;
import com.RestApi.ClinicAppointmentRestApi.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PatientServiceImplementation implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient create(Patient patient) {
        return patientRepository.save(patient);
    }
    @Override
    public Patient findPatientById(Long patientId) throws PatientNotFoundException {
        return patientRepository.findById(patientId)
                .orElseThrow(()-> new PatientNotFoundException(patientId));
    }
    @Override
    public List<Patient> patients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findByFirstNameAndLastName(String firstName, String lastName) throws PatientNotFoundException {
        return patientRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(
                        ()-> new PatientNotFoundException("Patient with " + firstName + " and " + lastName
                                + " not found."));
    }

    @Override
    public Patient update(Long patientId, Patient patient) throws PatientNotFoundException {
        Patient patientDB = patientRepository.findById(patientId)
                .orElseThrow(()-> new PatientNotFoundException(patientId));

        if (Objects.nonNull(patient.getFirstName()) &&
                !"".equalsIgnoreCase(patient.getFirstName())) {
            patientDB.setFirstName(patient.getFirstName());
        }

        if (Objects.nonNull(patient.getLastName()) &&
                !"".equalsIgnoreCase(patient.getLastName())) {
            patientDB.setLastName(patient.getLastName());
        }

        if (Objects.nonNull(patient.getContactNumber()) &&
                !"".equalsIgnoreCase(patient.getContactNumber())) {
            patientDB.setContactNumber(patient.getContactNumber());
        }

        if (Objects.nonNull(patient.getAddress()) &&
                !"".equalsIgnoreCase(patient.getAddress())) {
            patientDB.setAddress(patient.getAddress());
        }

        return patientRepository.save(patientDB);

    }

    @Override
    public void delete(Long patientId) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new PatientNotFoundException(patientId));

        patientRepository.delete(patient);
    }

    @Override
    public List<Appointment> appointmentsByPatient(Long patientId) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new PatientNotFoundException(patientId));

        return patient.getAppointments();
    }

}
