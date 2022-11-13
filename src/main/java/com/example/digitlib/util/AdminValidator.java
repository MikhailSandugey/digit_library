package com.example.digitlib.util;

import com.example.digitlib.models.Admin;
import com.example.digitlib.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AdminValidator implements Validator {

    private final AdminService adminService;

    @Autowired
    public AdminValidator(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Admin.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Admin admin = (Admin) target;
        if (adminService.getByUsername(admin.getUsername()).isPresent()) {
            errors.rejectValue("username", "", "Admin with this name is already exist!");
        }
    }
}
