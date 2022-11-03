package com.example.epamproj.dao;

import com.example.epamproj.dao.entities.Invoice;

import java.sql.SQLException;
import java.util.List;

public interface AbstractInvoiceDAO extends AbstractEntityDAO<Invoice> {
    List<Invoice> getInvoicesByUser(int userId) throws SQLException;
}
