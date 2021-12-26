package com.example.barbershop.project;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findById(long id);
    List<Project> findByName(String name);
}
