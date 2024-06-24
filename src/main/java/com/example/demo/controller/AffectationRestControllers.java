package com.example.demo.controller;

import com.example.demo.entites.Affectation;
import com.example.demo.entites.ApiResponse;
import com.example.demo.services.impl.AffectationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/affectation")
public class AffectationRestControllers {
    @Autowired
    AffectationServices affectationControl;

    @GetMapping("/getallaffectations")  //check
    public List<Affectation> getAllaffectations() {
        return affectationControl.getAllAffectations();
    }


    @GetMapping("/GetAffectation/{idAffectation}")
    public ResponseEntity<Affectation> getAffectationById(@PathVariable("idAffectation") Long idAffectation) {
        Optional<Affectation> optionalAffectation = affectationControl.getAffectationWithID(idAffectation);
        ApiResponse response = new ApiResponse();
        if (optionalAffectation.isPresent())
            return ResponseEntity.ok(optionalAffectation.get());
        else {
            response.setMessage("affectation not found");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }
    }


    @PostMapping("/addaffectation/{idUser}")
    public ResponseEntity<String> addAffectation(@RequestBody Affectation Affectation,
                                                 @PathVariable("idUser") String idUser) {
        List<String> affectation = affectationControl.addAffectation(Affectation,idUser);
        ApiResponse response = new ApiResponse();

        if(affectation == null){
            response.setMessage("une affectation avec le meme projet et meme ressource existe déjà");
            return new ResponseEntity(response, HttpStatus.CONFLICT);

        }
        else if(affectation.isEmpty()){
            response.setMessage("affectation ajouté avec succès !");
            return new ResponseEntity(response, HttpStatus.OK);

        }
        else{
            response.setMessage("vous avez depassé les nombre de jours de travail de ces mois" + affectation);
            return new ResponseEntity(response, HttpStatus.CONFLICT);

        }
    }





    @PutMapping("/update/{idAffectation}")
    public ResponseEntity<String> updateAffectation(@RequestBody Affectation affectation,
                                  @PathVariable("idAffectation") Long idAffectation){
        affectationControl.updateAffectation(affectation, idAffectation);
        ApiResponse response = new ApiResponse();
        response.setMessage("Project updated successfully !");
        return new ResponseEntity(response, HttpStatus.OK);
    }




    @DeleteMapping("/deleteaffectation/{idAffectation}")
    public ResponseEntity<String> deleteAffectation(@PathVariable("idAffectation") Long idAffectation) {
        affectationControl.deleteAffectation(idAffectation);
        ApiResponse response = new ApiResponse();
        response.setMessage("Project deleted !");
        return new ResponseEntity(response, HttpStatus.OK);

    }
}
