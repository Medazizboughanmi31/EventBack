package com.example.demo.services;

import com.example.demo.entites.Naturedaffectation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface INatureDaffec {
    Naturedaffectation addNatureDaff(Naturedaffectation naturedaff);

    List<Naturedaffectation> GetAllNatureDaff();
}
