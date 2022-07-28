package com.sparta.week02_homework.models;

import lombok.Getter;

@Getter
public class PasswordDto {

    private String password;


    public String some(){

        return this.password;
    }

}
