package com.example.sekkison.common;

import lombok.Data;

@Data
public class ResponseForm {
    private String msg;
    private boolean success;
    private Object data;

    public ResponseForm setError(String msg) {
        this.msg = msg;
        this.success = false;
        return this;
    }
    public ResponseForm setSuccess(Object data) {
        this.success = true;
        this.data = data;
        return this;
    }
}
