package com.example.epamproj.dao;

import com.example.epamproj.dao.entities.Invoice;

import java.sql.SQLException;
import java.util.List;

public interface AbstractInvoiceDAO extends AbstractEntityDAO<Invoice> {
    boolean add(Invoice entity, int orderInv) throws SQLException;

    List<Invoice> getInvoicesByUser(int userId) throws SQLException;
}
