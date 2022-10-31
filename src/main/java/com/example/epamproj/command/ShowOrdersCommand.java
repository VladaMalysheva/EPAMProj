package com.example.epamproj.command;

import com.example.epamproj.dao.DBException;
import com.example.epamproj.dao.OrderDAO;
import com.example.epamproj.dao.entities.Order;
import com.example.epamproj.dao.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.List;

public class ShowOrdersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        List<Order> orders = null;
        try {
            orders = OrderDAO.getInstance().getAll();
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e.getCause());
        }
        request.setAttribute("orders", orders);
        if(((User)request.getSession().getAttribute("user")).getRole().equals("admin"))return "/orders.jsp";
        else if(((User)request.getSession().getAttribute("user")).getRole().equals("client"))return "/user-cabinet.jsp";

        return null;

    }
}