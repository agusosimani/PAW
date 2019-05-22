
package ar.edu.itba.paw2019a2.webapp.form;
import ar.edu.itba.paw2019a2.model.RecipeIngredient;

import java.util.List;

public class AddIngredientForm {

    @RecipeIngredientValidatorInterface
    private List<RecipeIngredient> ingredients;


    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

}
