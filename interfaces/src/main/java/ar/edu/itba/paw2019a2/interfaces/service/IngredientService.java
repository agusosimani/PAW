package ar.edu.itba.paw2019a2.interfaces.service;

import ar.edu.itba.paw2019a2.model.Either;
import ar.edu.itba.paw2019a2.model.Enum.Warnings;
import ar.edu.itba.paw2019a2.model.Ingredient;
import ar.edu.itba.paw2019a2.model.RecipeIngredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {

    List<Ingredient> getAllIngredients();

    Optional<Ingredient> getById(final int id);

    List<RecipeIngredient> findByUser(int userId);

    Optional<List<RecipeIngredient>> findByRecipe(int recipeId);

    Optional<RecipeIngredient> getByIngredientUserId(int ingredientId, int userId);

    RecipeIngredient addNewRecipeIngredient(RecipeIngredient ri, int recipe);

    RecipeIngredient addNewUserIngredient(RecipeIngredient ui, int userId);

    Ingredient addNewIngredient(Ingredient i);

    void updateI(Ingredient ingredient);

    void updateRI(RecipeIngredient ingredient, int recipeId);

    void updateUI(RecipeIngredient ri, int userId);

    void deleteUI(int ri, int userId);

    Warnings cookRecipe(int recipeId, List<RecipeIngredient> ri, int currentUserID);
}
