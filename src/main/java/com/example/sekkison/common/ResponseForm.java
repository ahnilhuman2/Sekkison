package com.example.sekkison.common;

import lombok.Data;

@Data
public class ResponseForm {
    private String msg;
    private boolean success;
    private Object data;

    public ResponseForm setError(String msg, boolean success) {
        this.msg = msg;
        this.success = success;
        return this;
    }
    public ResponseForm setSuccess(boolean success, Object data) {
        this.success = success;
        this.data = data;
        return this;
    }
}
