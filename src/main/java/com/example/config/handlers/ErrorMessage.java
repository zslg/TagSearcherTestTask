package com.example.config.handlers;

public class ErrorMessage {

    private String path;

    private String value;

    private String message;

    public ErrorMessage(String path, String value, String message) {
        this.path = path;
        this.value = value;
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
