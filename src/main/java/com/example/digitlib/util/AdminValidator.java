package com.example.digitlib.util;

import com.example.digitlib.model.Admin;
import com.example.digitlib.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AdminValidator implements Validator {

    private final AdminServiceImpl adminServiceImpl;

    @Autowired
    public AdminValidator(AdminServiceImpl adminServiceImpl) {
        this.adminServiceImpl = adminServiceImpl;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Admin.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Admin admin = (Admin) target;
        if (adminServiceImpl.getByUsername(admin.getUsername()).isPresent()) {
            errors.rejectValue("username", "", "Admin with this name is already exist!");
        }
    }
}
