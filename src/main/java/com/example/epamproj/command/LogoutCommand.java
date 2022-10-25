package com.example.epamproj.command;

import com.example.epamproj.dao.DBException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;

public class LogoutCommand implements Command {

    private static Logger log = LogManager.getLogger(LogoutCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        {
            log.info("logout");
            if (request.getSession().getAttribute("user") != null) {
                request.getSession().removeAttribute("user");
                return "/login.jsp";
            } else {
                return "/index.jsp";
            }
        }

    }
}
