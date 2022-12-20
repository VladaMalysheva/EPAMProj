package com.example.epamproj.command;

import com.example.epamproj.exceptions.AlertException;
import com.example.epamproj.exceptions.DBException;
import com.example.epamproj.dao.ReportDAO;
import com.example.epamproj.dao.entities.Report;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class ShowReportsCommand implements Command {
    private static Logger log = LogManager.getLogger(ShowReportsCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException, AlertException {
        List<Report> reports = null;
        try {
            reports = ReportDAO.getInstance().getAll();
        } catch (SQLException e) {
            log.error("Failed to get reports from db");
            throw new DBException(e.getMessage(), e);
        }
        try {
            if (request.getParameter("reportsFilter") != null && !Objects.equals(request.getParameter("reportsFilter"), "None")) {
                reports.removeIf(r -> !Objects.equals(r.getInvoice().getOrder().getDestination(), request.getParameter("reportsFilter")));
            } else if (request.getParameter("date") != null) {
                reports.removeIf(r -> !Objects.equals(String.valueOf(r.getInvoice().getOrder().getDate()), request.getParameter("date")));
            }
            request.setAttribute("reports", reports);
            log.info("Attribute \"reports\" set");
        } catch (Exception e) {
            log.error("Failed to filter reports");
            throw new DBException(e.getMessage(), e);
        }

        return "/reports.jsp";
    }
}
