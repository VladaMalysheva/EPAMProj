package com.example.epamproj.controller;

import com.example.epamproj.exceptions.AlertException;
import com.example.epamproj.exceptions.AppException;
import com.example.epamproj.exceptions.DBException;
import com.example.epamproj.command.Command;
import com.example.epamproj.command.CommandContainer;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static Logger log = LogManager.getLogger(Controller.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("Parameters: " + request.getParameterMap().keySet().stream()
                .map(key -> key + "=" + Arrays.toString(request.getParameterMap().get(key)))
                .collect(Collectors.joining(", ", "{", "}")));


        String address = "error_page.jsp";
        String commandName = request.getParameter("command");
        Command command = CommandContainer.getCommand(commandName);
        try {
            address = command.execute(request, response);
        } catch (AlertException e) {
            request.getSession().setAttribute("Alert", e);
            log.error("Failed to execute command \"" + commandName + "\"");
            log.info("Created an alert");
            String addressAlert = e.getAddress();
            request.getRequestDispatcher(addressAlert).forward(request, response);
            log.info("forwarded to " + addressAlert);
        } catch (AppException ex){
            log.error("Failed to execute command \"" + commandName + "\"");
            log.info("Redirected to the error page");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            log.info("forwarded to \"/error.jsp\"");
        }
        request.getRequestDispatcher(address).forward(request, response);
        log.info("forwarded to " + address);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Predicate<Object> isQualified = item -> !(item.equals("name") || item.equals("surname")
                || item.equals("patronymic") || item.equals("password"));

        log.info("Parameters: " + request.getParameterMap().keySet().stream()
                .filter(isQualified)
                .map(key -> key + "=" + Arrays.toString(request.getParameterMap().get(key)))
                .collect(Collectors.joining(", ", "{", "}")));


//        try {
//            request.setAttribute("users", UserDAO.getInstance().getAll());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        String address = "error_page.jsp";
        String commandName = request.getParameter("command");
        Command command = CommandContainer.getCommand(commandName);

        try {
            address = command.execute(request, response);
//            log.info("address => " + address);
        } catch (AlertException e) {
            request.getSession().setAttribute("Alert", e);
            log.error("Failed to execute command \"" + commandName + "\"");
            log.info("Created an alert");
            address = e.getAddress();
        } catch (AppException ex){
            log.error("Failed to execute command \"" + commandName + "\"");
            log.info("Redirected to the error page");
            address = "/error.jsp";
        }
        response.sendRedirect(address);
        log.info("redirected to " + address);
    }
}
