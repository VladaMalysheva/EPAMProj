package com.example.epamproj.command;

import com.example.epamproj.exceptions.AlertException;
import com.example.epamproj.exceptions.DBException;
import com.example.epamproj.dao.UserDAO;
import com.example.epamproj.dao.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class LoginCommand implements Command{

    private static final Logger log = LogManager.getLogger(LoginCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException, AlertException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = null;
        try {
            user = UserDAO.getInstance().getByLogin(login);
            log.info("user with login " + login + " was found successfully");
        } catch (SQLException e) {
            log.error("user with login " + login + " wasn't found");
            throw new AlertException("invalid login", "/login.jsp");
        }

        request.getSession().setAttribute("user", user);

        if (password.equals(user.getPassword()) && "admin".equals(user.getRole())) {
            log.info("user with login " + login + " logged in successfully and his role is admin");
            return "/index.jsp";
        } else if (password.equals(user.getPassword()) && "client".equals(user.getRole())) {
            log.info("user with login " + login + " logged in successfully and his role is client");
            return "/index.jsp";
        } else {
            log.error("invalid password");
            request.getSession().removeAttribute("user");
            throw new AlertException("invalid password", "/login.jsp");
//            return "/error.jsp";
        }
    }
}
