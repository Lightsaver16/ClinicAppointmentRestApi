package com.RestApi.ClinicAppointmentRestApi.Controllers;

import com.RestApi.ClinicAppointmentRestApi.Entities.Doctor;
import com.RestApi.ClinicAppointmentRestApi.Exceptions.DoctorNotFoundException;
import com.RestApi.ClinicAppointmentRestApi.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Doctor> create(@Valid @RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.create(doctor));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> findDoctorById(@PathVariable("id") Long doctorId) {
        try {
            return ResponseEntity.ok(doctorService.findDoctorById(doctorId));
        } catch (DoctorNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping
    public ResponseEntity<List<Doctor>> doctors() {
        List<Doctor> doctors = doctorService.doctors();
        return ResponseEntity.ok(doctors);
    }
    @GetMapping("/query")
    public ResponseEntity<Doctor> findDoctorByName(@RequestParam String firstName,
                                                   @RequestParam String lastName) {
        try {
            Doctor doctor = doctorService.findDoctorByFirstNameAndLastName(firstName, lastName);
            return ResponseEntity.ok(doctor);
        } catch (DoctorNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> update(@PathVariable("id") Long doctorId,
                                         @RequestBody Doctor doctor) {
        try {
            Doctor doc = doctorService.update(doctorId, doctor);
            return ResponseEntity.ok(doc);
        } catch (DoctorNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long doctorId) {
        try {
            doctorService.delete(doctorId);
            return ResponseEntity.ok("Doctor record deleted successfully.");
        } catch (DoctorNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/unavailable/query")
    public ResponseEntity<String> unavailable(@RequestParam String firstName,
                                              @RequestParam String lastName) {
        try {
            doctorService.unavailable(firstName, lastName);
            return ResponseEntity.ok("Dr. " + firstName + " " + lastName + " is now unavailable.");
        } catch (DoctorNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("/available/query")
    public ResponseEntity<String> available(@RequestParam String firstName,
                                            @RequestParam String lastName) {
        try {
            doctorService.available(firstName, lastName);
            return ResponseEntity.ok("Dr. " + firstName + " " + lastName + " is now available.");
        } catch (DoctorNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
