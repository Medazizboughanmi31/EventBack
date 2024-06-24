package com.example.demo.repository;

import com.example.demo.entites.Affectation;
import com.example.demo.entites.Ressources;
import com.example.demo.entites.projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AffectationRepo extends JpaRepository<Affectation, Long> {
    //Affectation findByResssource(Ressources ressource);

    List<Affectation> findByNomRessourceAndPrenomRessource(String nomR, String prenomR);

    Affectation findByProjetAndResssource(String projet, Ressources ressource);
}