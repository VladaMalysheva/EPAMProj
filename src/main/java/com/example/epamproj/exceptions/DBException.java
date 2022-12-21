package com.example.epamproj.exceptions;


public class DBException extends AppException {
    public DBException() {
    }

    public DBException(String message) {
        super(message);
    }

    public DBException(String message, Exception ex) {
        super(message, ex);
    }
}
