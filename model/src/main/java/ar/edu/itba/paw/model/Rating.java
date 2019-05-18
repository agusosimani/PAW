package ar.edu.itba.paw.model;

import java.sql.Date;

public class Rating {

    private int userId;

    private int recipeId;

    private float rating;

    //cambialo a timestamp TODO
    private Date date;

    private String status;


    /*protected*/ Rating() {
        //Para hibernate futuro.
    }

    public Rating(int userId,int recipeId, float rating, Date date, String status) {
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
