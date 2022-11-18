package com.example.digitlib.controller;

import com.example.digitlib.model.Admin;
import com.example.digitlib.service.impl.RegistrationServiceImpl;
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

    private final RegistrationServiceImpl registrationServiceImpl;
    private final AdminValidator adminValidator;

    @Autowired
    public AuthController(RegistrationServiceImpl registrationServiceImpl, AdminValidator adminValidator) {
        this.registrationServiceImpl = registrationServiceImpl;
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
        registrationServiceImpl.registerAdmin(admin);
        return "redirect:/auth/login";
    }
}
