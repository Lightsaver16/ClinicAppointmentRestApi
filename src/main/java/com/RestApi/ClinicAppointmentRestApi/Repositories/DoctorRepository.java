package com.RestApi.ClinicAppointmentRestApi.Repositories;

import com.RestApi.ClinicAppointmentRestApi.Entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByFirstNameAndLastName(String firstName, String lastName);
}
