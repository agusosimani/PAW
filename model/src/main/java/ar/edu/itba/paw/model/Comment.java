package ar.edu.itba.paw.model;

import java.util.Objects;

public class Comment {

    private int id;
    private int userId;
    private int recipeId;
    private String message;

    public Comment(int id, int userId, int recipeId, String message) {
        this.id = id;
        this.userId = userId;
        this.recipeId = recipeId;
        this.message = message;
    }

    public Comment(int userId, int recipeId, String message) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id &&
                userId == comment.userId &&
                recipeId == comment.recipeId &&
                Objects.equals(message, comment.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, recipeId, message);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", recipeId=" + recipeId +
                ", message='" + message + '\'' +
                '}';
    }
}
