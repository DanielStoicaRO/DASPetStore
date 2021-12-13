package com.das.project.controller;

import com.das.project.dto.UserDto;
import com.das.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "user/register";
    }

    @PostMapping("register/add")
    public String register(@ModelAttribute("userDto") UserDto userDto) {
        userService.save(userDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("userDto") UserDto userDto) {
        userService.findByEmail(userDto.getEmail());
        return "redirect:/home";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin/admin";
    }
}