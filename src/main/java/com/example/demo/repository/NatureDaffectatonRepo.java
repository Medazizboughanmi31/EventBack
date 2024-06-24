package com.example.demo.repository;

import com.example.demo.entites.Naturedaffectation;
import com.example.demo.entites.projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NatureDaffectatonRepo extends JpaRepository<Naturedaffectation, Long> {
    Naturedaffectation findByNameNaturedaffec(String nom);
}
