package com.RestApi.ClinicAppointmentRestApi.Controllers;

import com.RestApi.ClinicAppointmentRestApi.Entities.Doctor;
import com.RestApi.ClinicAppointmentRestApi.Exceptions.DoctorNotFoundException;
import com.RestApi.ClinicAppointmentRestApi.Service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/doctors")
public class DoctorController {

    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Create a new record of doctor
    @PostMapping
    public ResponseEntity<Doctor> create(@Valid @RequestBody Doctor doctor) {
        Doctor doctorRecord = doctorService.create(doctor);

        return new ResponseEntity<>(doctorRecord, HttpStatus.CREATED);
    }

    // Fetch a record of doctor by id
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> findDoctorById(@PathVariable("id") Long doctorId) {
        try {
            Doctor doctor = doctorService.findDoctorById(doctorId);
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } catch (DoctorNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Get all records of doctors
    @GetMapping
    public ResponseEntity<List<Doctor>> doctors() {
        List<Doctor> doctors = doctorService.doctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    // Fetch a record of doctor by name
    @GetMapping("/query")
    public ResponseEntity<Doctor> findDoctorByName(@RequestParam String firstName,
                                                   @RequestParam String lastName) {
        try {
            Doctor doctor = doctorService.findDoctorByFirstNameAndLastName(firstName, lastName);

            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } catch (DoctorNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Update a record of doctor by id
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> update(@PathVariable("id") Long doctorId,
                                         @RequestBody Doctor doctor) {
        try {
            Doctor doc = doctorService.update(doctorId, doctor);
            return new ResponseEntity<>(doc, HttpStatus.OK);
        } catch (DoctorNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete a record of doctor by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long doctorId) {
        try {
            doctorService.delete(doctorId);
            return new ResponseEntity<>("Doctor record deleted successfully.", HttpStatus.OK);
        } catch (DoctorNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Make the doctor unavailable by querying the name
    @PutMapping("/unavailable/query")
    public ResponseEntity<String> unavailable(@RequestParam String firstName,
                                              @RequestParam String lastName) {
        try {
            doctorService.unavailable(firstName, lastName);

            String message = "Dr. " + firstName + " " + lastName + " is now unavailable.";

            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (DoctorNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Make the doctor available by querying the name
    @PutMapping("/available/query")
    public ResponseEntity<String> available(@RequestParam String firstName,
                                            @RequestParam String lastName) {
        try {
            doctorService.available(firstName, lastName);

            String message = "Dr. " + firstName + " " + lastName + " is now available.";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (DoctorNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
