package com.example.sekkison.common;

import lombok.Data;

@Data
public class ResponseForm {
    private String msg;
    private boolean isSuccess;
    private Object data;

    public ResponseForm setError(String msg, boolean isSuccess) {
        this.msg = msg;
        this.isSuccess = isSuccess;
        return this;
    }
    public ResponseForm setSuccess(boolean isSuccess, Object data) {
        this.isSuccess = isSuccess;
        this.data = data;
        return this;
    }
}
