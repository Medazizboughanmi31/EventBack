package com.example.demo.services;

import com.example.demo.entites.Session;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

@Component
public interface ISession {
    Integer addSession(Session S , String idUser) throws MessagingException;
    Integer editSession(Session S, Long idUser);
    void sendEmailSession( Long idsession) throws MessagingException;

    List<Session> GetAllSession();
    Optional<Session> getSessionById(Long idSession);
    Optional<Session> getActiveSession();
}
