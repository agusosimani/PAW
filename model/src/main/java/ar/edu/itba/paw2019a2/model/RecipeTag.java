package ar.edu.itba.paw2019a2.model;

public class RecipeTag {
    private int recipeId;
    private String tag;

    public RecipeTag(String tag, int recipeId) {
        this.recipeId = recipeId;
        this.tag = tag;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setId(int id) {
        this.recipeId = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
