package ar.edu.itba.paw.model;

import java.sql.Date;
import java.util.Objects;

public class Comment {

    private int id;
    private int userId;
    private int recipeId;
    private String message;
    private Date date;

    public Comment(int id, int userId, int recipeId, String message,Date date) {

        this.id = id;
        this.userId = userId;
        this.recipeId = recipeId;
        this.message = message;
        this.date = date;
    }

    public Comment(int userId, int recipeId, String message) {
        this.id = -1;
        this.userId = userId;
        this.recipeId = recipeId;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id &&
                userId == comment.userId &&
                recipeId == comment.recipeId &&
                Objects.equals(message, comment.message) &&
                Objects.equals(date, comment.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, recipeId, message, date);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", recipeId=" + recipeId +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
