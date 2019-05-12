package ar.edu.itba.paw.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



//TODO: Hacer los constructores package private para usar hibernate
//TODO: agregar las dependencias de hibernate
//TODO: @Entity en los models con @Table(name = recipes)
public class Recipe {

    //TODO: @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipes_generator")
    // @SequenceGenerator(name = "recipes_generator", sequenceName = "recipes_generator", allocationSize = 1)
    // @Column()
    private int id;
    private String name;
    private String description;
    private List<RecipeIngredient> ingredients;
    private String instructions;
    private int userId;
    private int status;
    private List<Rating> rating;
    private List<String> tags;
    //private List<Comment> comments;

    /* package */ Recipe() {
        //hibernate
    }

<<<<<<< HEAD

    private Recipe(int id, String name, String description, List<RecipeIngredient> ingredients,
                  String instructions, int userId, int status,
                   List<String> tags, List<Rating> rating) {
=======
    //TODO: TIENE QUE SER PRIVATE
    public Recipe(int id, String name, String description, List<RecipeIngredient> ingredients,
                  String instructions, int userId, int status, float yourRating, float globalRating) {
>>>>>>> DB
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.userId = userId;
        this.status = status;
        this.tags = tags;
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

    public List<Rating> getRating() {
        return rating;
    }

    public void setRating(List<Rating> rating) {
        this.rating = rating;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public static class Builder{
        private int id;
        private String name;
        private String description = "";
        private List<RecipeIngredient> ingredients;
        private String instructions;
        private int userId;
        private int status;
        private List<String> tags;
        private List<Rating> rating;
        //private List<Comment> comments;

        public Builder(int id, String name, List<RecipeIngredient> ingredients, String instructions,
                       int userId, int status) {

            this.id = id;
            this.name = name;
            this.ingredients = ingredients;
            this.instructions = instructions;
            this.userId = userId;
            this.status = status;

        }

        public Builder(String name, List<RecipeIngredient> ingredients, String instructions,
                       int userId, int status) {
            this.name = name;
            this.ingredients = ingredients;
            this.instructions = instructions;
            this.userId = userId;
            this.status = status;

        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder tags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public Builder rating(List<Rating> rating){
            this.rating = rating;
            return this;
        }

        public Recipe build() {
            return new Recipe(id,name, description, ingredients, instructions,
                    userId, status, tags, rating);
        }
    }

}
