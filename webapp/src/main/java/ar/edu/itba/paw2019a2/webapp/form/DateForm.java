package ar.edu.itba.paw2019a2.webapp.form;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DateForm
{
    private Date from;

    private Date to;

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
