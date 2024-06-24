package com.example.demo.services;

import com.example.demo.entites.User;
import com.example.demo.entites.projet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface IUser {
    List<User> getAllUsers();
    Optional<User> getUserWithID(String id);
    void initRolesAndUser();
    Integer deleteUser(String idUser);

    Integer updateUser(String idUser, User user);

}
