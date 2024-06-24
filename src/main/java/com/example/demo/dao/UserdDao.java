package com.example.demo.dao;
import com.example.demo.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface UserdDao extends JpaRepository<User, String >{

    Optional<User> findOneByUserFirstName(String userFirstName);

    User findAllByUserName(String idUser);
}
