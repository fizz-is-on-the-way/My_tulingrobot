package com.example.administrator.my_tulingrobot;

/**
 * Created by Administrator on 2018/3/16.
 */

public class CommonException extends RuntimeException {
    public CommonException() {
    }

    public CommonException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public CommonException(String detailMessage) {
        super(detailMessage);
    }

    public CommonException(Throwable throwable) {
        super(throwable);
    }
}