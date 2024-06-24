package com.example.demo.controller;

import com.example.demo.entites.StatuDaffectation;
import com.example.demo.services.impl.StatuDaffecServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/StatuDaff")
public class StatuDaffectationControllers {
    @Autowired
    StatuDaffecServices statuDaffecControl;

    //http://localhost:8082/StatuDaff/GetallStatudaffectation
    @GetMapping("/GetallStatudaffectation")
    List<StatuDaffectation> GetAllStatDaff() {
        return statuDaffecControl.GetAllStatDaff();
    }


    // http://localhost:8082/StatuDaff/addStatuDaffectation
    @PostMapping("/addStatuDaffectation")
    public StatuDaffectation addSatuDaff(@RequestBody StatuDaffectation statdaff )
    {
        StatuDaffectation statudaffectation = statuDaffecControl.addSatuDaff(statdaff);
        return statudaffectation;
    }

}
