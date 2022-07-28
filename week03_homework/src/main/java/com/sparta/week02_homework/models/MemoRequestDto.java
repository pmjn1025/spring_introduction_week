package com.sparta.week02_homework.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoRequestDto {

    private String username;
    private String title;
    private String comment;
    private String userPassword;
    private String inputPassword;

}
