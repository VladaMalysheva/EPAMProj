package com.example.epamproj.controller;

import com.example.epamproj.dao.UserDAO;
import com.example.epamproj.dao.entities.Direction;
import com.example.epamproj.unused.Main;
import com.example.epamproj.unused.Repo;
import com.example.epamproj.command.Command;
import com.example.epamproj.command.CommandContainer;
import com.example.epamproj.dao.entities.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static Logger log = LogManager.getLogger(Controller.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        PrintWriter out = response.getWriter();
        log.info("Parameters: " + request.getParameterNames());


        String address = "error_page.jsp";
        String commandName = request.getParameter("command");
        Command command = CommandContainer.getCommand(commandName);
        try{
            address = command.execute(request, response);
        }catch (Exception e){
            request.setAttribute("e", e);
        }
        request.getRequestDispatcher(address).forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Parameter \"command\": " + request.getParameter("command"));
        log.info("Parameter \"productId\": " + request.getParameter("productId"));
        log.info("Parameter \"product\": " + request.getParameter("product"));


        try {
            request.setAttribute("users", UserDAO.getInstance().getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        String address = "error_page.jsp";
        String commandName = request.getParameter("command");
        log.info("doPost(): commandName => " + commandName);
        Command command = CommandContainer.getCommand(commandName);

        try{
            address = command.execute(request, response);
            log.info("doPost(): address => " + address);
        }catch (Exception e){
            request.getSession().setAttribute("e", e);
            log.error("doPost(): Failed to execute command \"" + commandName + "\"");
        }
//        response.sendRedirect(address);
        log.info("doPost(): forwarded to " + address);
        response.sendRedirect(address);
//        request.getRequestDispatcher(address).forward(request, response);
    }
}
