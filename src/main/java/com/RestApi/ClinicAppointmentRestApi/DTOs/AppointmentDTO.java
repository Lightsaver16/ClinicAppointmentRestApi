package com.RestApi.ClinicAppointmentRestApi.DTOs;

import com.RestApi.ClinicAppointmentRestApi.Entities.Enums.AppointmentStatus;
import lombok.Data;

@Data
public class AppointmentDTO {
    private Long appointmentId;
    private String appointmentDate;
    private String appointmentTime;
    private String patientFirstName;
    private String patientLastName;
    private String doctorFirstName;
    private String doctorLastName;
    private AppointmentStatus status;
}
