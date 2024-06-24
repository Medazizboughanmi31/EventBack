package com.example.demo.services.impl;

import com.example.demo.entites.*;
import com.example.demo.repository.RessourceRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.services.IRessource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.lang.reflect.Field;
import java.util.List;
@Service
@Slf4j
public class RessourceServices implements IRessource {
    @Autowired
    RessourceRepo ressourceRepository;
    @Autowired
    UserRepo userRepository;



    @Override
    public List<Ressources> getAllRessources() {
        return ressourceRepository.findAll();
    }

    @Override
    public Optional<Ressources> getRessourceWithID(Long id){
        return ressourceRepository.findById(id);
    }

    @Override
    public Integer removeRessouce(Long idRessources, String idUser) {
        User currentUser = userRepository.findById(idUser).orElse(null);
        if (currentUser != null && currentUser.getRole() != null) {
            String roleName = currentUser.getRole().getRoleName();
            if ("Admin".equals(roleName)) {
                ressourceRepository.deleteById(idRessources);
                return 1;
            }
        }
        return 0;
    }

    public static List<String> getResourceAttributes(Class<?> resourceClass) {
        List<String> attributeNames = new ArrayList<>();
        Field[] fields = resourceClass.getDeclaredFields();
        for (Field field : fields) {
            attributeNames.add(field.getName());
        }
        return attributeNames;
    }

    @Override
    public Ressources updateRessource(Ressources r, Long idRessource) {
        Ressources existingRessource = ressourceRepository.findById(idRessource).orElse(null);
            List<String> attributes = getResourceAttributes(Ressources.class);
            Set<String> ignoreProperties = new HashSet<>();
            for (String attribute : attributes) {
                if (r.get(attribute) == null)
                    ignoreProperties.add(attribute);
            }
            String[] ignorePropertiesArray = ignoreProperties.toArray(new String[0]);
            BeanUtils.copyProperties(r, existingRessource, ignorePropertiesArray);
            return ressourceRepository.save(existingRessource);
        }


    @Override
    public Ressources addRessourcewithIdUser(Ressources r, String idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        r.setUser(user);
        return ressourceRepository.save(r);
    }

}
