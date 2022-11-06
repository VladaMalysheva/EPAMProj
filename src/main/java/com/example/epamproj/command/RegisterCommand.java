package com.example.epamproj.command;
import com.example.epamproj.dao.DBException;
import com.example.epamproj.dao.UserDAO;
import com.example.epamproj.dao.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class RegisterCommand implements Command{

    private static Logger log = LogManager.getLogger(RegisterCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        String phone = request.getParameter("phone");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User("client", name, surname, patronymic, phone, login, password);
        request.getSession().setAttribute("user", user);
        log.info("Attribute \"user\" set to session");
        try {
            UserDAO.getInstance().add(user);
            log.info("User \"" + login + "\" added successfully");
        } catch (SQLException e) {
            log.error("Failed to add user \"" + login + "\" to db");
            throw new DBException(e.getMessage(), e.getCause());
        }

        return "/index.jsp";
    }
}
