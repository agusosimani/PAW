package ar.edu.itba.paw2019a2.model;

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
    private int userId;
    private String status;

    public Ingredient(){

    }

    private Ingredient(int id, String name, boolean isVegetarian, boolean isVegan, boolean taccFree,
                      double calories, double protein, double carbohydrates, double totalFat,
                      double sugar, double serving, String typeOfServing, int userId,String status) {
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
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return id == that.id;
    }

    public static class Builder {
        private int id;
        private String name;
        private boolean isVegetarian = false;
        private boolean isVegan = false;
        private boolean taccFree = false;
        private double calories = -1;
        private double protein = -1;
        private double carbohydrates = -1;
        private double totalFat = -1;
        private double sugar = -1;
        private double serving;
        private String typeOfServing;
        private String status = "REGULAR";
        private int userId;

        public Builder(int id, String name, double serving, String typeOfServing, int userId, String status) {
            this.id = id;
            this.name = name;
            this.serving = serving;
            this.typeOfServing = typeOfServing;
            this.userId = userId;
            this.status = status;
        }

        public Builder(String name, double serving, String typeOfServing, int userId) {
            this.name = name;
            this.serving = serving;
            this.typeOfServing = typeOfServing;
            this.userId = userId;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder isVegetararian(boolean isVegetarian) {
            this.isVegetarian = isVegetarian;
            return this;
        }

        public Builder isVegan(boolean isVegan) {
            this.isVegan =isVegan;
            return this;
        }

        public Builder taccFree(boolean isTaccFree) {
            this.taccFree =isTaccFree;
            return this;
        }

        public Builder calories(double cals) {
            this.calories = cals;
            return this;
        }

        public Builder proteins(double protein) {
            this.protein = protein;
            return this;
        }

        public Builder carbohydrates(double carbohydrates) {
            this.carbohydrates = carbohydrates;
            return this;
        }

        public Builder totalFat(double totalFat) {
            this.totalFat = totalFat;
            return this;
        }

        public Builder sugar(double sugar) {
            this.sugar = sugar;
            return this;
        }

        public Ingredient build() {
            return new Ingredient(id,name, isVegetarian, isVegan, taccFree, calories, protein,
                    carbohydrates, totalFat, sugar, serving, typeOfServing, userId,status);
        }
    }
}
