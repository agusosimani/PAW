package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.Ingredient;
import ar.edu.itba.paw.model.Recipe;
import ar.edu.itba.paw.model.RecipeIngredient;
import ar.edu.itba.paw.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IngredientService {

    Optional<List<Ingredient>> getAllIngredients();

    Optional<Ingredient> getById(final int id);

    Optional<Ingredient> findByName(final String name);

    Optional<List<RecipeIngredient>> findByUser(int userId);

    Optional<List<RecipeIngredient>> findByRecipe(Recipe recipe);

    RecipeIngredient findUserIngredientByName(int u, String name);

    RecipeIngredient findRecipeIngredientByName(Recipe recipe, String name);

    RecipeIngredient addNewRecipeIngredient(RecipeIngredient ri, Recipe recipe);

    RecipeIngredient addNewUserIngredient(RecipeIngredient ui, User user);

    Ingredient addNewIngredient(Ingredient i);

    //TODO:cambiar grasada

    void updateI(Ingredient ingredient, String change, Object value);

    void updateI(Ingredient ingredient, Map<String, Object> map);

    void updateRI(RecipeIngredient ingredient, Recipe recipe, String change, Object value);

    void updateRI(RecipeIngredient ingredient, Recipe recipe);

    void updateRI(RecipeIngredient ingredient, Recipe recipe, Map<String, Object> map);

    void updateUI(RecipeIngredient ri, User user);

    void deleteRI(RecipeIngredient ri, Recipe recipe);

    void deleteUI(RecipeIngredient ri, User user);

    void deleteI(Ingredient i);


}
