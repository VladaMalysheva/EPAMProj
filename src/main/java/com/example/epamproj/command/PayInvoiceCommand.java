package com.example.epamproj.command;

import com.example.epamproj.exceptions.AlertException;
import com.example.epamproj.exceptions.DBException;
import com.example.epamproj.dao.InvoiceDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class PayInvoiceCommand implements Command {
    private static final Logger log = LogManager.getLogger(PayInvoiceCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException, AlertException {
        int invId = Integer.parseInt(request.getParameter("invoice"));
        try {
            InvoiceDAO.getInstance().pay(invId);
        } catch (SQLException e) {
            log.error("Failed to pay invoice");
            throw new AlertException("Cannot pay invoice, check your balance or try again later", "/controller?command=showOrders");
        }
        return "/controller?command=showOrders";
    }
}
