package com.example.epamproj.command;
import com.example.epamproj.exceptions.AlertException;
import com.example.epamproj.exceptions.DBException;
import com.example.epamproj.dao.UserDAO;
import com.example.epamproj.dao.entities.User;
import com.example.epamproj.unused.UserController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class RegisterCommand implements Command{

    private static final Logger log = LogManager.getLogger(RegisterCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException, AlertException {
        String name = request.getParameter("name");                  //TODO Add parameters check
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        String phone = request.getParameter("phone");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            if(UserDAO.getInstance().getByLogin(login) != null){        //TODO add more validation
                log.error("login already exists");
                throw new AlertException("login already exists", "/register.jsp");
            }
        } catch (SQLException e) {
            log.error("Failed to get user by login from db");
            throw new DBException(e.getMessage(), e);
        }
        User user = new User("client", name, surname, patronymic, phone, login, password);
        request.getSession().setAttribute("user", user);
        log.info("Attribute \"user\" set to session");
        try {
            UserDAO.getInstance().add(user);
            log.info("User \"" + login + "\" added successfully");
        } catch (SQLException e) {
            log.error("Failed to add user \"" + login + "\" to db");
            throw new DBException(e.getMessage(), e);
        }

        return "/index.jsp";
    }
}
