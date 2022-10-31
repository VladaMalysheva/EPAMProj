package com.example.epamproj.dao.entities;

import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable {

    private Direction direction;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;
    private int id;
    private Date date;
    private int directionId;
    private int userId;
    private float weight;
    private float dimensions;
    private double totalPrice;
    private String typeOfCargo;
    private String address;
    private String pointOfDeparture;
    private String destination;



    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDirectionId() {
        return directionId;
    }

    public void setDirectionId(int directionId) {
        this.directionId = directionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getDimensions() {
        return dimensions;
    }

    public void setDimensions(float dimensions) {
        this.dimensions = dimensions;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTypeOfCargo() {
        return typeOfCargo;
    }

    public void setTypeOfCargo(String typeOfCargo) {
        this.typeOfCargo = typeOfCargo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPointOfDeparture() {
        return pointOfDeparture;
    }

    public void setPointOfDeparture(String pointOfDeparture) {
        this.pointOfDeparture = pointOfDeparture;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Order(Date date, int directionId, int userId, float weight, float dimensions, double totalPrice, String typeOfCargo, String address, String pointOfDeparture, String destination, String status) {
        this.date = date;
        this.directionId = directionId;
        this.userId = userId;
        this.weight = weight;
        this.dimensions = dimensions;
        this.totalPrice = totalPrice;
        this.typeOfCargo = typeOfCargo;
        this.address = address;
        this.pointOfDeparture = pointOfDeparture;
        this.destination = destination;
        this.status = status;
    }

    public Order(Date date, int directionId, int userId, float weight, float dimensions, double totalPrice, String typeOfCargo, String address, String pointOfDeparture, String destination) {
        this.date = date;
        this.directionId = directionId;
        this.userId = userId;
        this.weight = weight;
        this.dimensions = dimensions;
        this.totalPrice = totalPrice;
        this.typeOfCargo = typeOfCargo;
        this.address = address;
        this.pointOfDeparture = pointOfDeparture;
        this.destination = destination;
    }

    public Order(int id, Date date, int directionId, int userId, float weight, float dimensions, double totalPrice, String typeOfCargo, String address, String pointOfDeparture, String destination, String status) {
        this.id = id;
        this.date = date;
        this.directionId = directionId;
        this.userId = userId;
        this.weight = weight;
        this.dimensions = dimensions;
        this.totalPrice = totalPrice;
        this.typeOfCargo = typeOfCargo;
        this.address = address;
        this.pointOfDeparture = pointOfDeparture;
        this.destination = destination;
        this.status = status;
    }

}
