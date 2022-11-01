package com.example.epamproj.dao;

import com.example.epamproj.command.GetProducts;
import com.example.epamproj.dao.entities.Direction;
import com.example.epamproj.dao.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DirectionDAO implements AbstractDirectionDAO{

    private static Logger log = LogManager.getLogger(DirectionDAO.class.getName());

    private static DirectionDAO instance;

    public static synchronized DirectionDAO getInstance() {
        if (instance == null) instance = new DirectionDAO();
        return instance;
    }

    private DirectionDAO(){}

    private final ConnectionPool connectionPool = new ConnectionPool("jdbc:mysql://localhost:3306/cargo_delivery", "root", "admin");
    final String GET_ALL_DIRECTIONS = "SELECT * FROM directions";

    final String GET_ALL_ORDERED_DISTANCE = "SELECT * FROM directions ORDER BY distance";

    final String GET_ALL_ORDERED_NAME = "SELECT * FROM directions ORDER BY name";

    final String GET_DIRECTION_BY_ID = "SELECT * FROM directions WHERE id = ?";
    final String ADD_DIRECTION = "INSERT INTO directions(name, place1, place2, distance, image) VALUES (?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE directions SET name=?, place1=?, place2=?, distance=?, image=? WHERE id=?";
    final String DELETE_BY_ID = "DELETE FROM directions WHERE id = ?";
    final String GET_BY_NAME = "SELECT * FROM directions WHERE name = ?";

    @Override
    public Direction getByName(String name) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Direction direction = null;
        try {
            ps = connection.prepareStatement(GET_BY_NAME);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                direction = new Direction();
                direction.setId(rs.getInt(1));
                direction.setName(rs.getString(2));
                direction.setPlace1(rs.getString(3));
                direction.setPlace2(rs.getString(4));
                direction.setDistance(rs.getDouble(5));
                direction.setImage(rs.getString(6));
                direction.setNameOfReceiver(rs.getString(7));
                direction.setSurnameOfReceiver(rs.getString(8));
                direction.setPatronymicOfReceiver(rs.getString(9));
            }
        } finally {
            rs.close();
            ps.close();
            connection.close();
        }

        return direction;
    }

    @Override
    public List<Direction> getAllOrderBy(String Characteristic) throws SQLException {
        List<Direction> res = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            if(Characteristic.equals("name")){
                rs = st.executeQuery(GET_ALL_ORDERED_NAME);
            } else if (Characteristic.equals("distance")) {
                rs = st.executeQuery(GET_ALL_ORDERED_DISTANCE);
            }
            while (true) {
                assert rs != null;
                if (!rs.next()) break;
                Direction direction = new Direction();
                direction.setId(rs.getInt(1));
                direction.setName(rs.getString(2));
                direction.setPlace1(rs.getString(3));
                direction.setPlace2(rs.getString(4));
                direction.setDistance(rs.getDouble(5));
                direction.setImage(rs.getString(6));
                direction.setNameOfReceiver(rs.getString(7));
                direction.setSurnameOfReceiver(rs.getString(8));
                direction.setPatronymicOfReceiver(rs.getString(9));
                res.add(direction);
            }
        } finally {
            rs.close();
            st.close();
            connection.close();
        }
        return res;
    }


    @Override
    public List<Direction> getAll() throws SQLException {
        List<Direction> res = new ArrayList<>();
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
            rs = st.executeQuery(GET_ALL_DIRECTIONS);
            while (rs.next()) {
                Direction direction = new Direction();
                direction.setId(rs.getInt(1));
                direction.setName(rs.getString(2));
                direction.setPlace1(rs.getString(3));
                direction.setPlace2(rs.getString(4));
                direction.setDistance(rs.getDouble(5));
                direction.setImage(rs.getString(6));
                direction.setNameOfReceiver(rs.getString(7));
                direction.setSurnameOfReceiver(rs.getString(8));
                direction.setPatronymicOfReceiver(rs.getString(9));
                res.add(direction);
            }
        } finally {
            try {
                rs.close();
                st.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return res;
    }

    @Override
    public Direction getById(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Direction direction = null;
        try {
            ps = connection.prepareStatement(GET_DIRECTION_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                direction = new Direction();
                direction.setId(rs.getInt(1));
                direction.setName(rs.getString(2));
                direction.setPlace1(rs.getString(3));
                direction.setPlace2(rs.getString(4));
                direction.setDistance(rs.getDouble(5));
                direction.setImage(rs.getString(6));
                direction.setNameOfReceiver(rs.getString(7));
                direction.setSurnameOfReceiver(rs.getString(8));
                direction.setPatronymicOfReceiver(rs.getString(9));
            }
        } finally {
            rs.close();
            ps.close();
            connection.close();
        }

        return direction;
    }

    @Override
    public boolean add(Direction entity) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(ADD_DIRECTION);
            st.setString(1, entity.getName());
            st.setString(2, entity.getPlace1());
            st.setString(3, entity.getPlace2());
            st.setDouble(4, entity.getDistance());
            st.setString(5, entity.getImage());
            st.executeUpdate();
        }finally {
            st.close();
            connection.close();
        }
        return true;
    }

    @Override
    public boolean update(Direction entity) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(UPDATE);
            st.setString(1, entity.getName());
            st.setString(2, entity.getPlace1());
            st.setString(3, entity.getPlace2());
            st.setDouble(4, entity.getDistance());
            st.setString(5, entity.getImage());
            st.executeUpdate();
        }finally {
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
            ps.close();
            connection.close();
        }

        return true;
    }
}
