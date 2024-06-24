package com.example.demo.services.impl;

import com.example.demo.entites.*;
import com.example.demo.repository.UserRepo;
import com.example.demo.repository.projetRepo;
import com.example.demo.services.IProjet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
@Slf4j
public class ProjetServices implements IProjet {
    @Autowired
    projetRepo projetRepository;

    @Autowired
    UserRepo userRepository;


    @Override
    public List<projet> GetAllprojet() {
        return projetRepository.findAll();
    }

    @Override
    public Optional<projet> getProjetWithID(Long id){
        return projetRepository.findById(id);
    }


    public static List<String> getProjetAttributes(Class<?> projetClass) {
        List<String> attributeNames = new ArrayList<>();
        Field[] fields = projetClass.getDeclaredFields();
        for (Field field : fields) {
            attributeNames.add(field.getName());
        }
        return attributeNames;
    }

    @Override
    public projet updateProjet(projet p,Long projectId) {
        projet existingProjet = projetRepository.findById(projectId).orElse(null);
        List<String> attributes = getProjetAttributes(projet.class);
        Set<String> ignoreProperties = new HashSet<>();
        for (String attribute : attributes) {
            if (p.get(attribute) == null)
                ignoreProperties.add(attribute);
        }
        String[] ignorePropertiesArray = ignoreProperties.toArray(new String[0]);
        BeanUtils.copyProperties(p, existingProjet, ignorePropertiesArray);
        return projetRepository.save(existingProjet);
    }

    /*@Override
    public Integer removeProjet(Long idprojet, String idUser) {
        User currentUser = userRepository.findById(idUser).orElse(null);
        if ((currentUser.getRole().getRoleName() != "Admin") && (currentUser.getRole().getRoleName() == "Manager")){
            projetRepository.deleteById(idprojet);
            return 1;
        }
        else
            return 0;
    }*/

    @Override
    public Integer removeProjet(Long idprojet, String idUser){
        User currentUser = userRepository.findById(idUser).orElse(null);
        if (currentUser != null && currentUser.getRole() != null) {
            String roleName = currentUser.getRole().getRoleName();
            if ("Admin".equals(roleName)) {
                projetRepository.deleteById(idprojet);
                return 1;
            }
        }
        return 0;
    }



    @Override
    public projet addProjetwithIdUser(projet p, String idUser ) {
        User user = userRepository.findById(idUser).orElse(null);
        p.setUser(user);
        projet projetExistant = projetRepository.findByNomprojet(p.getNomprojet());
        if (projetExistant == null) {
            projet nouveauProjet = new projet();
            nouveauProjet.setNomprojet(p.getNomprojet());
            nouveauProjet.setUser(user);
            nouveauProjet.setRegion(p.getRegion());
            nouveauProjet.setCountry(p.getCountry());
            return projetRepository.save(nouveauProjet);
        } else {
            return null;
        }
    }

}
