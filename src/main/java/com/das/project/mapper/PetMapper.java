package com.das.project.mapper;

import com.das.project.dto.PetDto;
import com.das.project.dto.PetInfo;
import com.das.project.model.Category;
import com.das.project.model.Pet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PetMapper {

    // to dtos
    public List<PetInfo> mapPetsToDtos(List<Pet> entities) {
        return entities.stream()
                .map(pet -> mapFromPetToPetInfo(pet))
                .collect(Collectors.toList());
    }

    // toEntity(PetDto dto)
    public Pet map(PetDto dto) {
        Pet entity = new Pet();
        entity.setName(dto.getName());
        entity.setCategory(Category.valueOf(dto.getCategory()));
        entity.setDescription(dto.getDescription());
        entity.setAdoption(dto.getAdoption());
        entity.setAvailable(dto.isAvailable());
        entity.setPhoto(dto.getPhoto());
        return entity;
    }

    // update(PetDto dto, Pet entity)
    public Pet update(Pet petToUpdate, PetDto data) {
        petToUpdate.setName(data.getName());
        petToUpdate.setCategory(Category.valueOf(data.getCategory()));
        petToUpdate.setDescription(data.getDescription());
        petToUpdate.setAvailable(data.isAvailable());
        petToUpdate.setPhoto(data.getPhoto());
        return petToUpdate;
    }

    // toDto(Pet entity)
    public PetDto map(Pet entity) {
        PetDto dto = new PetDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCategory(String.valueOf(entity.getCategory()));
        dto.setDescription(entity.getDescription());
        dto.setAdoption(entity.getAdoption());
        dto.setAvailable(entity.isAvailable());
        dto.setPhoto(entity.getPhoto());
        return dto;
    }

    // toEntity(PetInfo dto)
    public Pet mapFromPetInfoToPet(PetInfo petInfo) {
        Pet entity = new Pet();
        entity.setName(petInfo.getName());
        entity.setCategory(petInfo.getCategory());
        entity.setDescription(petInfo.getDescription());
        entity.setAvailable(petInfo.isAvailable());
        entity.setPhoto(petInfo.getPhoto());
        return entity;
    }

    // to PetInfo(Pet entity)
    public PetInfo mapFromPetToPetInfo(Pet entity) {
        PetInfo petInfo = new PetInfo();
        petInfo.setName(entity.getName());
        petInfo.setCategory(entity.getCategory());
        petInfo.setDescription(entity.getDescription());
        petInfo.setAvailable(entity.isAvailable());
        petInfo.setPhoto(entity.getPhoto());
        return petInfo;
    }
}
