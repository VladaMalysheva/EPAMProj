package com.example.epamproj.dao;

import com.example.epamproj.dao.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements AbstractUserDAO{

    private static UserDAO instance;

    public static synchronized UserDAO getInstance() {
        if (instance == null) instance = new UserDAO();
        return instance;
    }

    private UserDAO(){

    }
    private final ConnectionPool connectionPool = new ConnectionPool("jdbc:mysql://localhost:3306/cargo_delivery", "root", "admin");
    final String GET_ALL_USERS = "SELECT * FROM user";
    final String GET_USER_BY_ID = "SELECT * FROM user WHERE userId = ?";
    final String ADD_USER = "INSERT INTO user(role, name, surname, patronymic, phone, login, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE user SET role=?, name=?, surname=?, patronymic=?, phone=?, login=?, password=? WHERE userId=?";
    final String DELETE_BY_ID = "DELETE FROM user WHERE userId = ?";
    final String GET_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    final String DELETE_BY_LOGIN = "DELETE FROM user WHERE login = ?";



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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
//                assert rs != null;
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
        Connection connection = connectionPool.getConnection();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(ADD_USER);
            st.setString(1, entity.getRole());
            st.setString(2, entity.getName());
            st.setString(3, entity.getSurname());
            st.setString(4, entity.getPatronymic());
            st.setString(5, entity.getPhone());
            st.setString(6, entity.getLogin());
            st.setString(7, entity.getPassword());
            st.executeUpdate();
        }finally {
            st.close();
            connection.close();
        }
        return true;
    }

    @Override
    public boolean update(User entity) throws SQLException {

        Connection connection = connectionPool.getConnection();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(UPDATE);
            st.setString(1, entity.getRole());
            st.setString(2, entity.getName());
            st.setString(3, entity.getSurname());
            st.setString(4, entity.getPatronymic());
            st.setString(5, entity.getPhone());
            st.setString(6, entity.getLogin());
            st.setString(7, entity.getPassword());
            st.setInt(8, entity.getUserId());
            st.executeUpdate();
        }finally {
//            assert st != null;
            st.close();
            connection.close();
        }
        return true;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(DELETE_BY_ID);
            ps.setInt(1, id);
            ps.executeUpdate();

        } finally {
//            assert ps != null;
            ps.close();
            connection.close();
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
        } finally {
//            assert rs != null;
            rs.close();
            ps.close();
            connection.close();
        }

        return user;
    }

    @Override
    public boolean deleteByLogin(String login) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement ps = null;
        User user = null;
        try {
            ps = connection.prepareStatement(DELETE_BY_LOGIN);
            ps.setString(1, login);
            ps.executeUpdate();

        } finally {
//            assert ps != null;
            ps.close();
            connection.close();
        }

        return true;
    }
}
