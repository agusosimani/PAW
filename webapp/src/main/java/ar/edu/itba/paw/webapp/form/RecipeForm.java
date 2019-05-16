package ar.edu.itba.paw.webapp.form;

import ar.edu.itba.paw.model.RecipeTag;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeForm {

    @Size(max = 100)
    private String name;
    @Size(max = 100)
    private String description;
    @Size(max = 100)
    private String instructions;

    private List<String> tags;


    private int ingredientOneAmount;
    private int ingredientOne;

    private MultipartFile image;

    public List<String> getTags() {
        if(tags == null)
            return new ArrayList<>();
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
