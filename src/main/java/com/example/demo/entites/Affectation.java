package com.example.demo.entites;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor


public class Affectation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAffectation;
    private String nomManager;
    private String prenomManager;
    private String departement;

    private String nomRessource;
    private String prenomRessource;
    private String projet;

    private String country;
    private String region;

    private String natureAff;
    private String statutAff;

    private Integer moisA;
    private Integer moisB;
    private Integer moisC;

    private Integer sommeParProjet;


    // liaison avec nature d'affectation
    @ManyToOne(fetch = FetchType.LAZY,cascade =CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name = "idNaturedaffec")
    private Naturedaffectation naturedaffectations;


    // liaison avec statu d'affectation
    @ManyToOne(fetch = FetchType.LAZY,cascade =CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name = "idStat")
    private StatuDaffectation statuDaffectations;


    // Many-to-one relationship with User entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;


    // Many-to-one relationship with Resource entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ressource")
    private Ressources resssource;


    // Many-to-one relationship with Project entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idprojet")
    private projet project;


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
