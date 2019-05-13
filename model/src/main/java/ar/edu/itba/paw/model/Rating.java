package ar.edu.itba.paw.model;

import java.sql.Date;

public class Rating {

    private int userId;

    private int recipeId;

    private int rating;

    private Date date;

    private int status;


    /*protected*/ Rating() {
        //Para hibernate futuro.
    }

    public Rating(int userId,int recipeId, int rating, Date date, int status) {
        this.userId = userId;
        this.recipeId = recipeId;
        this.rating = rating;
        this.date = date;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
