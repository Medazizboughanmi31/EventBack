package com.example.demo.services.impl;

import com.example.demo.entites.StatuDaffectation;
import com.example.demo.repository.StatuDaffectationRepo;
import com.example.demo.services.IStatuDaffec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StatuDaffecServices implements IStatuDaffec {
    @Autowired
    StatuDaffectationRepo statudafRepository ;

    @Override
    public StatuDaffectation addSatuDaff(StatuDaffectation statdaff){
        return statudafRepository.save(statdaff);
    }

    @Override
    public List<StatuDaffectation> GetAllStatDaff() {
        return statudafRepository.findAll();
    }
}
