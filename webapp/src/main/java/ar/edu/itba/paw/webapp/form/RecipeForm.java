package ar.edu.itba.paw.webapp.form;

import ar.edu.itba.paw.model.RecipeTag;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class RecipeForm {

    @Size(min = 5, max = 100)
    private String name;
    @Size(min = 10, max = 100)
    private String description;
    @Size(min = 20, max = 4000)
    private String instructions;

    private List<String> tags;

    private List<Integer> ingredients;
    private List<Integer> ingredientsAmount;
    @ImageSize
    private MultipartFile image;

    public List<String> getTags() {
        if(tags == null)
            return new ArrayList<>();
        return tags;
    }

    public List<Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Integer> getIngredientsAmount() {
        return ingredientsAmount;
    }

    public void setIngredientsAmount(List<Integer> ingredientsAmount) {
        this.ingredientsAmount = ingredientsAmount;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
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
