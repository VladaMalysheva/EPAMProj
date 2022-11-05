package com.example.epamproj.dao;

import com.example.epamproj.dao.entities.Tariff;

import java.sql.SQLException;

public interface AbstractTariffDAO extends AbstractEntityDAO<Tariff> {
    Tariff getByName(String name) throws SQLException;
}
