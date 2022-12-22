package com.example.epamproj.command;

import com.example.epamproj.exceptions.AlertException;
import com.example.epamproj.exceptions.AppException;
import com.example.epamproj.exceptions.DBException;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException, AlertException {
        request.getSession().removeAttribute("weight");
        request.getSession().removeAttribute("dimension");
        request.getSession().removeAttribute("totalPrice");
        Direction direction = (Direction) request.getSession().getAttribute("productCalc");
        log.info("Direction => " + direction.getName());
        float weight = Float.parseFloat(request.getParameter("Weight"));     //TODO Add parameters check
        float dimension = Float.parseFloat(request.getParameter("Dimension"));
        if(!((weight>Float.MIN_VALUE&&weight<Float.MAX_VALUE) && (dimension>Float.MIN_VALUE&&dimension<Float.MAX_VALUE))){
            log.error("Numbers are too big or too small");
            throw new AlertException("Numbers are too big or too small", "/calculate.jsp");
        }


        Tariff tariffWeight = null;
        Tariff tariffDistance = null;
        Tariff tariffDimension = null;
        try {
            tariffWeight = TariffDAO.getInstance().getByName("weight");
            tariffDimension = TariffDAO.getInstance().getByName("dimension");
            tariffDistance = TariffDAO.getInstance().getByName("distance");
        } catch (SQLException e) {
            log.error("failed to get tariffs from DAO");
            throw new DBException("Failed to get tariffs from DAO", e);
        }
        float totalPrice = (float) ((tariffDimension.getValue()*dimension)
                +(tariffWeight.getValue()*weight)+(tariffDistance.getValue()*direction.getDistance()));

        request.getSession().setAttribute("weight", weight);
        request.getSession().setAttribute("dimension", dimension);
        request.getSession().setAttribute("totalPrice", totalPrice);

        log.info("Attribute \"weight\" set to session => " + weight);
        log.info("Attribute \"dimension\" set to session => " + dimension);
        log.info("Attribute \"totalPrice\" set to session => " + totalPrice);


        return "/calculate.jsp";
    }
}
