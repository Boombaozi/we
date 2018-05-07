package com.boombz.blog.util;


import java.io.Serializable;

/**
 * @program: we
 * @description:
 * @author: boombaozi.com
 * @create: 2018-04
 **/

public class ServerResponse<T>  {

    private boolean status;
    private String msg;
    private T data;

    private ServerResponse(boolean status) {
        this.status = status;
    }

    private ServerResponse(boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(boolean status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    //@JsonIgnore
    //使之不在json序列化结果当中
    public boolean isSuccess() {
        return this.status;
    }

    public boolean getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }


    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<T>(true);
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
        return new ServerResponse<T>(true, msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data) {
        return new ServerResponse<T>(true, data);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
        return new ServerResponse<T>(true, msg, data);
    }

    public static <T> ServerResponse<T> createByError() {
        return new ServerResponse<T>(false);
    }

    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {
        return new ServerResponse<T>(false, errorMessage);
    }

}
