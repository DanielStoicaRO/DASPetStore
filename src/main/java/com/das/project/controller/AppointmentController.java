package com.das.project.controller;

import com.das.project.dto.AppointmentDto;
import com.das.project.dto.AppointmentInfo;
import com.das.project.dto.PetDto;
import com.das.project.model.User;
import com.das.project.service.AppointmentService;
import com.das.project.service.PetService;
import com.das.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class AppointmentController {

    public final AppointmentService appointmentService;
    public final UserService userService;
    public final PetService petService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService,
                                 UserService userService,
                                 PetService petService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
        this.petService = petService;
    }

    @GetMapping("/appointments")
    public String getAppointmentsPage(Model model) {
        model.addAttribute("appointmentsDto", appointmentService.findAll());
        return "appointment/appointments";
    }

    @GetMapping("/appointments/add")
    public String getAppointmentForm(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User loggedUser = userService.findByEmail(email);
        List<PetDto> pets = petService.getAvailablePets();

        AppointmentDto appointmentDto = new AppointmentDto(loggedUser, null, null);

        model.addAttribute("appointmentDto", appointmentDto);
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("pets", pets);
        model.addAttribute("LocalDateTime", LocalDateTime.now());

        return "appointment/appointment-add";
    }

    @PostMapping("/appointments/add")
    public String addAppointmentForm(AppointmentDto appointmentDto) {
        appointmentService.save(appointmentDto);
        return "redirect:/home";
    }

    @GetMapping("/appointments/about")
    public String getAppointmentsAboutPage() {
        return "appointment/appointments-about";
    }

    @GetMapping("/my-appointments")
    public String getMyAppointmentsPage(Model model) {
        User user = userService.findLoggedUser();
        List<AppointmentInfo> myAppointments = appointmentService.findAppointmentsByUser(user);
        model.addAttribute("myAppointmentsInfo", myAppointments);
        return "appointment/my-appointments";
    }

}
