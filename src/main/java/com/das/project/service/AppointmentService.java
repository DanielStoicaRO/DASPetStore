package com.das.project.service;

import com.das.project.dto.AppointmentDto;
import com.das.project.dto.AppointmentInfo;
import com.das.project.mapper.AppointmentMapper;
import com.das.project.model.Appointment;
import com.das.project.model.User;
import com.das.project.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final UserService userService;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,
                              AppointmentMapper appointmentMapper, UserService userService) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.userService = userService;
    }


    public List<AppointmentDto> findAll() {
        return appointmentRepository.findAll().stream()
                .map(appointment -> appointmentMapper.map(appointment))
                .collect(Collectors.toList());
    }

    public Appointment save(AppointmentDto appointmentDto) {
        appointmentDto.setUser(userService.findLoggedUser());

        return appointmentRepository.save(appointmentMapper.map(appointmentDto));
    }

    public List<AppointmentInfo> findAppointmentsByUser(User user) {
        return appointmentRepository.findAll().stream()
                .filter(appointment -> appointment.getUser().equals(user))
                .map(appointment -> appointmentMapper.mapFromAppointmentToAppointmentInfo(appointment))
                .collect(Collectors.toList());

    }
}
