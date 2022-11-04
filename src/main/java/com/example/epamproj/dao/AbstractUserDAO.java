package com.example.epamproj.dao;

import com.example.epamproj.dao.entities.User;

import java.sql.SQLException;

public interface AbstractUserDAO extends AbstractEntityDAO<User> {
    User getByLogin(String login) throws SQLException;
    void withdrawMoney(int id, double money) throws SQLException;
    boolean topUp(int id, double money) throws SQLException;

    void deleteByLogin(String login) throws SQLException;

}
