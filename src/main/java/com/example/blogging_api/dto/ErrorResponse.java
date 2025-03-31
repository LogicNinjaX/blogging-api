package com.example.blogging_api.dto;

public class ErrorResponse<T>{

    private boolean success;

    private String message;

    private int error_code;

    private T data;

    public ErrorResponse(boolean success, String message, int error_code, T data) {
        this.success = success;
        this.message = message;
        this.error_code = error_code;
        this.data = data;
    }

    public ErrorResponse(boolean success, String message, int error_code) {
        this.success = success;
        this.message = message;
        this.error_code = error_code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
