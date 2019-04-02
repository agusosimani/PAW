package ar.edu.itba.paw.model;

public class Ingredient {

    private int id;
    private String name;
    private boolean isVegetarian;
    private boolean isVegan;
    private boolean taccFree;
    private double calories;
    private double protein;
    private double carbohydrates;
    private double totalFat;
    private double sugar;
    private double serving;
    private String typeOfServing;
    private String userId;

    private Ingredient(int id, String name, boolean isVegetarian, boolean isVegan, boolean taccFree,
                      double calories, double protein, double carbohydrates, double totalFat,
                      double sugar, double serving, String typeOfServing, String userId) {
        this.id = id;
        this.name = name;
        this.isVegetarian = isVegetarian;
        this.isVegan = isVegan;
        this.taccFree = taccFree;
        this.calories = calories;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.totalFat = totalFat;
        this.sugar = sugar;
        this.serving = serving;
        this.typeOfServing = typeOfServing;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public boolean isTaccFree() {
        return taccFree;
    }

    public void setTaccFree(boolean celiac) {
        taccFree = celiac;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(double totalFat) {
        this.totalFat = totalFat;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public double getServing() {
        return serving;
    }

    public void setServing(double serving) {
        this.serving = serving;
    }

    public String getTypeOfServing() {
        return typeOfServing;
    }

    public void setTypeOfServing(String typeOfServing) {
        this.typeOfServing = typeOfServing;
    }

    public static class IngredientBuilder {
        //TODO ARMARLO
    }
}
