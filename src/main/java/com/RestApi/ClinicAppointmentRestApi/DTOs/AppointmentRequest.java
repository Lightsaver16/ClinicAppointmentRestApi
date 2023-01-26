package com.RestApi.ClinicAppointmentRestApi.DTOs;

import lombok.Data;

@Data
public class AppointmentRequest {

    private Long doctorId;
    private Long patientId;
    private String appointmentDate;
    private String appointmentTime;
}
