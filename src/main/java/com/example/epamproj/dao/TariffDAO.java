package com.example.epamproj.dao;

import com.example.epamproj.dao.entities.Tariff;
import com.example.epamproj.dao.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TariffDAO implements AbstractTariffDAO{

    private static Logger log = LogManager.getLogger(TariffDAO.class.getName());

    private static TariffDAO instance;

    public static synchronized TariffDAO getInstance() {
        if (instance == null) instance = new TariffDAO();
        return instance;
    }

    private TariffDAO(){}
    private final ConnectionPool connectionPool = new ConnectionPool("jdbc:mysql://localhost:3306/cargo_delivery", "root", "admin");
    final String GET_ALL_TARIFFS = "SELECT * FROM tariffs";
    final String GET_TARIFF_BY_ID = "SELECT * FROM tariffs WHERE id = ?";
    final String GET_TARIFF_BY_NAME = "SELECT * FROM tariffs WHERE name = ?";
    final String ADD_TARIFF = "INSERT INTO tariffs(name, value) VALUES (?, ?)";
    final String UPDATE = "UPDATE tariffs SET name=?, value=? WHERE id=?";
    final String DELETE_BY_ID = "DELETE FROM tariffs WHERE id = ?";
    @Override
    public List<Tariff> getAll() throws SQLException {
        List<Tariff> res = new ArrayList<>();
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
            rs = st.executeQuery(GET_ALL_TARIFFS);
            while (rs.next()) {
                Tariff tariff = new Tariff();
                tariff.setId(rs.getInt(1));
                tariff.setName(rs.getString(2));
                tariff.setValue(rs.getFloat(3));
                res.add(tariff);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert rs != null;
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
    public Tariff getById(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Tariff tariff = null;
        try {
            ps = connection.prepareStatement(GET_TARIFF_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                tariff = new Tariff();
                tariff.setId(rs.getInt(1));
                tariff.setName(rs.getString(2));
                tariff.setValue(rs.getFloat(3));
            }
        } finally {
            assert rs != null;
            rs.close();
            ps.close();
            connection.close();
        }

        return tariff;
    }

    @Override
    public boolean add(Tariff entity) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(ADD_TARIFF);
            st.setString(1, entity.getName());
            st.setFloat(2, entity.getValue());
            st.executeUpdate();
        }finally {
            assert st != null;
            st.close();
            connection.close();
        }
        return true;
    }

    @Override
    public boolean update(Tariff entity) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(UPDATE);
            st.setString(1, entity.getName());
            st.setFloat(2, entity.getValue());
            st.setInt(3, entity.getId());
            st.executeUpdate();
        }finally {
            assert st != null;
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
            assert ps != null;
            ps.close();
            connection.close();
        }

        return true;
    }

    @Override
    public Tariff getByName(String name) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Tariff tariff = null;
        try {
            ps = connection.prepareStatement(GET_TARIFF_BY_NAME);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                tariff = new Tariff();
                tariff.setId(rs.getInt(1));
                tariff.setName(rs.getString(2));
                tariff.setValue(rs.getFloat(3));
            }
        } finally {
            assert rs != null;
            rs.close();
            ps.close();
            connection.close();
        }

        return tariff;
    }
}
