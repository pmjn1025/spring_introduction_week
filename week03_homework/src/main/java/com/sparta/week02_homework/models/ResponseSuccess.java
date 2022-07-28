package com.sparta.week02_homework.models;

import lombok.Getter;

@Getter
public class ResponseSuccess<T> extends Response {
    private boolean success;
    private T data;
    private String error;

    public ResponseSuccess(T data) {
        this.success = true;
        this.data = data;
        this.error = null;
    }
}