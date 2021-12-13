package com.das.project.controller;

import com.das.project.dto.AdoptionDto;
import com.das.project.dto.PetDto;
import com.das.project.model.User;
import com.das.project.service.AdoptionService;
import com.das.project.service.PetService;
import com.das.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AdoptionController {

    private final AdoptionService adoptionService;
    private final UserService userService;
    private final PetService petService;

    @Autowired
    public AdoptionController(AdoptionService adoptionService,
                              UserService userService,
                              PetService petService) {
        this.adoptionService = adoptionService;
        this.userService = userService;
        this.petService = petService;
    }

    @GetMapping("/adoption")
    public String getPetsPage(Model model) {
        model.addAttribute("adoption", adoptionService.findAll());
        return "adoption/adoptions";
    }

    @GetMapping("/adoptions/add")
    public String getAdoptionsPage(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User loggedUser = userService.findByEmail(email);
        List<PetDto> pets = petService.getAvailablePets();

        AdoptionDto adoptionDto = new AdoptionDto(loggedUser, null, null, null, null);

        model.addAttribute("adoptionDto", adoptionDto);
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("pets", pets);
        model.addAttribute("localDate", LocalDate.now());

        return "adoption/adoption-add";
    }

    @PostMapping("/adoptions/add")
    public String adoptPet(@ModelAttribute AdoptionDto adoptionDto){
        adoptionService.save(adoptionDto);
        return "redirect:/home";
    }

    @GetMapping("/adoptions/about")
    public String getAboutAdoptionsPage(){
        return "adoption/adoptions-about";
    }

}
