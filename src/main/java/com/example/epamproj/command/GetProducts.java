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
        log.info("Parameter \"Sort\" => " + request.getParameter("Sort"));
        log.info("Parameter \"Filter\" => " + request.getParameter("Filter"));
        try {
            if(request.getParameter("Sort") == null || request.getParameter("Sort").equals("NoneSort")){
                directions = DirectionDAO.getInstance().getAll();
            }else {
                directions = DirectionDAO.getInstance().getAllOrderBy((String) request.getParameter("Sort"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(request.getParameter("Filter")!= null){
            directions.removeIf(d -> !d.getPlace1().equals(request.getParameter("Filter")) && !d.getPlace2().equals(request.getParameter("Filter")));
        }

        request.setAttribute("products", directions);
        return "main.jsp";
    }
}
