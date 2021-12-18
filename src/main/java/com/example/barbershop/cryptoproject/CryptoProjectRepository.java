package com.example.barbershop.cryptoproject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CryptoProjectRepository extends JpaRepository<CryptoProject, Long> {
    CryptoProject findById(long id);
    List<CryptoProject> findByName(String name);
}
