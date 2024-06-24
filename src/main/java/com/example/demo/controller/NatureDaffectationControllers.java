package com.example.demo.controller;
import com.example.demo.entites.Naturedaffectation;
import com.example.demo.services.impl.NatureDaffecServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/NatureDaffectation")
public class NatureDaffectationControllers {
    @Autowired
    NatureDaffecServices natureDaffectControl;


    // http://localhost:8082/NatureDaffectation/GetallNatureDaffectation
    @GetMapping("/GetallNatureDaffectation")
    List<Naturedaffectation> GetAllNatureDaff() {
        return natureDaffectControl.GetAllNatureDaff();
    }

    // http://localhost:8082/NatureDaffectation/addNatureDaffectation
    @PostMapping("/addNatureDaffectation")
       public  Naturedaffectation addNatureDaff(@RequestBody Naturedaffectation naturedaff)
    {
        Naturedaffectation naturedaffectation = natureDaffectControl.addNatureDaff(naturedaff);
        return naturedaffectation;
    }
}
