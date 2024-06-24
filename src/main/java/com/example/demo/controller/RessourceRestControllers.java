package com.example.demo.controller;

import com.example.demo.entites.ApiResponse;
import com.example.demo.entites.Ressources;
import com.example.demo.services.impl.RessourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Ressource")
public class RessourceRestControllers {

    @Autowired
    RessourceServices ressourceControl;


    @GetMapping("/getallRessources")
    List<Ressources> getAllRessources(){
        return ressourceControl.getAllRessources();
    }

    @GetMapping("/GetRessource/{idRessource}")
    public ResponseEntity<Ressources> getProjetById(@PathVariable("idRessource") Long idRessource) {
        Optional<Ressources> optionalRessource = ressourceControl.getRessourceWithID(idRessource);
        ApiResponse response = new ApiResponse();
        if (optionalRessource.isPresent())
            return ResponseEntity.ok(optionalRessource.get());
        else {
            response.setMessage("ressource not found");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/addressource/{idUser}")
    public ResponseEntity<String> addRessourcewithIdUser(@RequestBody Ressources r ,
                                             @PathVariable("idUser") String idUser) {
        Ressources ressource = ressourceControl.addRessourcewithIdUser(r,idUser);
        ApiResponse response = new ApiResponse();
        response.setMessage("Ressource ajouté avec succès !");
        return new ResponseEntity(response , HttpStatus.OK);
    }


    @PutMapping("/update/{idRessource}")
    public ResponseEntity<String> updateRessource(@RequestBody Ressources r,
                                @PathVariable("idRessource") Long idRessource ) {
        ressourceControl.updateRessource(r,idRessource);
        ApiResponse response = new ApiResponse();
        response.setMessage("Ressource updated successfully !");
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @DeleteMapping("/removeRessource/{iduser}/{idressource}")
    public ResponseEntity<String> removeRessource(@PathVariable("idressource") Long idressource,
                             @PathVariable("iduser") String idUser) {
        ApiResponse response = new ApiResponse();
        Integer test = ressourceControl.removeRessouce(idressource, idUser);
        if(test == 1){
            response.setMessage("ressource deleted !");
            return new ResponseEntity(response, HttpStatus.OK);
        }
        else{
            response.setMessage("only admin can delete a resource");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }
    }

}
