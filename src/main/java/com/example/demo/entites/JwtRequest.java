package com.example.demo.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Setter
public class JwtRequest  implements Serializable {


    private String userName;
    private String userPassword ;

}
