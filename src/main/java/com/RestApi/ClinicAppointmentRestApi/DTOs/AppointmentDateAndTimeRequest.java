package com.RestApi.ClinicAppointmentRestApi.DTOs;

import lombok.Data;

@Data
public class AppointmentDateAndTimeRequest {

    private String appointmentDate;
    private String appointmentTime;
}
