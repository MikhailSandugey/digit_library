package com.example.digitlib.services;

import com.example.digitlib.models.Admin;
import com.example.digitlib.repositories.AdminsRepository;
import com.example.digitlib.security.AdminDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminDetailsService implements UserDetailsService {

    private final AdminsRepository adminsRepository;

    @Autowired
    public AdminDetailsService(AdminsRepository adminsRepository) {
        this.adminsRepository = adminsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admin = adminsRepository.findByUsername(username);
        if (admin.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }
        return new AdminDetails(admin.get());
    }
}
