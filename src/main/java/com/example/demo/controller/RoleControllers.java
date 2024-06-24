package com.example.demo.controller;

import com.example.demo.services.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleControllers {

    @Autowired
    private RoleService roleService;


}
