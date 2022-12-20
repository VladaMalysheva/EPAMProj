package com.example.epamproj.exceptions;

public class AlertException extends Exception {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AlertException() {
    }

    public AlertException(String message, String address) {
        super(message);
        this.address=address;
    }



    public AlertException(String message, Exception ex, String address) {
        super(message, ex);
        this.address=address;
    }

}