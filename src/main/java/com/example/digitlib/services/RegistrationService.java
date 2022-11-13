package com.example.digitlib.services;

import com.example.digitlib.models.Admin;
import com.example.digitlib.repositories.AdminsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RegistrationService {

    private final AdminsRepository adminsRepository;

    @Autowired
    public RegistrationService(AdminsRepository adminsRepository) {
        this.adminsRepository = adminsRepository;
    }

    @Transactional
    public void registerAdmin(Admin admin) {
        adminsRepository.save(admin);
    }
}
