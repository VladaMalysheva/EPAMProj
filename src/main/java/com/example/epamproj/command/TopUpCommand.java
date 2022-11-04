package com.example.epamproj.command;

import com.example.epamproj.dao.DBException;
import com.example.epamproj.dao.UserDAO;
import com.example.epamproj.dao.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;

public class TopUpCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        int id = ((User) request.getSession().getAttribute("user")).getUserId();
        double money = Double.parseDouble(request.getParameter("money"));
        try {
            UserDAO.getInstance().topUp(id, money);
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e.getCause());
        }
        return "/controller?command=showOrders";
    }
}
