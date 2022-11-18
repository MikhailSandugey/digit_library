package com.example.digitlib.service;

import com.example.digitlib.model.Admin;

import java.util.Optional;

/**
 * Interface for AdminService entity
 */
public interface AdminService {

    /**
     * Gets admin by username at a service layer
     * @param username username of Admin
     * @return Admin
     */
    Optional<Admin> getByUsername(String username);
}
