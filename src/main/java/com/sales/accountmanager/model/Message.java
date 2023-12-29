package com.sales.accountmanager.model;

import lombok.Data;

@Data
public class Message {
    private String message;
    private String resCode;

    public Message(String message, String resCode) {
        this.message = message;
        this.resCode = resCode;
    }
}
