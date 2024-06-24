package com.example.demo.controller;
import com.example.demo.entites.ApiResponse;
import com.example.demo.entites.Ressources;
import com.example.demo.entites.Session;
import com.example.demo.services.impl.SessionService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Session")
public class SessionControllers {

    @Autowired
    SessionService sessionControl ;

    // http://localhost:8082/Session/GetallSession
    @GetMapping("/GetallSession")
    List<Session> GetAllSession() {
        return sessionControl.GetAllSession();
    }

    @GetMapping("/GetSession/{idSession}")
    public ResponseEntity<Session> getSessionById(@PathVariable("idSession") Long idSession) {
        Optional<Session> optionalSession = sessionControl.getSessionById(idSession);
        ApiResponse response = new ApiResponse();
        if (optionalSession.isPresent())
            return ResponseEntity.ok(optionalSession.get());
        else {
            response.setMessage("session not found");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getActiveSession")
    public ResponseEntity<Session> getActiveSession() {
        Optional<Session> optionalSession = sessionControl.getActiveSession();
        ApiResponse response = new ApiResponse();
        if (optionalSession.isPresent())
            return ResponseEntity.ok(optionalSession.get());
        else {
            response.setMessage("no active session");
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    // http://localhost:8082/Session/addsession
    @PostMapping("/addSession/{idUser}")
    public ResponseEntity<Session>  addSession(@RequestBody Session S, @PathVariable String idUser){

            Integer test  = sessionControl.addSession(S, idUser);
            ApiResponse response = new ApiResponse();
            if(test == 1){
                response.setMessage("session added successfully !");
                return new ResponseEntity(response, HttpStatus.OK);
            }
            else{
                response.setMessage("error adding session");
                return new ResponseEntity(response, HttpStatus.CONFLICT);
            }
    }

   /* @PostMapping("/sendEmailing/{idUser}")
    public ResponseEntity<String> sendEmail(@RequestBody Session session, @PathVariable  Long idUser) {
        try {
            sessionControl.sendEmailSession(session, idUser);
            return ResponseEntity.ok("Email sent successfully");
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email");
        }
    }*/


    @PutMapping("/update/{idSession}")
    public ResponseEntity<String> updateSession(@RequestBody Session S,
                                                  @PathVariable("idSession") Long idSession ) {
        sessionControl.editSession(S,idSession);
        ApiResponse response = new ApiResponse();
        response.setMessage("Ressource updated successfully !");
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping("/send/{idsession}")
    public ResponseEntity<String> sendEmailSession(@PathVariable("idsession") Long idsession) throws MessagingException {
        try {
            sessionControl.sendEmailSession(idsession);
            return ResponseEntity.ok("Email sent successfully");
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email");
        }
    }
}
