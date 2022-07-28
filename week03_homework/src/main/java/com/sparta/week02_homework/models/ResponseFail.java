package com.sparta.week02_homework.models;

public class ResponseFail<T> extends Response {

    private boolean success;
    private String data;
    private String error;

    public ResponseFail() {
        this.success = true;
        this.data = "false";
        this.error = null;
    }


}
