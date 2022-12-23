package com.example.epamproj.command;

import com.example.epamproj.exceptions.AlertException;
import com.example.epamproj.exceptions.AppException;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException, AlertException {
        List<Report> reports = null;
        //TODO create date validation

        if(request.getParameter("pageId")!=null){
            request.getSession().setAttribute("pageId", request.getParameter("pageId"));
            log.info("Attribute \"pageId\" set to session => " + request.getParameter("pageId"));
        } else {
            request.getSession().setAttribute("pageId", "1");
        }

        int pageId = Integer.parseInt((String)request.getSession().getAttribute("pageId"));
        int recordsPerPage = 10;

        try {
            reports = ReportDAO.getInstance().getAll();
        } catch (SQLException e) {
            log.error("Failed to get reports from db");
            throw new DBException("Failed to get reports from db", e);
        }
        try {
            if (request.getParameter("reportsFilter") != null && !Objects.equals(request.getParameter("reportsFilter"), "None")) {
                reports.removeIf(r -> !Objects.equals(r.getInvoice().getOrder().getDestination(), request.getParameter("reportsFilter")));
            } else if (request.getParameter("date") != null) {
                reports.removeIf(r -> !Objects.equals(String.valueOf(r.getInvoice().getOrder().getDate()), request.getParameter("date")));
            }

            int noOfPages = (int)Math.ceil(reports.size() * 1.0
                    / recordsPerPage);
            int start = (pageId-1)*recordsPerPage;
            int end = recordsPerPage*pageId;
            if (end> reports.size()){
                end = reports.size();
            }
            reports = reports.subList(start, end);
            request.setAttribute("noOfPages", noOfPages);

            request.setAttribute("reports", reports);

            log.info("Attribute \"reports\" set");

        } catch (Exception e) {
            log.error("Failed to filter reports");
            throw new AppException("Failed to filter reports", e);
        }

        return "/reports.jsp";
    }
}
