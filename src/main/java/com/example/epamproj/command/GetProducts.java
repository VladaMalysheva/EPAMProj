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
        if (request.getParameter("Sort")!=null) {
            request.getSession().setAttribute("Sort", request.getParameter("Sort"));
            log.info("Attribute \"Sort\" set to session => " + request.getParameter("Sort"));
        }
        if (request.getParameter("Filter")!=null) {
            request.getSession().setAttribute("Filter", request.getParameter("Filter"));
            log.info("Attribute \"Filter\" set to session => " + request.getParameter("Filter"));
        }
        String filter = (String)request.getSession().getAttribute("Filter");
        String sort = (String)request.getSession().getAttribute("Sort");
        try {
            if (sort == null || sort.equals("NoneSort")) {
                directions = DirectionDAO.getInstance().getAll();
            }else {
                directions = DirectionDAO.getInstance().getAllOrderBy(sort);
            }

        } catch (SQLException e) {
            log.error("Failed to get all directions");
            throw new DBException(e.getMessage(), e.getCause());
        }

        if (filter!= null && !filter.equals("None")) {
            directions.removeIf(d -> !d.getPlace1().equals(filter) && !d.getPlace2().equals(filter));
        }

        request.setAttribute("products", directions);
        log.info("Attribute \"products\" set");
        return "main.jsp";
    }
}
