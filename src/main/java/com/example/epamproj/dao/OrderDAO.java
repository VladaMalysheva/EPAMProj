package com.example.epamproj.dao;

import com.example.epamproj.dao.entities.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements AbstractOrderDAO{

    private static Logger log = LogManager.getLogger(OrderDAO.class.getName());
    private static OrderDAO instance;
    final String GET_ALL_ORDERS = "SELECT * FROM orders";
    final String GET_ORDER_BY_ID = "SELECT * FROM orders WHERE orderId = ?";
    final String ADD_ORDER = "INSERT INTO orders(userId, directionId, weight, dimensions, total_price," +
            " type_of_cargo, date, address, point_of_departure, destination) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE orders SET userId=?, directionId=?, weight=?, dimensions=?, total_price=?," +
            " type_of_cargo=?, date=?, address=?, point_of_departure=?, destination=? WHERE orderId=?";
    final String DELETE_BY_ID = "DELETE FROM orders WHERE orderId = ?";
    final String UPDATE_STATUS = "UPDATE orders SET status=? WHERE orderId=?";
    private final ConnectionPool connectionPool = new ConnectionPool("jdbc:mysql://localhost:3306/cargo_delivery", "root", "admin");

    private OrderDAO(){}

    public static synchronized OrderDAO getInstance() {
        if (instance == null) instance = new OrderDAO();
        return instance;
    }

    @Override
    public List<Order> getAll() throws SQLException {
        List<Order> res = new ArrayList<>();
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
            rs = st.executeQuery(GET_ALL_ORDERS);
            while (rs.next()) {
                Order order = new Order();
                prepareOrder(rs, order);
                res.add(order);
            }
        } finally {
            rs.close();
            st.close();
            connection.close();

        }

        return res;
    }

    private void prepareOrder(ResultSet rs, Order order) throws SQLException {
        order.setId(rs.getInt(1));
        order.setUserId(rs.getInt(2));
        order.setDirectionId(rs.getInt(3));
        order.setWeight(rs.getFloat(4));
        order.setDimensions(rs.getFloat(5));
        order.setTotalPrice(rs.getDouble(6));
        order.setTypeOfCargo(rs.getString(7));
        order.setDate(rs.getDate(8));
        order.setAddress(rs.getString(9));
        order.setPointOfDeparture(rs.getString(10));
        order.setDestination(rs.getString(11));
        order.setCurrency(rs.getString(12));
        order.setStatus(rs.getString(13));
        order.setUser(UserDAO.getInstance().getById(rs.getInt(2)));
        order.setDirection(DirectionDAO.getInstance().getById(rs.getInt(3)));
    }

    @Override
    public Order getById(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order order = null;
        try {
            ps = connection.prepareStatement(GET_ORDER_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                order = new Order();
                prepareOrder(rs, order);
            }
        } finally {
            rs.close();
            ps.close();
            connection.close();
        }

        return order;
    }

    @Override
    public boolean add(Order entity) throws SQLException {
        return addToStatement(entity, ADD_ORDER);
    }

    @Override
    public boolean update(Order entity) throws SQLException {
        return addToStatement(entity, UPDATE);
    }

    private boolean addToStatement(Order entity, String update) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement st = connection.prepareStatement(update)) {
            st.setInt(1, entity.getUserId());
            st.setInt(2, entity.getDirectionId());
            st.setFloat(3, entity.getWeight());
            st.setFloat(4, entity.getDimensions());
            st.setDouble(5, entity.getTotalPrice());
            st.setString(6, entity.getTypeOfCargo());
            st.setDate(7, entity.getDate());
            st.setString(8, entity.getAddress());
            st.setString(9, entity.getPointOfDeparture());
            st.setString(10, entity.getDestination());
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
    public boolean updateStatus(String status, int id) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement st = connection.prepareStatement(UPDATE_STATUS)) {
            st.setString(1, status);
            st.setInt(2, id);
            st.executeUpdate();
        }
        return true;
    }
}
