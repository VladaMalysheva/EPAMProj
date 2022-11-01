package com.example.epamproj.dao;

import com.example.epamproj.dao.entities.Invoice;

import java.util.List;

public interface AbstractInvoiceDAO extends AbstractEntityDAO<Invoice> {
    List<Invoice> getInvoicesByUser(int userId);
}
