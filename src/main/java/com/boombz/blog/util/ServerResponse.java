package com.boombz.blog.util;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @program: we
 * @description:统一服务端的返回数据
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

    @JsonIgnore
    public boolean isSuccess() {
        return this.status;
    }
    @JsonIgnore
    public boolean getStatus() {
        return status;
    }
    @JsonIgnore
    public T getData() {
        return data;
    }
    @JsonIgnore
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
