package com.example.epamproj.dao;

import com.example.epamproj.dao.entities.User;
import com.example.epamproj.exceptions.AlertException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements AbstractUserDAO{

    private static UserDAO instance;
    private static Logger log = LogManager.getLogger(UserDAO.class.getName());
    final String GET_ALL_USERS = "SELECT * FROM user";
    final String GET_USER_BY_ID = "SELECT * FROM user WHERE userId = ?";
    final String ADD_USER = "INSERT INTO user(role, name, surname, patronymic, phone, login, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE user SET role=?, name=?, surname=?, patronymic=?, phone=?, login=?, password=? WHERE userId=?";
    final String DELETE_BY_ID = "DELETE FROM user WHERE userId = ?";
    final String GET_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    final String DELETE_BY_LOGIN = "DELETE FROM user WHERE login = ?";
    final String WITHDRAW_MONEY = "UPDATE user SET cash = cash-? WHERE userId=?";
    final String TOP_UP_MONEY = "UPDATE user SET cash = cash+? WHERE userId=?";
    private final ConnectionPool connectionPool = new ConnectionPool("jdbc:mysql://localhost:3306/cargo_delivery", "root", "admin");

    private UserDAO(){}

    public static synchronized UserDAO getInstance() {
        if (instance == null) instance = new UserDAO();
        return instance;
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> res = new ArrayList<>();
        Connection connection = null;

        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Statement st = null;
        ResultSet rs = null;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(GET_ALL_USERS);
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt(1));
                user.setRole(rs.getString(2));
                user.setName(rs.getString(3));
                user.setSurname(rs.getString(4));
                user.setPatronymic(rs.getString(5));
                user.setPhone(rs.getString(6));
                user.setLogin(rs.getString(7));
                user.setPassword(rs.getString(8));
                user.setCash(rs.getDouble(9));
                res.add(user);
            }
        } finally {
                rs.close();
                st.close();
                connection.close();

        }

        return res;
    }

    @Override
    public User getById(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        try {
            ps = connection.prepareStatement(GET_USER_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setRole(rs.getString(2));
                user.setName(rs.getString(3));
                user.setSurname(rs.getString(4));
                user.setPatronymic(rs.getString(5));
                user.setPhone(rs.getString(6));
                user.setLogin(rs.getString(7));
                user.setPassword(rs.getString(8));
                user.setCash(rs.getDouble(9));
            }
        } finally {

            rs.close();
            ps.close();
            connection.close();
        }

        return user;
    }

    @Override
    public boolean add(User entity) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement st = connection.prepareStatement(ADD_USER)) {
            addToStatement(entity, st);
            st.executeUpdate();
        }
        return true;
    }

    private void addToStatement(User entity, PreparedStatement st) throws SQLException {
        st.setString(1, entity.getRole());
        st.setString(2, entity.getName());
        st.setString(3, entity.getSurname());
        st.setString(4, entity.getPatronymic());
        st.setString(5, entity.getPhone());
        st.setString(6, entity.getLogin());
        st.setString(7, entity.getPassword());
    }

    @Override
    public boolean update(User entity) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement st = connection.prepareStatement(UPDATE)) {
            addToStatement(entity, st);
            st.setInt(8, entity.getUserId());
            st.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        }

        return true;
    }

    @Override
    public User getByLogin(String login) throws SQLException{
        Connection connection = connectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        try {
            ps = connection.prepareStatement(GET_BY_LOGIN);
            ps.setString(1, login);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setRole(rs.getString(2));
                user.setName(rs.getString(3));
                user.setSurname(rs.getString(4));
                user.setPatronymic(rs.getString(5));
                user.setPhone(rs.getString(6));
                user.setLogin(rs.getString(7));
                user.setPassword(rs.getString(8));
                user.setCash(rs.getDouble(9));
            }
            if(user == null)throw new SQLException();
        } finally {
            rs.close();
            ps.close();
            connection.close();
        }

        return user;
    }

    @Override
    public void withdrawMoney(int id, double money) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement st = connection.prepareStatement(WITHDRAW_MONEY)) {
//            connection.setAutoCommit(false);
            if(getById(id).getCash()<money)throw new SQLException();
            st.setDouble(1, money);
            st.setInt(2, id);
            st.executeUpdate();
//            connection.commit();
        }
//        catch (SQLException e) {
//            connection.rollback();
//        }
    }

    public void withdrawMoney(int id, double money, Connection con) throws SQLException {
        try (PreparedStatement st = con.prepareStatement(WITHDRAW_MONEY)) {
            if(getById(id).getCash()<money)throw new SQLException();
            st.setDouble(1, money);
            st.setInt(2, id);
            st.executeUpdate();
        }
    }

    @Override
    public boolean topUp(int id, double money) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement st = connection.prepareStatement(TOP_UP_MONEY)) {
            st.setDouble(1, money);
            st.setInt(2, id);
            st.executeUpdate();
        }
        return true;
    }

    @Override
    public void deleteByLogin(String login) throws SQLException {
        User user = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_BY_LOGIN)) {
            ps.setString(1, login);
            ps.executeUpdate();

        }

    }
}
