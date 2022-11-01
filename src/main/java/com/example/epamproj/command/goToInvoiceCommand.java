package com.example.epamproj.command;

import com.example.epamproj.dao.DBException;
import com.example.epamproj.dao.OrderDAO;
import com.example.epamproj.dao.entities.Order;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class goToInvoiceCommand implements Command {

    private static Logger log = LogManager.getLogger(goToInvoiceCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        log.info("parameter => " + (request.getParameter("orderId")));
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        log.info("order id => " + orderId);
        Order order = null;
        try {
            order = OrderDAO.getInstance().getById(orderId);
            log.info("order => " + order);
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e.getCause());
        }
        request.setAttribute("orderInv", order);
//        log.info("request.getAttribute(\"order\") => " + request.getAttribute("order"));
        return "/invoice.jsp";
    }
}
