package com.example.epamproj.dao;

import com.example.epamproj.dao.entities.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TariffDAO implements AbstractTariffDAO{

    private static Logger log = LogManager.getLogger(TariffDAO.class.getName());
    private static TariffDAO instance;
    final String GET_ALL_TARIFFS = "SELECT * FROM tariffs";
    final String GET_TARIFF_BY_ID = "SELECT * FROM tariffs WHERE id = ?";
    final String ADD_TARIFF = "INSERT INTO tariffs(weight, distance, dimension) VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE tariffs SET weight=?, distance=?, dimension=? WHERE id=?";
    final String DELETE_BY_ID = "DELETE FROM tariffs WHERE id = ?";
    private final ConnectionPool connectionPool = new ConnectionPool("jdbc:mysql://localhost:3306/cargo_delivery", "root", "admin");

    private TariffDAO(){}

    public static synchronized TariffDAO getInstance() {
        if (instance == null) instance = new TariffDAO();
        return instance;
    }

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
                tariff.setWeight(rs.getFloat(2));
                tariff.setDistance(rs.getFloat(3));
                tariff.setDimension(rs.getFloat(4));
                res.add(tariff);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
                tariff.setWeight(rs.getFloat(2));
                tariff.setDistance(rs.getFloat(3));
                tariff.setDimension(rs.getFloat(4));
            }
        } finally {
            rs.close();
            ps.close();
            connection.close();
        }

        return tariff;
    }

    @Override
    public boolean add(Tariff entity) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement st = connection.prepareStatement(ADD_TARIFF)) {
            st.setFloat(1, entity.getWeight());
            st.setFloat(2, entity.getDistance());
            st.setFloat(3, entity.getDimension());
            st.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean update(Tariff entity) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement st = connection.prepareStatement(UPDATE)) {
            st.setFloat(1, entity.getWeight());
            st.setFloat(2, entity.getDistance());
            st.setFloat(3, entity.getDimension());
            st.setInt(4, entity.getId());
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

}
