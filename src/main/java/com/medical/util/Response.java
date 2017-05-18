package com.medical.util;

import java.io.Serializable;

/**
 * Created by ewrfcas on 2017/3/2.
 */

@SuppressWarnings("all")
public class Response<T> implements Serializable {
    //返回状态
    private ResponseStatus status;
    //返回信息
    private String message;
    //返回数据
    private T data;

    public Response() {
    }

    public Response(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(ResponseStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
