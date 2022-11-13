package com.example.digitlib.services;

import com.example.digitlib.models.Admin;
import com.example.digitlib.repositories.AdminsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AdminService {

    private final AdminsRepository adminsRepository;

    @Autowired
    public AdminService(AdminsRepository adminsRepository) {
        this.adminsRepository = adminsRepository;
    }

    public Optional<Admin> getByUsername(String username) {
        return adminsRepository.findByUsername(username);
    }
}
