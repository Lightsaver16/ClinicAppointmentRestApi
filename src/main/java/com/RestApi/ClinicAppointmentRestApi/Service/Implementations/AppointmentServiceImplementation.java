package com.RestApi.ClinicAppointmentRestApi.Service.Implementations;

import com.RestApi.ClinicAppointmentRestApi.DTOs.AppointmentDTO;
import com.RestApi.ClinicAppointmentRestApi.Entities.Appointment;
import com.RestApi.ClinicAppointmentRestApi.Entities.Doctor;
import com.RestApi.ClinicAppointmentRestApi.Entities.Enums.AppointmentStatus;
import com.RestApi.ClinicAppointmentRestApi.Entities.Enums.Availability;
import com.RestApi.ClinicAppointmentRestApi.Entities.Patient;
import com.RestApi.ClinicAppointmentRestApi.Exceptions.*;
import com.RestApi.ClinicAppointmentRestApi.Mapper.AppointmentMapper;
import com.RestApi.ClinicAppointmentRestApi.Repositories.AppointmentRepository;
import com.RestApi.ClinicAppointmentRestApi.Repositories.DoctorRepository;
import com.RestApi.ClinicAppointmentRestApi.Repositories.PatientRepository;
import com.RestApi.ClinicAppointmentRestApi.DTOs.AppointmentDateAndTimeRequest;
import com.RestApi.ClinicAppointmentRestApi.DTOs.AppointmentRequest;
import com.RestApi.ClinicAppointmentRestApi.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImplementation implements AppointmentService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentMapper appointmentMapper;


    @Override
    public AppointmentDTO create(AppointmentRequest request) throws DoctorNotFoundException, DoctorNotAvailableException, PatientNotFoundException {
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new DoctorNotFoundException(request.getDoctorId()));
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new PatientNotFoundException(request.getPatientId()));
        Appointment appointment = new Appointment();

        appointment.setDoctor(doctor);
        if (!(appointment.getDoctor().getAvailability() == Availability.AVAILABLE)) {
            throw new DoctorNotAvailableException();
        }

        appointment.setPatient(patient);
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setAppointmentStatus(AppointmentStatus.BOOKED);

        appointmentRepository.save(appointment);

        return appointmentMapper.toAppointmentDTO(appointment);
    }
    @Override
    public AppointmentDTO findAppointmentById(Long appointmentId) throws AppointmentNotFoundException{
        Appointment appointment = appointmentRepository.findById(appointmentId)
                    .orElseThrow(()-> new AppointmentNotFoundException(appointmentId));

        return appointmentMapper.toAppointmentDTO(appointment);
    }
    @Override
    public List<AppointmentDTO> findAllAppointments(Pageable pageable) {
        Page<Appointment> appointments = appointmentRepository.findAll(pageable);

        return appointments.getContent()
                .stream()
                .map(appointmentMapper::toAppointmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> findAppointmentsByPatientName(String firstName, String lastName, Pageable pageable) throws AppointmentNotFoundException {
        Page<Appointment> appointmentPage = appointmentRepository.findAppointmentsByPatientName(firstName, lastName, pageable);
        List<Appointment> appointmentsByPatientName = appointmentPage.getContent();

        if (appointmentsByPatientName.isEmpty()) {
            throw new AppointmentNotFoundException("Appointments for patient = " + firstName
                    + " " + lastName + " not found.");
        }
        return appointmentsByPatientName.stream()
                .map(appointmentMapper::toAppointmentDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<AppointmentDTO> findByAppointmentDate(String appointmentDate, Pageable pageable) throws AppointmentNotFoundException {
        Page<Appointment> appointments = appointmentRepository.findByAppointmentDate(appointmentDate, pageable);
        List<Appointment> appointmentList = appointments.getContent();


        if (appointmentList.isEmpty()){
            throw new AppointmentNotFoundException("Appointment with date = " + appointmentDate + " not found.");
        }

        return appointmentList.stream()
                .map(appointmentMapper::toAppointmentDTO)
                .collect(Collectors.toList());
    }
    @Override
    public void cancel(Long appointmentId) throws AppointmentNotFoundException {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentNotFoundException(appointmentId));
        appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);
    }

    @Override
    public AppointmentDTO changeDateAndTime(Long appointmentId, AppointmentDateAndTimeRequest request) throws AppointmentNotFoundException{
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new AppointmentNotFoundException(appointmentId));

        // Checks the appointment request's date is not null
        if (Objects.nonNull(request.getAppointmentDate()) &&
                !"".equalsIgnoreCase(request.getAppointmentDate())) {
            appointment.setAppointmentDate(request.getAppointmentDate());
        }

        // Checks the appointment request's time is not null
        if (Objects.nonNull(request.getAppointmentTime()) &&
                !"".equalsIgnoreCase(request.getAppointmentTime())) {
            appointment.setAppointmentTime(request.getAppointmentTime());
        }

        appointmentRepository.save(appointment);

        return appointmentMapper.toAppointmentDTO(appointment);
    }

    @Override
    public AppointmentDTO changeDoctor(Long appointmentId, Long doctorId) throws AppointmentNotFoundException, DoctorNotFoundException {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new AppointmentNotFoundException(appointmentId));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(()-> new DoctorNotFoundException(doctorId));

        appointment.setDoctor(doctor);

        appointmentRepository.save(appointment);

        return appointmentMapper.toAppointmentDTO(appointment);

    }


}
