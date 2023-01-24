package com.RestApi.ClinicAppointmentRestApi.Request;

import lombok.Data;

@Data
public class AppointmentDateAndTimeRequest {

    private String appointmentDate;
    private String appointmentTime;
}
