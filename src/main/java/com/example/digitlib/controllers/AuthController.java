package com.example.digitlib.controllers;

import com.example.digitlib.models.Admin;
import com.example.digitlib.services.RegistrationService;
import com.example.digitlib.util.AdminValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final AdminValidator adminValidator;

    @Autowired
    public AuthController(RegistrationService registrationService, AdminValidator adminValidator) {
        this.registrationService = registrationService;
        this.adminValidator = adminValidator;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("admin") Admin admin) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("admin") @Valid Admin admin,
                                      BindingResult bindingResult) {
        adminValidator.validate(admin, bindingResult);
        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }
        registrationService.registerAdmin(admin);
        return "redirect:/auth/login";
    }
}
