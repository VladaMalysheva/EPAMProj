package com.example.epamproj.command;

import com.example.epamproj.exceptions.AlertException;
import com.example.epamproj.exceptions.AppException;
import com.example.epamproj.exceptions.DBException;
import com.example.epamproj.dao.DirectionDAO;
import com.example.epamproj.dao.entities.Direction;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.AppenderLoggingException;

import java.sql.SQLException;
import java.util.List;

public class GetProducts implements Command {

    private static Logger log = LogManager.getLogger(GetProducts.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException, AlertException {
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

        if(request.getParameter("pageId")!=null){
            request.getSession().setAttribute("pageId", request.getParameter("pageId"));
            log.info("Attribute \"pageId\" set to session => " + request.getParameter("pageId"));
        } else {
            request.getSession().setAttribute("pageId", "1");
        }

        String filter = (String)request.getSession().getAttribute("Filter");
        String sort = (String)request.getSession().getAttribute("Sort");
        int pageId = Integer.parseInt((String)request.getSession().getAttribute("pageId"));

        try {
            if (sort == null || sort.equals("NoneSort")) {
                directions = DirectionDAO.getInstance().getAll();
            }else {
                directions = DirectionDAO.getInstance().getAllOrderBy(sort);
            }
        } catch (SQLException e) {
            log.error("Failed to get all directions");
            throw new DBException("Failed to get all directions", e);
        }

        if (filter!= null && !filter.equals("None")) {
            directions.removeIf(d -> !d.getPlace1().equals(filter) && !d.getPlace2().equals(filter));
        }

        int recordsPerPage = 6;
        int noOfPages = (int)Math.ceil(directions.size() * 1.0
                / recordsPerPage);
        int start = (pageId-1)*recordsPerPage;
        int end = recordsPerPage*pageId;
        if (end> directions.size()){
            end = directions.size();
        }
        directions = directions.subList(start, end);

        request.setAttribute("products", directions);
        request.setAttribute("noOfPages", noOfPages);
        log.info("Attribute \"products\" set");
        return "main.jsp";
    }
}
