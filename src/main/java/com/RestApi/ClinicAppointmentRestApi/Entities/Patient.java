package com.RestApi.ClinicAppointmentRestApi.Entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@Table(name = "patients")
public class Patient {

    @Id
    @SequenceGenerator(name = "patient_sequence",
            sequenceName = "patient_sequence",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_sequence")
    private Long patientId;

    @NotBlank
    @Column(name = "patient_first_name")
    private String firstName;

    @NotBlank
    @Column(name = "patient_last_name")
    private String lastName;

    @Column(name = "patient_contact_number")
    private String contactNumber;

    @NotBlank
    @Column(name = "patient_address")
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private List<Appointment> appointments;

}
