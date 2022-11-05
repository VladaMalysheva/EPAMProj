package com.example.epamproj.command;

import com.example.epamproj.dao.DBException;
import com.example.epamproj.dao.TariffDAO;
import com.example.epamproj.dao.entities.Direction;
import com.example.epamproj.dao.entities.Tariff;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class CalculateCommand implements Command {
    private static Logger log = LogManager.getLogger(CalculateCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        Direction direction = (Direction) request.getSession().getAttribute("productCalc");
        float weight = Float.parseFloat(request.getParameter("Weight"));
        log.info("Weight => " + weight);
        float dimension = Float.parseFloat(request.getParameter("Dimension"));
        log.info("Dimension => " + dimension);
        Tariff tariffWeight = null;
        Tariff tariffDistance = null;
        Tariff tariffDimension = null;
        try {
            tariffWeight = TariffDAO.getInstance().getByName("weight");
            tariffDimension = TariffDAO.getInstance().getByName("dimension");
            tariffDistance = TariffDAO.getInstance().getByName("distance");
        } catch (SQLException e) {
            log.error("failed to get tariffs from DAO");
            throw new DBException(e.getMessage(), e.getCause());
        }
        float totalPrice = (float) ((tariffDimension.getValue()*dimension)
                +(tariffWeight.getValue()*weight)+(tariffDistance.getValue()*direction.getDistance()));
        request.getSession().setAttribute("weight", weight);
        request.getSession().setAttribute("dimension", dimension);
        request.getSession().setAttribute("totalPrice", totalPrice);


        return "/calculate.jsp";
    }
}
