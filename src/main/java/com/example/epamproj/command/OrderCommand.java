package com.example.epamproj.command;

import com.example.epamproj.dao.DBException;
import com.example.epamproj.dao.OrderDAO;
import com.example.epamproj.dao.entities.Direction;
import com.example.epamproj.dao.entities.Order;
import com.example.epamproj.dao.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.SQLException;

public class OrderCommand implements Command {
    private static Logger log = LogManager.getLogger(OrderCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        int userId = ((User) request.getSession().getAttribute("user")).getUserId();
        int directionId = ((Direction) request.getSession().getAttribute("productOrd")).getId();
        float weight = (float) request.getSession().getAttribute("weight");
        float dimensions = (float) request.getSession().getAttribute("dimension");
        double totalPrice = (float) request.getSession().getAttribute("totalPrice");
        String typeOfCargo = request.getParameter("typeOfCargo");
        Date date = Date.valueOf(request.getParameter("dateOfDelivery"));
        String address = request.getParameter("exactAddress");
        String pointOfDeparture = request.getParameter("pointOfDeparture");
        String destination = request.getParameter("destination");
        Order order = new Order(date, directionId, userId, weight, dimensions,
                totalPrice, typeOfCargo, address, pointOfDeparture, destination);
        try{
            OrderDAO.getInstance().add(order);
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e.getCause());
        }
        request.getSession().setAttribute("productOrd", null);
        request.getSession().setAttribute("weight", null);
        request.getSession().setAttribute("dimension", null);
        request.getSession().setAttribute("totalPrice", null);
        return "/index.jsp";

    }
}
