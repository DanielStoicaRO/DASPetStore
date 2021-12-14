package com.das.project.service;

import com.das.project.controller.exception.ResourceNotFoundException;
import com.das.project.dto.PetDto;
import com.das.project.dto.PetInfo;
import com.das.project.mapper.PetMapper;
import com.das.project.model.Category;
import com.das.project.model.Pet;
import com.das.project.model.User;
import com.das.project.repository.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    private static final Logger log = LoggerFactory.getLogger(PetService.class);

    private final PetRepository petRepository;
    private final PetMapper petMapper;
    private final UserService userService;

    @Autowired
    public PetService(PetRepository petRepository, PetMapper petMapper, UserService userService) {
        this.petRepository = petRepository;
        this.petMapper = petMapper;
        this.userService = userService;
    }

    public PetDto save(PetDto petDto) {
        // TODO cosmin: use lambda
        petDto.setAvailable(true);
        Pet pet = petMapper.map(petDto);
        Pet savedPet = petRepository.save(pet);
        PetDto savedDto = petMapper.map(savedPet);
        return savedDto;
    }

    public List<PetDto> findAll() {
        return petRepository.findAll().stream()
                .map(pet -> petMapper.map(pet))
                .collect(Collectors.toList());
    }

    public Pet findByName(String name) {
        return petRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("pet not found"));
    }

    public PetDto findById(Long id) {
        log.info("finding pet with id {}", id);

        // find by id from repo
        return petRepository.findById(id)
                // convert to dto
                .map(pet -> petMapper.map(pet))
                .orElseThrow(() -> new ResourceNotFoundException("pet not found"));
    }

    public List<PetInfo> getUserPets() {
        log.info("get user pets");

        User user = userService.findLoggedUser();
        return user.getAdoptions().stream()
                .map(adoption -> adoption.getPet())
                .map(pet -> petMapper.mapFromPetToPetInfo(pet))
                .collect(Collectors.toList());
    }

    public List<PetDto> getAvailablePets() {
        log.info("get available pets");

        return findAll().stream()
                .filter(petDto -> petDto.isAvailable())
                .collect(Collectors.toList());
    }

    public void update(PetDto dto) {
        log.info("updating pet with id {} with data {}", dto.getId(), dto);

        // find entity by id
        petRepository.findById(dto.getId())
                // copy values from dto to entity
                .map(pet -> petMapper.update(pet, dto)) // transform pet to pet
                // save the updated pet
                .map(updatedPet -> petRepository.save(updatedPet))  // pet -> save pet
                .orElseThrow(() -> new ResourceNotFoundException("pet not found"));
    }

    public List<PetInfo> findByCategory(Category category) {
        log.info("find pets by category {}", category);

        return petRepository.findByCategory(category)
                .map(pets -> petMapper.mapPetsToDtos(pets))
                .orElseThrow(() -> new ResourceNotFoundException("pet not found"));
    }

    public Pet update2(PetDto dto) {
        Pet petToUpdate = petRepository.findById(dto.getId())
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("pet not found");
                });
        petToUpdate.setName(dto.getName());
        petToUpdate.setDescription(dto.getDescription());
        petToUpdate.setAvailable(dto.isAvailable());

       return petRepository.save( petToUpdate);
    }

    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }
}
