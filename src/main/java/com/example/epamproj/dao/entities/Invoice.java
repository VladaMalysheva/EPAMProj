package com.example.epamproj.dao.entities;

import java.io.Serializable;
import java.util.Date;

public class Invoice implements Serializable {
    private int invoiceId;
    private Order order;
    private User user = order.getUser();
    private int price = order.getPrice();
    private String userName = user.getName();
    private String userSurname = user.getSurname();
    private String userPatronymic = user.getPatronymic();
    private Date date = order.getDate();

    public int getInvoiceId() {
        return invoiceId;
    }

    public Order getOrder() {
        return order;
    }

    public User getUser() {
        return user;
    }

    public int getPrice() {
        return price;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public String getUserPatronymic() {
        return userPatronymic;
    }

    public Date getDate() {
        return date;
    }
}
