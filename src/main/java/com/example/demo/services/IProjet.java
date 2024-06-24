package com.example.demo.services;
import com.example.demo.entites.projet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface IProjet {
    projet updateProjet(projet p,Long projectId);

    Optional<projet> getProjetWithID(Long id);
    List<projet> GetAllprojet();

    Integer removeProjet(Long idprojet,  String idUser);

    projet addProjetwithIdUser(projet p, String idUser);



}
