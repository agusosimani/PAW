package ar.edu.itba.paw.model;

public class RecipeIngredient {

    private int id;
    private Ingredient ingredient;
    private int amount;
    private String observation;

    private RecipeIngredient(int id, Ingredient ingredient, int amount, String observation) {
        this.id = id;
        this.ingredient = ingredient;
        this.amount = amount;
        this.observation = observation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
