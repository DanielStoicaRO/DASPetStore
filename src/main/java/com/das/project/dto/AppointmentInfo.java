package com.das.project.dto;

import com.das.project.model.Pet;
import com.das.project.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class AppointmentInfo {

    private LocalDateTime date;
    private Set<Pet> pets;
    private User user;

}
