package com.example.administrator.my_tulingrobot;

/**
 * Created by Administrator on 2018/3/16.
 */

public class Result {
    private int code;
    private String text;

    public Result() {
    }

    public Result(int resultCode, String msg) {
        this.code = resultCode;
        this.text = msg;
    }

    public Result(int resultCode) {
        this.code = resultCode;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
