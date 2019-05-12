package ar.edu.itba.paw.model;

import java.sql.Date;
import java.util.Map;

public class Rating {

    User user;

    int rating;

    Date date;


    /*protected*/ Rating() {
        //Para hibernate futuro.
    }

    public Rating(User user, int rating, Date date) {
        this.user = user;
        this.rating = rating;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
