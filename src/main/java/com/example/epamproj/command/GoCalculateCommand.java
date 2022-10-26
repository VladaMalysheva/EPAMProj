package com.example.epamproj.command;

import com.example.epamproj.controller.Controller;
import com.example.epamproj.dao.DBException;
import com.example.epamproj.dao.DirectionDAO;
import com.example.epamproj.dao.TariffDAO;
import com.example.epamproj.dao.entities.Direction;
import com.example.epamproj.dao.entities.Tariff;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class GoCalculateCommand implements Command {

    private static Logger log = LogManager.getLogger(GoCalculateCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException{
        int id = Integer.parseInt(request.getParameter("productId"));
        log.info("id =>" + id);
        Direction direction = null;

        try {
            direction = DirectionDAO.getInstance().getById(id);
            log.info("direction => " + direction);

        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e.getCause());
        }
        request.getSession().setAttribute("productCalc", direction);
        log.info("request.getAttribute() => "+ (Direction)request.getAttribute("productCalc"));
        request.getSession().setAttribute("totalPrice", null);

        return "/calculate.jsp";
    }
}
