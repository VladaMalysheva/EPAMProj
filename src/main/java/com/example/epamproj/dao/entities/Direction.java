package com.example.epamproj.dao.entities;

import java.io.Serializable;

public class Direction implements Serializable {
    private int id;
    private String name;
    private String place1;
    private String place2;
    private double distance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace1() {
        return place1;
    }

    public void setPlace1(String place1) {
        this.place1 = place1;
    }

    public String getPlace2() {
        return place2;
    }

    public void setPlace2(String place2) {
        this.place2 = place2;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;

    public Direction(String name, String place1, String place2, double distance, String image) {
        this.name = name;
        this.place1 = place1;
        this.place2 = place2;
        this.distance = distance;
        this.image = image;
    }

    public Direction() {
    }

    public Direction(int id, String name, String place1, String place2, double distance, String image) {
        this.id = id;
        this.name = name;
        this.place1 = place1;
        this.place2 = place2;
        this.distance = distance;
        this.image = image;
    }
}
