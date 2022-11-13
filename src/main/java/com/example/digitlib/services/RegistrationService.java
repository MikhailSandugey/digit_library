package com.example.digitlib.services;

import com.example.digitlib.models.Admin;
import com.example.digitlib.repositories.AdminsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RegistrationService {

    private final AdminsRepository adminsRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(AdminsRepository adminsRepository, PasswordEncoder passwordEncoder) {
        this.adminsRepository = adminsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminsRepository.save(admin);
    }
}
