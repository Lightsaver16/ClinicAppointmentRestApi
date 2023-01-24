package com.RestApi.ClinicAppointmentRestApi.DTOs;

import com.RestApi.ClinicAppointmentRestApi.Entities.Enums.AppointmentStatus;
import lombok.Data;

@Data
public class AppointmentDTOInPatient {

    private Long appointmentId;
    private String appointmentDate;
    private String appointmentTime;
    private String doctorFirstName;
    private String doctorLastName;
    private AppointmentStatus status;
}
