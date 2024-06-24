package com.example.demo.services.impl;

import com.example.demo.entites.Naturedaffectation;
import com.example.demo.repository.NatureDaffectatonRepo;
import com.example.demo.services.INatureDaffec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NatureDaffecServices implements INatureDaffec {

   @Autowired
    NatureDaffectatonRepo naturedaffecRepository ;

   @Override
   public Naturedaffectation addNatureDaff(Naturedaffectation naturedaff){
       return naturedaffecRepository.save (naturedaff);
   }
    @Override
    public List<Naturedaffectation> GetAllNatureDaff() {
        return naturedaffecRepository.findAll();
    }


}
