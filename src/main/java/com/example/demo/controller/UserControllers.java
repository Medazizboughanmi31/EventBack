package com.example.demo.controller;
import com.example.demo.dao.UserdDao;
import com.example.demo.entites.ApiResponse;
import com.example.demo.entites.Ressources;
import com.example.demo.entites.User;
import com.example.demo.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Component
@RestController
@RequestMapping("/user")
public class UserControllers {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasRole('Expert,Organisateure,Entrepreneure')")
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUser/{idUser}")
    public ResponseEntity<User> getUserById(@PathVariable("idUser") String idUser) {
        Optional<User> user = userService.getUserWithID(idUser);
        ApiResponse response = new ApiResponse();
        if (user.isPresent())
            return ResponseEntity.ok(user.get());
        else {
            response.setMessage("user not found");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }
    }

    @PostMapping({"/RoleAndUser"})     //////////////////////USELESS////////////////////////
    public void initRoleAndUser() {
        userService.initRolesAndUser();
    }


    @PostMapping({"/registerNewUser"})
    public ResponseEntity<Ressources> registrerNewUser(@RequestBody User user) {
        Integer test = userService.registrerNewUser(user);
        ApiResponse response = new ApiResponse();
        if(test == 1){
            response.setMessage("user registred successffuly");
            return new ResponseEntity(response , HttpStatus.OK);
        }
        else{
            response.setMessage("cannot register user");
            return new ResponseEntity(response, HttpStatus.CONFLICT);
        }

    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Expert,Organisateure,Entrepreneure')")
    public String forAdmin() {
        return "this is for Admin";
    }


    @GetMapping({"/forManager"})
    @PreAuthorize("hasRole('Manager')")
    public String forUser() {
        return "this is for Manager";
    }

    @PutMapping({"/update/{idUser}"})
    public ResponseEntity<String> updateUser(@RequestBody User user,
                                             @PathVariable("idUser") String idUser){
        userService.updateUser(idUser, user);
        ApiResponse response = new ApiResponse();
        response.setMessage("User updated successfully !");
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping({"/removeUser/{idUser}"})
    @PreAuthorize("hasRole('Expert,Organisateure,Entrepreneure')")
    public ResponseEntity<String> removeUser(@PathVariable("idUser") String idUser){
        Integer test = userService.deleteUser(idUser);
        ApiResponse response = new ApiResponse();
        response.setMessage("User deleted !");
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
