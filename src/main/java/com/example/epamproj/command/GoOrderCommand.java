package com.example.epamproj.command;

import com.example.epamproj.exceptions.AlertException;
import com.example.epamproj.exceptions.AppException;
import com.example.epamproj.exceptions.DBException;
import com.example.epamproj.dao.entities.Direction;
import com.example.epamproj.dao.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoOrderCommand implements Command {

    private static Logger log = LogManager.getLogger(GoOrderCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException, AlertException {
        if (request.getSession().getAttribute("user")==null){
            log.warn("User is not registered");
            log.warn("Access to the order page denied");
            throw new AlertException("You're not signed in", "/login.jsp");
//            return "/login.jsp";
        }else if (((User) request.getSession().getAttribute("user")).getRole().equals("admin")) {
            log.warn("Admin cannot make order");
            log.warn("Access to the order page denied");
            throw new AlertException("You should be client to make an order", "/calculate.jsp");
//            return "/index.jsp";
        }else {
            if (request.getSession().getAttribute("totalPrice")== null) {
                log.warn("Total price isn't calculated");
                log.warn("Access to the order page denied");
                throw new AlertException("You should calculate the price first", "/calculate.jsp");
//                return "/calculate.jsp";
            }
            request.getSession().setAttribute("productOrd", (Direction) request.getSession().getAttribute("productCalc"));
            request.getSession().removeAttribute("productCalc");

            log.info("Attribute \"productOrd\" set to session");
            log.info("Attribute \"productCalc\" removed from session");

            return "/order.jsp";
        }
    }
}
