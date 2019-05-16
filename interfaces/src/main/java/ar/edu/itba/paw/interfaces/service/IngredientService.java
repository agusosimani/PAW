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

    Optional<List<RecipeIngredient>> findByRecipe(int recipeId);

    RecipeIngredient findUserIngredientByName(int u, String name);

    RecipeIngredient findRecipeIngredientByName(int recipeId, String name);

    RecipeIngredient addNewRecipeIngredient(RecipeIngredient ri, int recipe);

    RecipeIngredient addNewUserIngredient(RecipeIngredient ui, int userId);

    Ingredient addNewIngredient(Ingredient i);

    //TODO:cambiar grasada

    void updateI(Ingredient ingredient, String change, Object value);

    void updateI(Ingredient ingredient, Map<String, Object> map);

    void updateRI(RecipeIngredient ingredient, int recipeId);

    void updateUI(RecipeIngredient ri, int userId);

    void deleteRI(int ri, int recipeId);

    void deleteUI(int ri, int userId);

    void deleteI(Ingredient i);


    Boolean cookRecipe(RecipeIngredient ri, int currentUserID);
}
