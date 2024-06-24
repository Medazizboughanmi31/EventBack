package com.example.demo.services.impl;

import com.example.demo.entites.Session;
import com.example.demo.entites.TypeSession;
import com.example.demo.entites.User;
import com.example.demo.repository.SessionRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.services.ISession;
import com.example.demo.utils.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.*;

@Service
@Slf4j
public class SessionService implements ISession {
    @Autowired
    SessionRepo sessionRepository;
    @Autowired
    UserRepo userRepository;
    @Autowired
    private MailService mailService;

    @Override
    @Transactional
    public Integer addSession(Session S, String idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        if (user.getRole().getRoleName().equals("Admin")) {

            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDateTime dateDebut = convertToLocalDateTime(S.getDateDebutSession());
            LocalDateTime dateFin = convertToLocalDateTime(S.getDateFinSession());

            if (dateDebut.isAfter(currentDateTime) && dateFin.isAfter(currentDateTime)) {
                S.setTypeSession(TypeSession.Scheduled);
            } else if (dateFin.isBefore(currentDateTime)) {
                S.setTypeSession(TypeSession.Inactive);
            } else if (dateDebut.isBefore(currentDateTime) && dateFin.isAfter(currentDateTime)) {
                S.setTypeSession(TypeSession.Active);
            }

            S.setUser(user);
            sessionRepository.save(S);
            return 1;
        } else {
            return 0;
        }
    }

    private LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static List<String> getSessionAttributes(Class<?> sessionClass) {
        List<String> attributeNames = new ArrayList<>();
        Field[] fields = sessionClass.getDeclaredFields();
        for (Field field : fields) {
            attributeNames.add(field.getName());
        }
        return attributeNames;
    }
    @Override
    public Integer editSession(Session S, Long idSession){
        Session existingSession = sessionRepository.findById(idSession).orElse(null);;
        List<String> attributes = getSessionAttributes(Session.class);
        Set<String> ignoreProperties = new HashSet<>();
        for (String attribute : attributes){
            if (S.get(attribute) == null )
                ignoreProperties.add(attribute);
        }
        String[] ignorePropertiesArray = ignoreProperties.toArray(new String[0]);
        BeanUtils.copyProperties(S, existingSession, ignorePropertiesArray);

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime dateDebut = convertToLocalDateTime(S.getDateDebutSession());
        LocalDateTime dateFin = convertToLocalDateTime(S.getDateFinSession());

        if (dateDebut.isAfter(currentDateTime) && dateFin.isAfter(currentDateTime)) {
            S.setTypeSession(TypeSession.Scheduled);
        } else if (dateFin.isBefore(currentDateTime)) {
            S.setTypeSession(TypeSession.Inactive);
        } else if (dateDebut.isBefore(currentDateTime) && dateFin.isAfter(currentDateTime)) {
            S.setTypeSession(TypeSession.Active);
        }

        sessionRepository.save(S);
        return 1;
    }
    @Override
    public void sendEmailSession(Long idsession ) throws MessagingException {
        Session session = sessionRepository.findById(idsession).orElse(null);
        if ("Active".equals(session.getTypeSession())) {
            log.info("Votre Session est ouverte du : " + session.getDateDebutSession() + " et se fermera le " + session.getDateFinSession());
            mailService.sendActiveSession(session.getEmail(), TypeSession.Active, session.getDateDebutSession(), session.getDateFinSession());
        }
    }

    @Override
    public List<Session> GetAllSession() {
        return sessionRepository.findAll();
    }

    @Override
    public Optional<Session> getSessionById(Long idSession){
        return sessionRepository.findById(idSession);
    }


    @Override
    public Optional<Session> getActiveSession(){
        return sessionRepository.findByTypeSession(TypeSession.Active);
    }

}
