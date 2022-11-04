package com.example.epamproj.dao;

import com.example.epamproj.command.CreateInvoiceCommand;
import com.example.epamproj.dao.entities.Invoice;
import com.example.epamproj.dao.entities.Report;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO implements AbstractInvoiceDAO{

    private static Logger log = LogManager.getLogger(InvoiceDAO.class.getName());

    private static InvoiceDAO instance;

    public static synchronized InvoiceDAO getInstance() {
        if (instance == null) instance = new InvoiceDAO();
        return instance;
    }

    private InvoiceDAO(){

    }

    private final ConnectionPool connectionPool = new ConnectionPool("jdbc:mysql://localhost:3306/cargo_delivery", "root", "admin");

    final String GET_ALL = "SELECT * FROM invoice";
    final String GET_ALL_BY_USER = "select invoiceId, orderId, date, details, status\n" +
            "from\n" +
            "    (\n" +
            "        select invoiceId, orders.orderId, userId, invoice.date, details, invoice.status\n" +
            "        from orders inner join invoice on invoice.orderId = orders.orderId\n" +
            "    ) as tbl\n" +
            "where tbl.userId = ? AND tbl.status LIKE 'active'";
    final String DELETE_BY_ID = "DELETE FROM invoice WHERE invoiceId = ?";
    final String GET_BY_ID = "SELECT * FROM invoice WHERE invoiceId = ?";
    final String ADD = "INSERT INTO invoice(orderId, date, details) VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE invoice SET orderId=?, date=?, details=? WHERE invoiceId=?";
    final String UPDATE_STATUS = "UPDATE invoice SET status=? WHERE invoiceId=?";


    @Override
    public List<Invoice> getAll() throws SQLException {
        List<Invoice> res = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(GET_ALL)) {
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(rs.getInt(1));
                invoice.setOrderId(rs.getInt(2));
                invoice.setDate(rs.getDate(3));
                invoice.setDetails(rs.getString(4));
                invoice.setStatus(rs.getString(5));
                invoice.setOrder(OrderDAO.getInstance().getById(rs.getInt(2)));
                res.add(invoice);
            }
        }

        return res;
    }

    @Override
    public Invoice getById(int id) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Invoice invoice = null;
        try {
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                invoice = new Invoice();
                invoice.setId(rs.getInt(1));
                invoice.setOrderId(rs.getInt(2));
                invoice.setDate(rs.getDate(3));
                invoice.setDetails(rs.getString(4));
                invoice.setStatus(rs.getString(5));
                invoice.setOrder(OrderDAO.getInstance().getById(rs.getInt(2)));
            }
        } finally {
            rs.close();
            ps.close();
            connection.close();
        }

        return invoice;
    }

    @Override
    public boolean add(Invoice entity) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(ADD);
            st.setInt(1, entity.getOrderId());
            st.setDate(2, entity.getDate());
            st.setString(3, entity.getDetails());
            st.executeUpdate();
        }finally {
            st.close();
            connection.close();
        }
        return true;
    }

    @Override
    public boolean add(Invoice entity, int orderId) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement st = null;
        try {
//            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//            connection.setAutoCommit(false);
            st = connection.prepareStatement(ADD);
            st.setInt(1, entity.getOrderId());
            st.setDate(2, entity.getDate());
            st.setString(3, entity.getDetails());
            st.executeUpdate();
            OrderDAO.getInstance().updateStatus("unpaid", orderId);
//            connection.commit();
        } catch (SQLException e) {
            log.error("failed to add invoice or update order status");
            try{
                connection.rollback();
            }catch (SQLException ex){
                log.error("failed to rollback");
            }
            throw new SQLException(e);
        }finally {
            st.close();
            connection.close();
        }
        return true;
    }

    @Override
    public boolean pay(int invId) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            changeStatus(invId, "inactive");           //deactivate invoice
            Invoice invoice = getById(invId);
            UserDAO.getInstance().withdrawMoney(invoice.getOrder().getUserId(), invoice.getOrder().getTotalPrice());  //withdraw money
              long millis=System.currentTimeMillis();
              Date date=new Date(millis);
              ReportDAO.getInstance().add(new Report(invId, date));     //create report
            OrderDAO.getInstance().updateStatus("paid", invoice.getOrderId());         //change order status
              connection.commit();
        } catch (SQLException e) {
            log.error("failed to pay invoice");
            try{
                connection.rollback();
            }catch (SQLException ex){
                log.error("failed to rollback");
            }
            throw new SQLException(e);
        }finally {
            connection.close();
        }
        return true;
    }

    @Override
    public boolean changeStatus(int id, String status) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(UPDATE_STATUS);
            st.setString(1, status);
            st.setInt(2, id);
            st.executeUpdate();
        }finally {
            st.close();
            connection.close();
        }
        return true;
    }

    @Override
    public boolean update(Invoice entity) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(UPDATE);
            st.setInt(1, entity.getOrderId());
            st.setDate(2, entity.getDate());
            st.setString(3, entity.getDetails());
            st.executeUpdate();
        }finally {
            st.close();
            connection.close();
        }
        return true;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        }

        return true;
    }

    @Override
    public List<Invoice> getInvoicesByUser(int userId) throws SQLException {
        Connection connection = connectionPool.getConnection();
        List<Invoice> res = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(GET_ALL_BY_USER);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(rs.getInt(1));
                invoice.setOrderId(rs.getInt(2));
                invoice.setDate(rs.getDate(3));
                invoice.setDetails(rs.getString(4));
                invoice.setStatus(rs.getString(5));
                invoice.setOrder(OrderDAO.getInstance().getById(rs.getInt(2)));
                res.add(invoice);
            }
        } finally {
            rs.close();
            ps.close();
            connection.close();
        }

        return res;
    }
}
