package com.example.epamproj.dao;

import com.example.epamproj.dao.entities.Invoice;

import java.sql.SQLException;
import java.util.List;

public interface AbstractInvoiceDAO extends AbstractEntityDAO<Invoice> {
    boolean add(Invoice entity, int orderId) throws SQLException;
    boolean pay(int invId) throws SQLException;
    boolean changeStatus(int id, String status) throws SQLException;

    List<Invoice> getInvoicesByUser(int userId) throws SQLException;
}
