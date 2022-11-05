package com.example.epamproj.command;

import com.example.epamproj.dao.DBException;
import com.example.epamproj.dao.entities.Direction;
import com.example.epamproj.dao.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoOrderCommand implements Command {

    private static Logger log = LogManager.getLogger(GoOrderCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        if (request.getSession().getAttribute("user")==null){
            return "/login.jsp";
        }else if (((User) request.getSession().getAttribute("user")).getRole().equals("admin")) {
            return "/index.jsp";
        }else {
            if (request.getSession().getAttribute("totalPrice")== null) {
                return "/calculate.jsp";
            }
            request.getSession().setAttribute("productOrd", (Direction) request.getSession().getAttribute("productCalc"));
            request.getSession().setAttribute("productCalc",null);


            return "/order.jsp";
        }
    }
}
