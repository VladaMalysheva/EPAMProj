package com.example.epamproj.dao;

import java.sql.SQLException;
import java.util.List;

public interface AbstractEntityDAO<T>{

    List<T> getAll() throws SQLException;
    T getById(int id) throws SQLException;
    boolean add(T entity) throws SQLException;
    boolean update(T entity) throws SQLException;
    boolean deleteById(int id) throws SQLException;
}
