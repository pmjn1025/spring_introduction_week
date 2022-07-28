package com.sparta.week02_homework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Password {

    @JsonIgnore(value=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String password;



    public Password(PasswordDto passwordDto){


        this.password = passwordDto.getPassword();


    }


}
