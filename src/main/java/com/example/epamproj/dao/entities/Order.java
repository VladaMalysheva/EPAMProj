package com.example.epamproj.dao.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private int orderId;
    private List<Cargo> cargos;
    private Date date;
    private Direction destination;
    private User user;

    public int getOrderId() {
        return orderId;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public Date getDate() {
        return date;
    }

    public Direction getDestination() {
        return destination;
    }

    public User getUser() {
        return user;
    }

    public int getPrice(){
        int res = 0;
        for (Cargo cargo:cargos) {
            res+= cargo.getPrice();
        }
        return res;
    }

    public int getWeight(){
        int res = 0;
        for (Cargo cargo:cargos) {
            res+= cargo.getWeight();
        }
        return res;
    }

    public int getVolume(){
        int res = 0;
        for (Cargo cargo:cargos) {
            res+= cargo.getVolume();
        }
        return res;
    }
}
