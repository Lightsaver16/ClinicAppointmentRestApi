package com.RestApi.ClinicAppointmentRestApi.Service.Implementations;

import com.RestApi.ClinicAppointmentRestApi.Entities.Appointment;
import com.RestApi.ClinicAppointmentRestApi.Entities.Doctor;
import com.RestApi.ClinicAppointmentRestApi.Entities.Enums.AppointmentStatus;
import com.RestApi.ClinicAppointmentRestApi.Entities.Enums.Availability;
import com.RestApi.ClinicAppointmentRestApi.Entities.Patient;
import com.RestApi.ClinicAppointmentRestApi.Exceptions.*;
import com.RestApi.ClinicAppointmentRestApi.Repositories.AppointmentRepository;
import com.RestApi.ClinicAppointmentRestApi.Repositories.DoctorRepository;
import com.RestApi.ClinicAppointmentRestApi.Repositories.PatientRepository;
import com.RestApi.ClinicAppointmentRestApi.Request.AppointmentDateAndTimeRequest;
import com.RestApi.ClinicAppointmentRestApi.Request.AppointmentRequest;
import com.RestApi.ClinicAppointmentRestApi.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AppointmentServiceImplementation implements AppointmentService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;


    @Override
    public Appointment create(AppointmentRequest request) throws DoctorNotFoundException, DoctorNotAvailableException, PatientNotFoundException {
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(()-> new DoctorNotFoundException(request.getDoctorId()));
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(()-> new PatientNotFoundException(request.getPatientId()));

        Appointment appointment = new Appointment();

        appointment.setDoctor(doctor);
        if (!(appointment.getDoctor().getAvailability() == Availability.AVAILABLE)) {
            throw new DoctorNotAvailableException();
        }
        appointment.setPatient(patient);
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setAppointmentStatus(AppointmentStatus.BOOKED);

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment findAppointmentById(Long appointmentId) throws AppointmentNotFoundException {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new AppointmentNotFoundException(appointmentId));
    }
    @Override
    public List<Appointment> appointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> findAppointmentsByPatientName(String firstName, String lastName) throws AppointmentNotFoundException {
        List<Appointment> appointmentList = appointmentRepository.findAppointmentsByPatientName(firstName,
                lastName);
        if (appointmentList.isEmpty()) {
            throw new AppointmentNotFoundException("Appointments for patient = " + firstName
                    + " " + lastName + " not found.");
        }
        return appointmentList;
    }
    @Override
    public List<Appointment> findByAppointmentDate(String appointmentDate) throws AppointmentNotFoundException {
        List<Appointment> appointments = appointmentRepository.findByAppointmentDate(appointmentDate);
        if (appointments.isEmpty()){
            throw new AppointmentNotFoundException("Appointment with date = " + appointmentDate + " not found.");
        }
        return appointments;
    }
    @Override
    public void cancel(Long appointmentId) throws AppointmentNotFoundException {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentNotFoundException(appointmentId));
        appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
    }

    @Override
    public Appointment changeDateAndTime(Long appointmentId, AppointmentDateAndTimeRequest request) throws AppointmentNotFoundException{
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

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment changeDoctor(Long appointmentId, Long doctorId) throws AppointmentNotFoundException, DoctorNotFoundException {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new AppointmentNotFoundException(appointmentId));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(()-> new DoctorNotFoundException(doctorId));

        appointment.setDoctor(doctor);

        return appointmentRepository.save(appointment);

    }


}
