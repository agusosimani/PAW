package ar.edu.itba.paw.model;

import javafx.util.Builder;

import java.util.List;
import java.util.Objects;

/**
 * Hello world!
 *
 */
public class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String username;
    private boolean gender;
    private int status;
    private List<RecipeIngredient> ingredients;
    private List<Recipe> recipes;

    private User(int id, String email, String password, String name, String surname, String username, boolean gender, int status, List<RecipeIngredient> ingredients, List<Recipe> recipes) {
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
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                gender == user.gender &&
                status == user.status &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(username, user.username) &&
                Objects.equals(ingredients, user.ingredients) &&
                Objects.equals(recipes, user.recipes);
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
        private boolean gender = false;
        private int status = 0;
        private List<RecipeIngredient> ingredients = null;
        private List<Recipe> recipes = null;

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

        public Builder gender(boolean gender) {
            this.gender = gender;
            return this;
        }

        public Builder status(int status) {
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

        public User build() {
            return new User(id, email, password, name, surname, username, gender, status, ingredients, recipes);
        }
    }

}
