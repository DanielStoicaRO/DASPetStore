package com.das.project.mapper;

import com.das.project.dto.AdoptionDto;
import com.das.project.model.Adoption;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AdoptionMapper {

    public Adoption toEntity(AdoptionDto dto) {
        Adoption adoption = new Adoption();

        adoption.setAdoptionDate(LocalDate.parse(dto.getDate()));
        adoption.setSocialSecurityNumber(dto.getSocialSecurityNumber());
        adoption.setAddress(dto.getAddress());
        return adoption;
    }

    public AdoptionDto toDto(Adoption adoption) {
        AdoptionDto dto = new AdoptionDto();

        dto.setDate(String.valueOf(adoption.getAdoptionDate()));
        dto.setSocialSecurityNumber(adoption.getSocialSecurityNumber());
        dto.setAddress(adoption.getAddress());
        dto.setPet(adoption.getPet());
        dto.setUser(adoption.getUser());
        return dto;

    }

}
