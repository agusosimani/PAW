package ar.edu.itba.paw2019a2.webapp.form;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DateForm
{
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private String from;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private String to;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
