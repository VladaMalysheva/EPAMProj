package com.example.epamproj.command;

import com.example.epamproj.dao.DBException;
import com.example.epamproj.dao.InvoiceDAO;
import com.example.epamproj.dao.OrderDAO;
import com.example.epamproj.dao.UserDAO;
import com.example.epamproj.dao.entities.Invoice;
import com.example.epamproj.dao.entities.Order;
import com.example.epamproj.dao.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class ShowOrdersCommand implements Command {
    private static Logger log = LogManager.getLogger(ShowOrdersCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        List<Order> orders = null;
        try {
            orders = OrderDAO.getInstance().getAll();
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e.getCause());
        }
        if(((User)request.getSession().getAttribute("user")).getRole().equals("admin")){
            try {
                orders.removeIf(o -> Objects.equals(o.getStatus(), "paid"));
            }catch (Exception e){
                log.error("cannot filter orders");
            }
            request.setAttribute("orders", orders);
            return "/orders.jsp";
        }
        if(((User)request.getSession().getAttribute("user")).getRole().equals("client")){
            final int userId = ((User) request.getSession().getAttribute("user")).getUserId();
            try {
                orders.removeIf(o ->  o.getUserId() != userId);
            }catch (Exception e){
                log.error("cannot filter orders");
            }
            List<Invoice> invoices = null;
            try {
                invoices = InvoiceDAO.getInstance().getInvoicesByUser(userId);
                request.getSession().setAttribute("user", UserDAO.getInstance().getById(userId));
            } catch (SQLException e) {
                throw new DBException(e.getMessage(), e.getCause());
            }
            request.setAttribute("orders", orders);
            request.setAttribute("invoices", invoices);
            return "/user-cabinet.jsp";
        }

        return null;

    }
}
