package com.example.sekkison.common;

import lombok.Data;

@Data
public class ResponseForm {
    private String msg;
    private boolean isSuccess;
    private Object data;

    public void setError(String msg, boolean isSuccess) {
        this.msg = msg;
        this.isSuccess = isSuccess;
    }
    public void setSuccess(boolean isSuccess, Object data) {
        this.isSuccess = isSuccess;
        this.data = data;
    }
}
