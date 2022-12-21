package com.example.epamproj.command;

import com.example.epamproj.exceptions.AlertException;
import com.example.epamproj.exceptions.DBException;
import com.example.epamproj.dao.DirectionDAO;
import com.example.epamproj.dao.entities.Direction;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class GoCalculateCommand implements Command {

    private static Logger log = LogManager.getLogger(GoCalculateCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException, AlertException {
        int id = Integer.parseInt(request.getParameter("productId"));    //TODO Add parameters check
        Direction direction = null;
        try {
            direction = DirectionDAO.getInstance().getById(id);
            log.info("direction => " + direction);

        } catch (SQLException e) {
            log.error("Failed to get direction from database");
            throw new DBException(e.getMessage(), e);
        }
        request.getSession().setAttribute("productCalc", direction);
        log.info("Attribute \"productCalc\" set");
        request.getSession().removeAttribute("totalPrice");
        log.info("Attribute \"totalPrice\" removed");

        return "/calculate.jsp";
    }
}
