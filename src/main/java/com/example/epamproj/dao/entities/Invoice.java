package com.example.epamproj.dao.entities;

import java.io.Serializable;
import java.sql.Date;

public class Invoice implements Serializable {
    private int id;
    private Order order;
    private int orderId;
    private Date date;
    private String details;

    public Invoice(int orderId, Date date, String details) {
        this.orderId = orderId;
        this.date = date;
        this.details = details;
    }

    public Invoice(int id, Order order, int orderId, Date date, String details) {
        this.id = id;
        this.order = order;
        this.orderId = orderId;
        this.date = date;
        this.details = details;
    }

    public Invoice() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
