package com.example.epamproj.dao;

import com.example.epamproj.dao.entities.Order;

import java.sql.SQLException;

public interface AbstractOrderDAO extends AbstractEntityDAO<Order> {
    boolean updateStatus(String status, int id) throws SQLException;

}
