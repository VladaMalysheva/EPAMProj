package com.example.epamproj.dao.entities;

import java.io.Serializable;

public class Tariff implements Serializable {
    private int id;
    private String name;
    private float value;

    public Tariff() {
    }

    public Tariff(String name, float value) {
        this.name = name;
        this.value = value;
    }

    public Tariff(int id, String name, float value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

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

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
