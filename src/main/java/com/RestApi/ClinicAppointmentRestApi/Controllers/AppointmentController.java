package com.RestApi.ClinicAppointmentRestApi.Controllers;

import com.RestApi.ClinicAppointmentRestApi.DTOs.AppointmentDTO;
import com.RestApi.ClinicAppointmentRestApi.Entities.Appointment;
import com.RestApi.ClinicAppointmentRestApi.Exceptions.*;
import com.RestApi.ClinicAppointmentRestApi.Mapper.AppointmentMapper;
import com.RestApi.ClinicAppointmentRestApi.DTOs.AppointmentDateAndTimeRequest;
import com.RestApi.ClinicAppointmentRestApi.DTOs.AppointmentRequest;
import com.RestApi.ClinicAppointmentRestApi.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/appointments")
public class AppointmentController {

    private AppointmentService appointmentService;
    private AppointmentMapper appointmentMapper;

    @Autowired
    public AppointmentController(AppointmentService appointmentService,
                                 AppointmentMapper appointmentMapper) {
        this.appointmentService = appointmentService;
        this.appointmentMapper = appointmentMapper;
    }

    // Create new appointment
    @PostMapping("/create")
    public ResponseEntity<AppointmentDTO> create (@Valid @RequestBody AppointmentRequest appointmentRequest) {

        try {
            AppointmentDTO appointmentDTO = appointmentService.create(appointmentRequest);
            return new ResponseEntity<>(appointmentDTO, HttpStatus.CREATED);
        } catch (DoctorNotFoundException | DoctorNotAvailableException | PatientNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    // Find appointment record by id (convert to dto)
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> findAppointmentById(@PathVariable("id") Long appointmentId) {
        try {
            AppointmentDTO appointmentDTO = appointmentService.findAppointmentById(appointmentId);
            return new ResponseEntity<>(appointmentDTO, HttpStatus.OK);
        } catch (AppointmentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    // Fetch all appointment records then convert to dto
    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> findAllAppointments(@RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
                                                                    @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                                                    @RequestParam(value = "sortBy", defaultValue = "appointmentId", required = false) String sortBy,
                                                                    @RequestParam(value = "order", defaultValue = "ascending", required = false) String order){
        Sort sort;
        if (order.equalsIgnoreCase("ascending")) {
            sort = Sort.by((sortBy)).ascending();
        } else {
            sort = Sort.by((sortBy)).descending();
        }
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        List<AppointmentDTO> appointmentDTOList = appointmentService.findAllAppointments(pageable);
        if (appointmentDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(appointmentDTOList, HttpStatus.OK);
        }
    }

    // Fetch all appointments by Patient Name
    @GetMapping("/patient-name")
    public ResponseEntity<List<AppointmentDTO>> findAppointmentsByPatientName(@RequestParam String firstName,
                                                                              @RequestParam String lastName) {

        try {
            List<AppointmentDTO> appointmentsByPatientName = appointmentService.findAppointmentsByPatientName(firstName, lastName);
            return new ResponseEntity<>(appointmentsByPatientName, HttpStatus.OK);
        } catch (AppointmentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    // Find all appointments by date
    @GetMapping("/query")
    public ResponseEntity<List<AppointmentDTO>> findAllAppointmentsByDate(@RequestParam(value = "date") String appointmentDate) {

        try {
            List<AppointmentDTO> appointmentsByDate = appointmentService.findByAppointmentDate(appointmentDate);
            return new ResponseEntity<>(appointmentsByDate, HttpStatus.OK);
        } catch (AppointmentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    // Cancel Appointment by Appointment id
    @PutMapping("/{id}/cancel")
    public ResponseEntity<String> cancelAppointment(@PathVariable("id") Long appointmentId) {
        try {
            appointmentService.cancel(appointmentId);
            return new ResponseEntity<>("Appointment cancelled.", HttpStatus.OK);
        } catch (AppointmentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    // Change date and time of appointment by appointment id
    @PutMapping("/{id}/change-date-time")
    public ResponseEntity<AppointmentDTO> changeDateAndTime (@PathVariable("id") Long appointmentId,
                                                             @RequestBody AppointmentDateAndTimeRequest request) {
        try {
            AppointmentDTO appointmentDTO = appointmentService.changeDateAndTime(appointmentId, request);
            return new ResponseEntity<>(appointmentDTO, HttpStatus.OK);
        } catch (AppointmentNotFoundException | AppointmentCannotBeUpdatedException | PatientNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    // Change appointment's doctor by appointment id
    @PutMapping("/{appointmentId}/doctors/{doctorId}")
    public ResponseEntity<AppointmentDTO> changeDoctor (@PathVariable("appointmentId") Long appointmentId,
                                                        @PathVariable("doctorId") Long doctorId) {

        try {
            AppointmentDTO appointmentDTO = appointmentService.changeDoctor(appointmentId, doctorId);

            return new ResponseEntity<>(appointmentDTO, HttpStatus.OK);

        } catch (AppointmentNotFoundException | DoctorNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
