package com.example.epamproj.command;

import com.example.epamproj.dao.DBException;
import com.example.epamproj.dao.DirectionDAO;
import com.example.epamproj.dao.entities.Direction;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class GetProducts implements Command {

    private static Logger log = LogManager.getLogger(GetProducts.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        List<Direction> directions = null;
        log.info("Attribute \"Sort\" => " + request.getParameter("Sort"));
        try {
            if(request.getParameter("Sort") == null || request.getParameter("Sort").equals("NoneSort")){
                directions = DirectionDAO.getInstance().getAll();
            }else {
                directions = DirectionDAO.getInstance().getAllOrderBy((String) request.getParameter("Sort"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("products", directions);
        return "main.jsp";
    }
}
