package ar.edu.itba.paw.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
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

    private byte[] image;

    private List<RecipeTag> tags;

    //private List<Comment> comments;

    /* package */ Recipe() {
        //hibernate
    }

    private Recipe(int id, String name, String description, List<RecipeIngredient> ingredients,
                  String instructions, int userId,
                   List<RecipeTag> tags, byte[] image) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.userId = userId;
        this.tags = tags;
        this.image = image;
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

    public List<RecipeTag> getTags() {
        return tags;
    }

    public void setTags(List<RecipeTag> tags) {
        this.tags = tags;
    }

    public String getEncodedImage() {
        return Base64.getEncoder().encodeToString(image);
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public static class Builder{
        private int id;
        private String name;
        private String description = "";
        private List<RecipeIngredient> ingredients;
        private String instructions;
        private int userId;
        private List<RecipeTag> tags;
        private byte[] image;

        //private List<Comment> comments;

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

        public Builder tags(List<RecipeTag> tags) {
            this.tags = tags;
            return this;
        }

        public Builder image(byte[] image) {
            this.image = image;
            return this;
        }


        public Recipe build() {
            return new Recipe(id,name, description, ingredients, instructions,
                    userId, tags, image);
        }
    }
}
