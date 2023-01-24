package com.RestApi.ClinicAppointmentRestApi.Controllers;

import com.RestApi.ClinicAppointmentRestApi.DTOs.AppointmentDTO;
import com.RestApi.ClinicAppointmentRestApi.Entities.Appointment;
import com.RestApi.ClinicAppointmentRestApi.Exceptions.*;
import com.RestApi.ClinicAppointmentRestApi.Mapper.AppointmentMapper;
import com.RestApi.ClinicAppointmentRestApi.Request.AppointmentDateAndTimeRequest;
import com.RestApi.ClinicAppointmentRestApi.Request.AppointmentRequest;
import com.RestApi.ClinicAppointmentRestApi.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentMapper appointmentMapper;

    // Create new appointment
    @PostMapping
    public ResponseEntity<AppointmentDTO> create (@Valid @RequestBody AppointmentRequest appointmentRequest) {
        try {
            AppointmentDTO appointmentDTO = appointmentMapper.toAppointmentDTO(
                    appointmentService.create(appointmentRequest));

            return ResponseEntity.ok(appointmentDTO);
        } catch (DoctorNotFoundException | PatientNotFoundException | DoctorNotAvailableException e) {
            throw new RuntimeException(e);
        }
    }
    // Find appointment record by id (convert to dto)
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> findAppointmentById(@PathVariable("id") Long appointmentId) {
        try {
            Appointment appointment = appointmentService.findAppointmentById(appointmentId);
            AppointmentDTO appointmentDTO = appointmentMapper.toAppointmentDTO(appointment);
            return ResponseEntity.ok(appointmentDTO);
        } catch (AppointmentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Fetch all appointment records then convert to dto
    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> appointments(){
        List<AppointmentDTO> appointmentDTOS = appointmentService.appointments()
                .stream()
                .map(appointmentMapper::toAppointmentDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(appointmentDTOS);
    }

    // Find all appointments by date
    @GetMapping("/query")
    public ResponseEntity<List<AppointmentDTO>> appointmentsByDate(
            @RequestParam String appointmentDate) {

        try {
            List<AppointmentDTO> appointmentDTOList = appointmentService.findByAppointmentDate(appointmentDate)
                    .stream()
                    .map(appointmentMapper::toAppointmentDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(appointmentDTOList);
        } catch (AppointmentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    // Fetch all appointments by Patient Name
    @GetMapping("/patient")
    public ResponseEntity<List<AppointmentDTO>> appointmentsByPatientName(@RequestParam String firstName,
                                                                          @RequestParam String lastName) {
        try {
            List<AppointmentDTO> appointmentsByPatientName = appointmentService.findAppointmentsByPatientName(firstName,
                            lastName).stream()
                    .map(appointmentMapper::toAppointmentDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(appointmentsByPatientName);
        } catch (AppointmentNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    // Cancel Appointment by Appointment id
    @PutMapping("/{id}")
    public ResponseEntity<String> cancelAppointment(@PathVariable("id") Long appointmentId) {
        try {
            Appointment appointment = appointmentService.findAppointmentById(appointmentId);
            return ResponseEntity.ok("Appointment cancelled");
        } catch (AppointmentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    // Change date and time of appointment by appointment id
    @PutMapping("/{id}/change-date-time")
    public ResponseEntity<Appointment> changeDateAndTime (@PathVariable("id") Long appointmentId,
                                                          @RequestBody AppointmentDateAndTimeRequest request) {
        try {
            return ResponseEntity.ok(appointmentService.changeDateAndTime(appointmentId, request));
        } catch (AppointmentNotFoundException | AppointmentCannotBeUpdatedException | PatientNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    // Change appointment's doctor by appointment id
    @PutMapping("/{appointmentId}/doctors/{doctorId}")
    public ResponseEntity<AppointmentDTO> changeDoctor (@PathVariable("appointmentId") Long appointmentId,
                                                        @PathVariable("doctorId") Long doctorId) {

        try {
            AppointmentDTO appointmentDTO = appointmentMapper.toAppointmentDTO(
                    appointmentService.changeDoctor(appointmentId, doctorId));

            return ResponseEntity.ok(appointmentDTO);

        } catch (AppointmentNotFoundException | DoctorNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
