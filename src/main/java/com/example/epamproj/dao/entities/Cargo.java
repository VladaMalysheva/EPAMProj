package com.example.epamproj.dao.entities;

import java.io.Serializable;

public class Cargo implements Serializable {
    private int cargoId;
    private String name;
    private int weight;
    private int volume;
    private int price;

    public int getCargoId() {
        return cargoId;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getVolume() {
        return volume;
    }

    public int getPrice() {
        return price;
    }
}
