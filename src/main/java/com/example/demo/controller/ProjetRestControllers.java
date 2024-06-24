package com.example.demo.controller;

import com.example.demo.entites.ApiResponse;
import com.example.demo.entites.projet;
import com.example.demo.services.impl.ProjetServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController


@RequestMapping("/projet")
public class ProjetRestControllers {
    @Autowired
    ProjetServices  projetControl;


    @GetMapping("/GetallProject")
    @PreAuthorize("isAuthenticated()")
    List<projet> GetAllprojet() {
             return projetControl.GetAllprojet();
    }

   @GetMapping("/GetProjet/{idProjet}")
   public ResponseEntity<projet> getProjetById(@PathVariable("idProjet") Long idProjet) {
        Optional<projet> optionalProjet = projetControl.getProjetWithID(idProjet);
        ApiResponse response = new ApiResponse();
        if (optionalProjet.isPresent())
            return ResponseEntity.ok(optionalProjet.get());
        else {
            response.setMessage("projet not found");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }
   }

   @PostMapping("/addprojet/{idUser}")
   public ResponseEntity<String> addProjetwithIdUser(@RequestBody projet p ,
                                                          @PathVariable("idUser") String idUser) {
        projet projets = projetControl.addProjetwithIdUser(p, idUser);
        ApiResponse response = new ApiResponse();
        if (projets != null) {
            response.setMessage("Projet ajouté avec succès !");
            return new ResponseEntity(response , HttpStatus.OK);
        } else {
            response.setMessage("Projet existe déjà");
            return new ResponseEntity(response , HttpStatus.CONFLICT);

        }
    }

    //host:8082/updateProjet/1
    @PutMapping("/updates/{idprojet}")
    public ResponseEntity<String> updateProjet(@RequestBody projet p,
                             @PathVariable("idprojet") Long idprojet) {
        projetControl.updateProjet(p,idprojet);
        ApiResponse response = new ApiResponse();
        response.setMessage("Project updated successfully !");
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @DeleteMapping("/removeProjet/{iduser}/{idprojet}")
    public ResponseEntity<String> removeprojet(@PathVariable("idprojet") Long idprojet,
                             @PathVariable("iduser") String idUser) {
        Integer test = projetControl.removeProjet(idprojet, idUser);
        ApiResponse response = new ApiResponse();
        if (test == 1) {
            response.setMessage("Project deleted !");
            return new ResponseEntity(response, HttpStatus.OK);
        }
        else{
            response.setMessage("only admin can delete a project");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }
    }

}
