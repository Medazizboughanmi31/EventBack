package com.example.demo.services;

import com.example.demo.entites.Affectation;
import com.example.demo.entites.Ressources;
import com.example.demo.entites.projet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface IAffectation {

    List<Affectation> getAllAffectations();
    Optional<Affectation> getAffectationWithID(Long id);

    List<String> addAffectation(Affectation affectation , String idUser);


    List<String> getExceededMonths(Affectation newAffectation, Ressources resource);
    Affectation updateAffectation(Affectation affectation, Long idAffectation);
    void deleteAffectation(Long idAffectation);
}
