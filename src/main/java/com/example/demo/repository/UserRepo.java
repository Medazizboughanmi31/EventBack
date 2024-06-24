package com.example.demo.repository;

import com.example.demo.entites.Ressources;
import com.example.demo.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

}