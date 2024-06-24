package com.example.demo.services;
import com.example.demo.entites.Ressources;
import com.example.demo.entites.User;
import com.example.demo.entites.projet;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public interface IRessource {

    List<Ressources> getAllRessources();

    Optional<Ressources> getRessourceWithID(Long id);
    Integer removeRessouce(Long idRessources, String idUser);

    Ressources updateRessource(Ressources r, Long idRessource);

    Ressources addRessourcewithIdUser(Ressources r, String idUser);
}
