package com.example.demo.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Naturedaffectation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNaturedaffec;
    private String nameNaturedaffec ;


    // liaison avec bu many to one
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "statuDaffectations")
    //@JoinColumn(name = "idAffectation")
    private List<Affectation> affectation;


}
