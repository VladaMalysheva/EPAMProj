package com.example.epamproj.dao;

import com.example.epamproj.dao.entities.Direction;

import java.sql.SQLException;
import java.util.List;

public interface AbstractDirectionDAO extends AbstractEntityDAO<Direction> {
    Direction getByName(String name) throws SQLException;
    List<Direction> getAllOrderBy(String Characteristic) throws SQLException;
}
