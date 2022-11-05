package com.example.epamproj.command;

import com.example.epamproj.dao.DBException;
import com.example.epamproj.dao.InvoiceDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class PayInvoiceCommand implements Command {
    private static Logger log = LogManager.getLogger(PayInvoiceCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        int invId = Integer.parseInt(request.getParameter("invoice"));
        log.info("invoice => " + invId);
        try {
            InvoiceDAO.getInstance().pay(invId);
        } catch (SQLException e) {
            log.error("failed to pay invoice");
            throw new DBException(e.getMessage(), e.getCause());
        }
        return "/controller?command=showOrders";
    }
}
