package com.example.epamproj.command;

import com.example.epamproj.dao.DBException;
import com.example.epamproj.dao.InvoiceDAO;
import com.example.epamproj.dao.entities.Invoice;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.SQLException;

public class CreateInvoiceCommand implements Command {

    private static Logger log = LogManager.getLogger(CreateInvoiceCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        log.info("parameter date => " + request.getParameter("date"));
        log.info("parameter details => " + request.getParameter("details"));
        int orderInv = Integer.parseInt(request.getParameter("orderInv"));
        Date date = Date.valueOf(request.getParameter("date"));
        String details = request.getParameter("details");
        Invoice invoice = new Invoice(orderInv, date, details);
        try {
            InvoiceDAO.getInstance().add(invoice);
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e.getCause());
        }
        return "/index.jsp";
    }
}
