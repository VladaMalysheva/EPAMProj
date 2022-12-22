package com.example.epamproj.command;

import com.example.epamproj.exceptions.AlertException;
import com.example.epamproj.exceptions.AppException;
import com.example.epamproj.exceptions.DBException;
import com.example.epamproj.dao.UserDAO;
import com.example.epamproj.dao.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class TopUpCommand implements Command {
    private static Logger log = LogManager.getLogger(TopUpCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException, AlertException {
        int id = ((User) request.getSession().getAttribute("user")).getUserId();
        double money;
        try {
            money = Double.parseDouble(request.getParameter("money"));
            if(money>Double.MAX_VALUE || money<Double.MIN_VALUE){
                throw new Exception();
            }
        } catch (Exception e) {
            log.error("Cannot convert parameter \"money\" to double");
            throw new AppException("Invalid value");
        }
        try {
            UserDAO.getInstance().topUp(id, money);
        } catch (SQLException e) {
            log.error("Failed to top up cash");
            throw new DBException("Failed to top up cash", e);
        }
        return "/controller?command=showOrders";
    }
}
