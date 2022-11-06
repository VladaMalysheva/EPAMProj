package com.example.epamproj.command;

import com.example.epamproj.dao.DBException;
import com.example.epamproj.dao.InvoiceDAO;
import com.example.epamproj.dao.OrderDAO;
import com.example.epamproj.dao.entities.Invoice;
import com.example.epamproj.dao.entities.Order;
import com.example.epamproj.dao.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class GoToInvoiceCommand implements Command {

    private static Logger log = LogManager.getLogger(GoToInvoiceCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        if (((User) request.getSession().getAttribute("user")).getRole().equals("admin")) {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            Order order = null;
            try {
                order = OrderDAO.getInstance().getById(orderId);
            } catch (SQLException e) {
                log.error("Failed to get order from db");
                throw new DBException(e.getMessage(), e.getCause());
            }
            request.setAttribute("orderInv", order);
            log.info("Attribute \"orderInv\" set");
            return "/invoice.jsp";
        }else if (((User) request.getSession().getAttribute("user")).getRole().equals("client")) {
            int id = Integer.parseInt(request.getParameter("invoice"));
            Invoice invoice = null;
            try {
                invoice = InvoiceDAO.getInstance().getById(id);
            } catch (SQLException e) {
                log.error("Failed to get invoice from db");
                throw new DBException(e.getMessage(), e.getCause());
            }
            request.setAttribute("invoice", invoice);
            log.info("Attribute \"invoice\" set");
            return "/invoice-user.jsp";
        }
        log.warn("User is unregistered");
        return "/index.jsp";
    }
}