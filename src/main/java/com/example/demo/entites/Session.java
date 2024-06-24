package com.example.demo.entites;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Session implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idsession;
    private Date dateDebutSession;
    private Date dateFinSession;
    private String moisA;
    private String moisB;
    private String moisC;
    private Long joursTravailMoisA;
    private Long joursTravailMoisb;
    private Long joursTravailMoisC;
    private String email ;
    @Enumerated(EnumType.STRING)
    private TypeSession typeSession ;



    // liaison avec projet one to many
    //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true , mappedBy = "session")
    /*@OneToMany( mappedBy = "session")
    @JsonIgnore
    @ToString.Exclude
    private List<projet> projets;


    // liaison avec ressource one to many
    // @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true , mappedBy = "session")
    @OneToMany(mappedBy = "session")
    @JsonIgnore
    @ToString.Exclude
    private List<Ressources> ressources;*/

    // liaison avec user Many To One

    @ManyToOne(fetch = FetchType.LAZY,cascade =CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "idUser")
    private User user;


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
