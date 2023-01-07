package com.example.epamproj.dao.entities;

import java.io.Serializable;

public class Tariff implements Serializable {
    private int id;
    private float weight;
    private float distance;
    private float dimension;

    public Tariff() {
    }

    public Tariff(int id, float weight, float distance, float dimension) {
        this.id = id;
        this.weight = weight;
        this.distance = distance;
        this.dimension = dimension;
    }

    public Tariff(float weight, float distance, float dimension) {
        this.weight = weight;
        this.distance = distance;
        this.dimension = dimension;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getDimension() {
        return dimension;
    }

    public void setDimension(float dimension) {
        this.dimension = dimension;
    }
}
