package ar.edu.itba.paw.model;

import java.util.List;
import java.util.Map;

public class Recipe {

    private int id;
    private String name;
    private String description;
    private List<RecipeIngredient> ingredients;
    private String instructions;
    private int userId;
    private int status;
    private float yourRating;
    private float globalRating;
    //private List<Comment> comments;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getYourRating() {
        return yourRating;
    }

    public void setYourRating(float yourRating) {
        this.yourRating = yourRating;
    }

    public float getGlobalRating() {
        return globalRating;
    }

    public void setGlobalRating(float globalRating) {
        this.globalRating = globalRating;
    }


}
