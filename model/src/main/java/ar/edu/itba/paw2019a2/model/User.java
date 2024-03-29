package ar.edu.itba.paw2019a2.model;

import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String username;
    private String gender;
    private String status;
    private List<RecipeIngredient> ingredients;
    private List<Recipe> recipes;
    private List<RecipeList> recipeLists;
    private boolean enabled;
    private boolean isAdmin;
    //private byte[] image;

    private User(int id, String email, String password, String name, String surname,
                 String username, String gender, String status,
                 List<RecipeIngredient> ingredients, List<Recipe> recipes,
                 List<RecipeList> recipeLists, boolean enabled, boolean isAdmin) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.gender = gender;
        this.status = status;
        this.ingredients = ingredients;
        this.recipes = recipes;
        this.recipeLists = recipeLists;
        this.enabled = enabled;
        this.isAdmin = isAdmin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<RecipeList> getRecipeLists() {
        return recipeLists;
    }

    public void setRecipeLists(List<RecipeList> recipeLists) {
        this.recipeLists = recipeLists;
    }

    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                gender.equals(user.gender) &&
                status.equals(user.status) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(username, user.username) &&
                Objects.equals(ingredients, user.ingredients) &&
                Objects.equals(recipes, user.recipes) &&
                Objects.equals(enabled, user.enabled) &&
                Objects.equals(isAdmin, user.isAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, surname, username, gender, status, ingredients, recipes);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", gender=" + gender +
                ", status=" + status +
                ", ingredients=" + ingredients +
                ", recipes=" + recipes +
                '}';
    }

    public static class Builder{

        private int id = 0;
        private String email;
        private String password;
        private String name = "";
        private String surname  = "";
        private String username;
        private String gender = "notSpecified";
        private String status = "REGULAR";
        private List<RecipeIngredient> ingredients = null;
        private List<Recipe> recipes = null;
        private List<RecipeList> recipeLists= null;
        private boolean enabled = false;
        private boolean isAdmin = false;

        public Builder(int id, String username, String password, String email) {
            this.id = id;
            this.email = email;
            this.password = password;
            this.username = username;
        }

        public Builder(String username, String password, String email) {
            this.email = email;
            this.password = password;
            this.username = username;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder ingredients(List<RecipeIngredient> ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public Builder recipes(List<Recipe> recipes) {
            this.recipes = recipes;
            return this;
        }

        public Builder recipeList(List<RecipeList> recipeLists) {
            this.recipeLists = recipeLists;
            return this;
        }

        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder isAdmin(boolean isAdmin) {
            this.isAdmin = isAdmin;
            return this;
        }

        public User build() {
            return new User(id, email, password, name, surname, username, gender, status, ingredients, recipes,recipeLists, enabled, isAdmin);
        }
    }
}
