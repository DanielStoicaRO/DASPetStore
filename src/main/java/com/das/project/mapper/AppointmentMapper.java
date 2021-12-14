package com.das.project.mapper;

import com.das.project.dto.AppointmentDto;
import com.das.project.dto.AppointmentInfo;
import com.das.project.model.Appointment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AppointmentMapper {

    public Appointment map(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        appointment.setUser(appointmentDto.getUser());
        appointment.setDate(LocalDateTime.parse(appointmentDto.getDate()));

        appointment.setPets(appointmentDto.getPets());
        return appointment;
    }

    public AppointmentDto map(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setUser(appointment.getUser());
        appointmentDto.setPets(appointment.getPets());
        appointmentDto.setDate(String.valueOf(appointment.getDate()));
        return appointmentDto;
    }

    public AppointmentInfo mapFromAppointmentToAppointmentInfo(Appointment appointment) {
        AppointmentInfo dto = new AppointmentInfo();
        dto.setDate(appointment.getDate());
        dto.setPets(appointment.getPets());
        dto.setUser(appointment.getUser());
        return dto;
    }
}
