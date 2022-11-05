package com.example.epamproj.dao.entities;

import java.io.Serializable;
import java.sql.Date;

public class Report implements Serializable {
    private int reportId;
    private int invoiceId;
    private Invoice invoice;
    private Date dateOfPaying;

    public Report() {}

    public Report(int reportId, int invoiceId, Invoice invoice, Date dateOfPaying) {
        this.reportId = reportId;
        this.invoiceId = invoiceId;
        this.invoice = invoice;
        this.dateOfPaying = dateOfPaying;
    }

    public Report(int invoiceId, Date dateOfPaying) {
        this.invoiceId = invoiceId;
        this.dateOfPaying = dateOfPaying;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Date getDateOfPaying() {
        return dateOfPaying;
    }

    public void setDateOfPaying(Date dateOfPaying) {
        this.dateOfPaying = dateOfPaying;
    }


}
