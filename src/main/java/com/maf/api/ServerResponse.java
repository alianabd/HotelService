package com.maf.api;

/**
 * Pojo class to represent server general response with message in case of error
 */
public class ServerResponse {

    private String message;

    public ServerResponse() {
    }

    public ServerResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}