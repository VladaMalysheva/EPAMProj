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
            log.warn("User is not registered");
            log.warn("Access to the order page denied");
            return "/login.jsp";
        }else if (((User) request.getSession().getAttribute("user")).getRole().equals("admin")) {
            log.warn("Admin cannot make order");
            log.warn("Access to the order page denied");
            return "/index.jsp";
        }else {
            if (request.getSession().getAttribute("totalPrice")== null) {
                log.warn("Total price isn't calculated");
                log.warn("Access to the order page denied");
                return "/calculate.jsp";
            }
            request.getSession().setAttribute("productOrd", (Direction) request.getSession().getAttribute("productCalc"));
            request.getSession().removeAttribute("productCalc");

            log.info("Attribute \"productOrd\" set to session");
            log.info("Attribute \"productCalc\" removed from session");

            return "/order.jsp";
        }
    }
}
