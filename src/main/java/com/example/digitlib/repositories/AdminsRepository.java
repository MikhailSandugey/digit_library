package com.example.digitlib.repositories;

import com.example.digitlib.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminsRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByUsername(String username);
}
