package com.example.demo.entites;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Ressources implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRessource;
    private String nomRessource;
    private String prenomRessource;
    private String nomManager ;
    private String prenomManager ;
    private String departement ;


    // liaison  with user Many to one
    @ManyToOne (fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "idUser")
    private User user;

    // liaison avec Session
    @ManyToOne(fetch = FetchType.LAZY,cascade =CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "idsession")
    private Session session;

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

}

