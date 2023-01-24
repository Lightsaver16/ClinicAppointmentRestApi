package com.RestApi.ClinicAppointmentRestApi.Entities;

import com.RestApi.ClinicAppointmentRestApi.Entities.Enums.AppointmentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "appointments")
public class Appointment {

    @Id
    @SequenceGenerator(name = "appointment_sequence",
            sequenceName = "appointment_sequence",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_sequence")
    private Long appointmentId;

    @NotBlank(message = "Appointment Date must not be blank.")
    private String appointmentDate;

    @NotBlank(message = "Appointment Time must not be blank.")
    private String appointmentTime;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctorId")
    @JsonIgnore
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patientId")
    @JsonIgnore
    private Patient patient;

    @Enumerated(value = EnumType.STRING)
    private AppointmentStatus appointmentStatus;
}
