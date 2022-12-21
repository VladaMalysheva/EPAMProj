package com.example.epamproj.exceptions;

public class AppException extends Exception{

    public AppException() {
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Exception ex) {
        super(message, ex);
    }
}
