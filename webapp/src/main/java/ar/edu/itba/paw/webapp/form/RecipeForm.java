package ar.edu.itba.paw.webapp.form;

import ar.edu.itba.paw.model.RecipeTag;

import javax.validation.constraints.Size;
import java.awt.*;
import java.util.List;

public class RecipeForm {

    @Size(min = 4, max = 100)
    private String name;
    @Size(max = 100)
    private String description;
    @Size(min = 12, max = 100)
    private String instructions;

    private List<String> tags;


    private int ingredientOneAmount;
    private int ingredientOne;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getIngredientOne() {
        return ingredientOne;
    }

    public void setIngredientOne(int ingredientOne) {
        this.ingredientOne = ingredientOne;
    }

    public int getIngredientOneAmount() {
        return ingredientOneAmount;
    }

    public void setIngredientOneAmount(int ingredientOneAmount) {
        this.ingredientOneAmount = ingredientOneAmount;
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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
