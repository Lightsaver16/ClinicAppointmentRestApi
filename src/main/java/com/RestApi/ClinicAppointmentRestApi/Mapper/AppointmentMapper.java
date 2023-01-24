package com.RestApi.ClinicAppointmentRestApi.Mapper;

import com.RestApi.ClinicAppointmentRestApi.DTOs.AppointmentDTO;
import com.RestApi.ClinicAppointmentRestApi.DTOs.AppointmentDTOInPatient;
import com.RestApi.ClinicAppointmentRestApi.Entities.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public AppointmentDTO toAppointmentDTO(Appointment appointment) {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setAppointmentId(appointment.getAppointmentId());
        appointmentDTO.setAppointmentDate(appointment.getAppointmentDate());
        appointmentDTO.setAppointmentTime(appointment.getAppointmentTime());
        appointmentDTO.setPatientFirstName(appointment.getPatient().getFirstName());
        appointmentDTO.setPatientLastName(appointment.getPatient().getLastName());
        appointmentDTO.setDoctorFirstName(appointment.getDoctor().getFirstName());
        appointmentDTO.setDoctorLastName(appointment.getDoctor().getLastName());
        appointmentDTO.setStatus(appointment.getAppointmentStatus());

        return appointmentDTO;
    }
    public AppointmentDTOInPatient toAppointmentDTOInPatient(Appointment appointment) {
        AppointmentDTOInPatient appointmentDTOInPatient = new AppointmentDTOInPatient();
        appointmentDTOInPatient.setAppointmentId(appointment.getAppointmentId());
        appointmentDTOInPatient.setAppointmentDate(appointment.getAppointmentDate());
        appointmentDTOInPatient.setAppointmentTime(appointment.getAppointmentTime());
        appointmentDTOInPatient.setDoctorFirstName(appointment.getDoctor().getFirstName());
        appointmentDTOInPatient.setDoctorLastName(appointment.getDoctor().getLastName());
        appointmentDTOInPatient.setStatus(appointment.getAppointmentStatus());

        return appointmentDTOInPatient;
    }
}
