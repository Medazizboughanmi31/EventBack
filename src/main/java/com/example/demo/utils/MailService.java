package com.example.demo.utils;

import com.example.demo.entites.TypeSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

import static java.awt.SystemColor.info;


@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;
    public void sendActiveSession(String email, TypeSession typeSession,Date DateDebutSession ,Date DateFinSession ) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("daouthiamin70@gmail.com");
        helper.setTo(email);
        helper.setSubject("La Session est ouverte ");
        helper.setText("Votre session est  " +typeSession+ " le : " +DateDebutSession+ "est sera fermée le " +DateFinSession+""+ info);
        mailSender.send(message);
    }

}