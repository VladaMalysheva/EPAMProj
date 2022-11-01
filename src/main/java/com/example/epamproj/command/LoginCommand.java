package com.example.epamproj.command;

import com.example.epamproj.dao.DBException;
import com.example.epamproj.dao.UserDAO;
import com.example.epamproj.dao.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class LoginCommand implements Command{

    private static Logger log = LogManager.getLogger(LoginCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = null;
        try {
            user = UserDAO.getInstance().getByLogin(login);
            log.info("execute(): user with login " + login + " was found successfully");
        } catch (SQLException e) {
            log.error("execute(): user with login " + login + " wasn't found");
            throw new DBException(e.getMessage(), e.getCause());
        }
        request.getSession().setAttribute("user", user);
        if(password.equals(user.getPassword()) && "admin".equals(user.getRole())){
            log.info("execute(): user with login " + login + " logged in successfully and his role is admin");
            return "/index.jsp";
        } else if (password.equals(user.getPassword()) && "client".equals(user.getRole())) {
            log.info("execute(): user with login " + login + " logged in successfully and his role is client");
            return "/index.jsp";
        }else{
            log.error("execute(): invalid password");
            return "/error.jsp";
        }
    }
}
