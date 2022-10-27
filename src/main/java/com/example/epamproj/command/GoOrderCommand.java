package com.example.epamproj.command;

import com.example.epamproj.dao.DBException;
import com.example.epamproj.dao.DirectionDAO;
import com.example.epamproj.dao.entities.Direction;
import com.example.epamproj.dao.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class GoOrderCommand implements Command {

    private static Logger log = LogManager.getLogger(GoOrderCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        if (request.getSession().getAttribute("user")==null){
            return "/login.jsp";
        }else if(((User)request.getSession().getAttribute("user")).getRole().equals("admin")){
            return "/index.jsp";
        }else {
            if (request.getSession().getAttribute("productCalc") != null) {
                request.getSession().setAttribute("productOrd", (Direction) request.getSession().getAttribute("productCalc"));
            }
            int id;
            if (request.getParameter("productId") != null) {
                id = Integer.parseInt(request.getParameter("productId"));
                log.info("id =>" + id);
                Direction direction = null;

                try {
                    direction = DirectionDAO.getInstance().getById(id);
                    log.info("direction => " + direction);

                } catch (SQLException e) {
                    throw new DBException(e.getMessage(), e.getCause());
                }
                request.getSession().setAttribute("productOrd", direction);
            }

            return "/order.jsp";
        }
    }
}
