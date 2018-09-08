package com.example.androidhrd.server_communication_retrofit.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseResponse {
    private String msg;
    private boolean status;
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
