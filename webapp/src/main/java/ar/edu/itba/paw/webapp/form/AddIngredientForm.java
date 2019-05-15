
package ar.edu.itba.paw.webapp.form;

import ar.edu.itba.paw.model.Ingredient;

import javax.validation.constraints.Size;

public class AddIngredientForm {

    private Ingredient ingredient;

    private int amount;

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
