package com.example.demo.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {


    @Id
    private String userName;
    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private String userprofilPicture;
    private String usercoverPicture;
    private String useridCountry;
    private int usertype;
    private int userstars;
    @Enumerated(EnumType.STRING)
    private TypePerson Person ;
    @Column(name = "enabled")
    private boolean enabled = true;
    private LocalDateTime usercreatedAt;
    @Column(name = "is_approved", nullable = false, columnDefinition = "boolean default false")
    private boolean isApproved;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    private Role role;

    public Object get(String attributeName) {
        try {
            Class<?> clazz = this.getClass();
            Field field = clazz.getDeclaredField(attributeName);
            field.setAccessible(true);
            return field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @ToString.Exclude
    private List<projet> project;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @ToString.Exclude
    private List<Ressources> resources;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @ToString.Exclude
    private List<Session> sessions;
}
