package ar.edu.itba.paw.model;

import java.util.Objects;

public class RecipeIngredient {

    private Ingredient ingredient;
    private int amount;
    private String observation;

    private RecipeIngredient(Ingredient ingredient, int amount, String observation) {
        this.ingredient = ingredient;
        this.amount = amount;
        this.observation = observation;
    }


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

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredient that = (RecipeIngredient) o;
        return Objects.equals(ingredient, that.ingredient);
    }

    public static class Builder {

//        private int id;
        private Ingredient ingredient;
        private int amount;
        private String observation = "";

        public Builder( Ingredient ingredient,int amount) {
//            this.id = id;
            this.ingredient = ingredient;
            this.amount = amount;
        }

        public Builder observation(String observation) {
            this.observation = observation;
            return this;
        }

        public RecipeIngredient build() {
            return new RecipeIngredient(ingredient, amount, observation);
        }
    }

}
