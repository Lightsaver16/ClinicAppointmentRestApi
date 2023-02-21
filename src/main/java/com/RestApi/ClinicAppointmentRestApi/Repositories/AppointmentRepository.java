package com.RestApi.ClinicAppointmentRestApi.Repositories;

import com.RestApi.ClinicAppointmentRestApi.Entities.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByAppointmentDate(String appointmentDate);

    @Query("SELECT a from Appointment a JOIN Patient p ON a.patient = p " +
            "WHERE p.firstName = :firstName AND p.lastName = :lastName")
    List<Appointment> findAppointmentsByPatientName(@Param("firstName") String firstName,
                                                    @Param("lastName") String lastName);
}
