package com.example.demo.services;

import com.example.demo.entites.StatuDaffectation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IStatuDaffec {
    StatuDaffectation addSatuDaff(StatuDaffectation statdaff);

    List<StatuDaffectation> GetAllStatDaff();
}
