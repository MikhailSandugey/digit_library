package com.example.digitlib.service;

import com.example.digitlib.model.Admin;

/**
 * Interface for RegistrationService entity
 */
public interface RegistrationService {

    /**
     * Creates new Admin
     * @param admin Admin
     */
    void registerAdmin(Admin admin);
}
