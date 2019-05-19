
package ar.edu.itba.paw.webapp.form;
import ar.edu.itba.paw.model.RecipeIngredient;

import java.util.List;

public class AddIngredientForm {

    private List<RecipeIngredient> ingredients;


    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

}
