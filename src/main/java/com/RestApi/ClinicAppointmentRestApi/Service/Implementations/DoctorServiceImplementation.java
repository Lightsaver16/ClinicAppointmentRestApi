package com.RestApi.ClinicAppointmentRestApi.Service.Implementations;

import com.RestApi.ClinicAppointmentRestApi.Entities.Doctor;
import com.RestApi.ClinicAppointmentRestApi.Entities.Enums.Availability;
import com.RestApi.ClinicAppointmentRestApi.Exceptions.DoctorNotFoundException;
import com.RestApi.ClinicAppointmentRestApi.Repositories.DoctorRepository;
import com.RestApi.ClinicAppointmentRestApi.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DoctorServiceImplementation implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor create(Doctor doctor) {
        doctor.setAvailability(Availability.AVAILABLE);
        return doctorRepository.save(doctor);
    }
    @Override
    public Doctor findDoctorById(Long doctorId) throws DoctorNotFoundException {
        return doctorRepository.findById(doctorId)
                .orElseThrow(()-> new DoctorNotFoundException(doctorId));
    }
    @Override
    public List<Doctor> doctors() {
        return doctorRepository.findAll();
    }
    @Override
    public Doctor findDoctorByFirstNameAndLastName(String firstName, String lastName) throws DoctorNotFoundException {
        return doctorRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(()-> new DoctorNotFoundException("Dr. " + firstName + " " + lastName + " not found."));
    }

    @Override
    public Doctor update(Long doctorId, Doctor doctor) throws DoctorNotFoundException {
        Doctor doctorDB = doctorRepository.findById(doctorId)
                .orElseThrow(()-> new DoctorNotFoundException(doctorId));

        if (Objects.nonNull(doctor.getFirstName()) &&
        !"".equalsIgnoreCase(doctor.getFirstName())) {
            doctorDB.setFirstName(doctor.getFirstName());
        }
        if (Objects.nonNull(doctor.getLastName()) &&
                !"".equalsIgnoreCase(doctor.getLastName())) {
            doctorDB.setLastName(doctor.getLastName());
        }

        return doctorRepository.save(doctorDB);

    }

    @Override
    public void delete(Long doctorId) throws DoctorNotFoundException {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(()-> new DoctorNotFoundException(doctorId));

        doctorRepository.delete(doctor);
    }

    @Override
    public void unavailable(String firstName, String lastName) throws DoctorNotFoundException {
        Doctor doctor = doctorRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(()-> new DoctorNotFoundException("Dr. " + firstName + " " + lastName + " not found."));
        doctor.setAvailability(Availability.UNAVAILABLE);
        doctorRepository.save(doctor);
    }
    @Override
    public void available(String firstName, String lastName) throws DoctorNotFoundException {
        Doctor doctor = doctorRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(()-> new DoctorNotFoundException("Dr. " + firstName + " " + lastName + " not found."));
        doctor.setAvailability(Availability.AVAILABLE);
        doctorRepository.save(doctor);
    }
}
