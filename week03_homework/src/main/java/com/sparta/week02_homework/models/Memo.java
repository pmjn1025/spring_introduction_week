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
public class Memo extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @JsonIgnore(value=true)
    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String comment;

    //dto받아서 메모 추가
    public Memo(MemoRequestDto memoRequestDto){

        this.username = memoRequestDto.getUsername();
        this.title = memoRequestDto.getTitle();
        this.userPassword = memoRequestDto.getUserPassword();
        this.comment = memoRequestDto.getComment();


    }

    public Memo(PasswordDto passwordDto){

        this.userPassword = passwordDto.getPassword();

    }


    public void update(MemoRequestDto memoRequestDto){

        //this.title = memoRequestDto.getTitle();
        this.comment = memoRequestDto.getComment();

    }


    public void passupdate(PasswordDto passwordDto) {

        this.userPassword = passwordDto.getPassword();

    }

    public void passupdate1(String some) {

        this.userPassword = some;

    }

}
