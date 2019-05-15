
package ar.edu.itba.paw.webapp.form;

import javax.validation.constraints.Size;

public class AddIngredientForm {

    private int ingredientId;

    private int amount;

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
