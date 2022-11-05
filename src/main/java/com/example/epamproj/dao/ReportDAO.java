package com.example.epamproj.dao;

import com.example.epamproj.dao.entities.Report;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO implements AbstractReportDAO{

    private static ReportDAO instance;
    private static Logger log = LogManager.getLogger(ReportDAO.class.getName());
    final String GET_ALL_REPORTS = "SELECT * FROM report";
    final String GET_REPORT_BY_ID = "SELECT * FROM report WHERE reportId = ?";
    final String ADD_REPORT = "INSERT INTO report(invoiceId, dateOfPaying) VALUES (?, ?)";
    final String UPDATE = "UPDATE report SET invoiceId=?, dateOfPaying=? WHERE reportId=?";
    final String DELETE_BY_ID = "DELETE FROM report WHERE reportId = ?";
    private final ConnectionPool connectionPool = new ConnectionPool("jdbc:mysql://localhost:3306/cargo_delivery", "root", "admin");

    private ReportDAO(){}

    public static synchronized ReportDAO getInstance() {
        if (instance == null) instance = new ReportDAO();
        return instance;
    }

    @Override
    public List<Report> getAll() throws SQLException {
        List<Report> res = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(GET_ALL_REPORTS)) {
            while (rs.next()) {
                Report report = new Report();
                report.setReportId(rs.getInt(1));
                report.setInvoiceId(rs.getInt(2));
                report.setDateOfPaying(rs.getDate(3));
                report.setInvoice(InvoiceDAO.getInstance().getById(rs.getInt(2)));
                res.add(report);
            }
        }

        return res;
    }

    @Override
    public Report getById(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Report report = null;
        try {
            ps = connection.prepareStatement(GET_REPORT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                report = new Report();
                report.setReportId(rs.getInt(1));
                report.setInvoiceId(rs.getInt(2));
                report.setDateOfPaying(rs.getDate(3));
                report.setInvoice(InvoiceDAO.getInstance().getById(rs.getInt(2)));
            }
        } finally {
            rs.close();
            ps.close();
            connection.close();
        }

        return report;
    }

    @Override
    public boolean add(Report entity) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement st = connection.prepareStatement(ADD_REPORT)) {
            st.setInt(1, entity.getInvoiceId());
            st.setDate(2, entity.getDateOfPaying());
            st.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean update(Report entity) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement st = connection.prepareStatement(UPDATE)) {
            st.setInt(1, entity.getInvoiceId());
            st.setDate(2, entity.getDateOfPaying());
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
