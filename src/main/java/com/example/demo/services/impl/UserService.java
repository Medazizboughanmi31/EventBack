package com.example.demo.services.impl;


import com.example.demo.dao.RoleDao;
import com.example.demo.dao.UserdDao;
import com.example.demo.entites.Role;
import com.example.demo.entites.User;
import com.example.demo.services.IUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
@Slf4j
public class UserService implements IUser {

    @Autowired
    private UserdDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public Optional<User> getUserWithID(String id){
        return userDao.findById(id);
    }
    public Integer registrerNewUser(User user) {
        Role role = roleDao.findOneByRoleName("Expert").get();
        if (role != null) {
            user.setRole(role);
            user.setUserPassword(getEncodedPassword(user.getUserPassword()));
            userDao.save(user);
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public void initRolesAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Expert");
        adminRole.setRoleDescription("Admin Role");
        roleDao.save(adminRole);

        Role adminorganisateurRole = new Role();
        adminorganisateurRole.setRoleName("Organisateure");
        adminorganisateurRole.setRoleDescription("default role for newly created record");
        roleDao.save(adminorganisateurRole);

        Role EntrepreneureRole = new Role();
        EntrepreneureRole.setRoleName("Entrepreneure");
        EntrepreneureRole.setRoleDescription("default role for newly created record");
        roleDao.save(EntrepreneureRole);

        User adminUser = new User();
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setRole(adminRole);
        userDao.save(adminUser);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }


    public static List<String> getUserAttributes(Class<?> userClass){
        List<String> attributeNames = new ArrayList<>();
        Field[] fields =userClass.getDeclaredFields();
        for (Field field : fields) {
            attributeNames.add(field.getName());
        }
        return attributeNames;
    }

    public Integer updateUser(String idUser, User user){
        User existingUser = userDao.findById(idUser).get();
        if(existingUser != null){
            List<String> attributes = getUserAttributes(User.class);
            Set<String> ignoreProperties = new HashSet<>();
            for (String attribute : attributes) {
                if (user.get(attribute) == null)
                    ignoreProperties.add(attribute);
            }
            String[] ignorePropertiesArray = ignoreProperties.toArray(new String[0]);
            BeanUtils.copyProperties(user, existingUser, ignorePropertiesArray);
            if(existingUser.getRole() != null){
                Role existingRole = roleDao.findOneByRoleName(existingUser.getRole().getRoleName()).orElse(null);
                if (existingRole != null) {
                    existingUser.setRole(existingRole);
                }
            }
            userDao.save(existingUser);
        }

        return 1;
    }

    @Override
    public Integer deleteUser(String idUser) {
        userDao.deleteById(idUser);
        return 1;
    }
}
