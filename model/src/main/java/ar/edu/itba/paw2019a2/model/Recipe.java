package ar.edu.itba.paw2019a2.model;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import org.apache.commons.text.StringEscapeUtils;

public class Recipe {


    private int id;
    private String name;
    private String description;
    private List<RecipeIngredient> ingredients;
    private String instructions;
    private int userId;
    private float rating;

    private byte[] image;

    private List<String> tags;

    private List<Comment> comments;

    private Recipe(int id, String name, String description, List<RecipeIngredient> ingredients,
                  String instructions, int userId,
                   List<String> tags, byte[] image, List<Comment> comments,float rating) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.userId = userId;
        this.tags = tags;
        this.image = image;
        this.comments = comments;
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getEscapedInstructions() {
        return StringEscapeUtils.escapeXml10(this.instructions).replace("\n", "<br/>");
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getEncodedImage() {
        if(image == null)
            return "";
        return Base64.getEncoder().encodeToString(image);
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return id == recipe.id;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class Builder{
        private int id;
        private String name;
        private String description = "";
        private List<RecipeIngredient> ingredients;
        private String instructions;
        private int userId;
        private List<String> tags = new ArrayList<>();
        private byte[] image;
        private float rating = 0;

        private List<Comment> comments = new ArrayList<>();

        public Builder(int id, String name, List<RecipeIngredient> ingredients, String instructions,
                       int userId) {

            this.id = id;
            this.name = name;
            this.ingredients = ingredients;
            this.instructions = instructions;
            this.userId = userId;

        }

        public Builder(String name, List<RecipeIngredient> ingredients, String instructions,
                       int userId) {
            this.name = name;
            this.ingredients = ingredients;
            this.instructions = instructions;
            this.userId = userId;

        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder tags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public Builder image(byte[] image) {
            this.image = image;
            return this;
        }

        public Builder comments(List<Comment>  comments) {
            this.comments = comments;
            return this;
        }

        public Builder rating(float  rating) {
            this.rating = rating;
            return this;
        }


        public Recipe build() {
            return new Recipe(id,name, description, ingredients, instructions,
                    userId, tags, image, comments,rating);
        }
    }
}
