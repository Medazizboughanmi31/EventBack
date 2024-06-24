package com.example.demo.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StatuDaffectation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStat;
    private String Nomstatutdaff;

    // liaison avec Affectation
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "naturedaffectations")
    //@JoinColumn(name = "idAffectation")
    private List<Affectation> affectation;





}
