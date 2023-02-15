package com.example.sekkison.common;

import lombok.Data;

@Data
public class ErrorForm {
    private String msg;
    private boolean isSuccess;

    public ErrorForm(String msg, boolean isSuccess) {
        this.msg = msg;
        this.isSuccess = isSuccess;
    }
}
