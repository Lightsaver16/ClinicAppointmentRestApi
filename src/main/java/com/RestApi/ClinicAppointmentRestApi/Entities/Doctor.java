package com.RestApi.ClinicAppointmentRestApi.Entities;

import com.RestApi.ClinicAppointmentRestApi.Entities.Enums.AppointmentStatus;
import com.RestApi.ClinicAppointmentRestApi.Entities.Enums.Availability;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@Table(name = "doctors")
public class Doctor {

    @Id
    @SequenceGenerator(name = "doctor_sequence",
            sequenceName = "doctor_sequence",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_sequence")
    private Long doctorId;

    @NotBlank(message = "First name should not be blank.")
    @Column(name = "doctor_first_name")
    private String firstName;

    @NotBlank(message = "Last name should not be blank.")
    @Column(name = "doctor_last_name")
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
    private List<Appointment> appointments;

    @Enumerated(value = EnumType.STRING)
    private Availability availability;
}
