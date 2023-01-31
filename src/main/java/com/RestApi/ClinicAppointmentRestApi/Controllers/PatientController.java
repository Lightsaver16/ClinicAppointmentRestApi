package com.RestApi.ClinicAppointmentRestApi.Controllers;

import com.RestApi.ClinicAppointmentRestApi.DTOs.AppointmentDTOInPatient;
import com.RestApi.ClinicAppointmentRestApi.Entities.Patient;
import com.RestApi.ClinicAppointmentRestApi.Exceptions.PatientNotFoundException;
import com.RestApi.ClinicAppointmentRestApi.Service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/patients")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // For creating new patient record
    @PostMapping("/create")
    public ResponseEntity<Patient> create(@Valid @RequestBody Patient patient) {
        Patient patientRecord = patientService.create(patient);

        return new ResponseEntity<>(patientRecord, HttpStatus.CREATED);
    }

    // Fetch patient record by id
    @GetMapping("/{id}")
    public ResponseEntity<Patient> findPatientById(@PathVariable("id") Long patientId) {
        try {
            Patient patient = patientService.findPatientById(patientId);

            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Fetch all patient records
    @GetMapping
    public ResponseEntity<List<Patient>> patients() {
        List<Patient> patients = patientService.patients();

        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // Fetch all appointments of a patient using patient's id
    // Appointments are converted to DTO
    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<AppointmentDTOInPatient>> appointmentsOfPatient(@PathVariable("id") Long patientId) {

        try {
            List<AppointmentDTOInPatient> appointmentsByPatient = patientService.appointmentsByPatient(patientId);

            return new ResponseEntity<>(appointmentsByPatient, HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Find patient by first name and last name
    @GetMapping("/query")
    public ResponseEntity<Patient> findPatientByFirstNameAndLastName(@RequestParam String firstName,
                                                                     @RequestParam String lastName) {
        try {
            Patient patient = patientService.findByFirstNameAndLastName(firstName, lastName);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<Patient> update(@PathVariable("id") Long patientId,
                                          @RequestBody Patient patient) {

        try {
            Patient patientUpdated = patientService.update(patientId, patient);
            return new ResponseEntity<>(patientUpdated, HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable("id") Long patientId) {

        try {
            patientService.delete(patientId);
            return new ResponseEntity<>("Patient record successfully deleted.", HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
