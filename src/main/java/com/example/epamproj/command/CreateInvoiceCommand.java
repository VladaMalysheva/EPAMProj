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
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Date date = null;
        try {
            date = Date.valueOf(request.getParameter("date"));
        }catch (Exception e){
            log.error("Failed to convert date");
        }
        String details = request.getParameter("details");
        Invoice invoice = new Invoice(orderId, date, details);
        try {
            InvoiceDAO.getInstance().add(invoice, orderId);

        } catch (SQLException e) {
            log.error("failed to add invoice to dao or to update status");
            throw new DBException(e.getMessage(), e.getCause());
        }
        return "/controller?command=showOrders";
    }
}
