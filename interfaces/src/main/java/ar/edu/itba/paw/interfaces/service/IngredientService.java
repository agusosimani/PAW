package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.model.Enum.Warnings;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IngredientService {

    List<Ingredient> getAllIngredients();

    Optional<Ingredient> getById(final int id);

    Optional<Ingredient> findByName(final String name);

    List<RecipeIngredient> findByUser(int userId);

    List<RecipeIngredient> findByRecipe(int recipeId);

    Either<RecipeIngredient,Warnings> findUserIngredientByName(int u, String name);

    Either<RecipeIngredient,Warnings> findRecipeIngredientByName(int recipeId, String name);

    RecipeIngredient addNewRecipeIngredient(RecipeIngredient ri, int recipe);

    RecipeIngredient addNewUserIngredient(RecipeIngredient ui, int userId);

    void addNewUserIngredient(List<RecipeIngredient> recipeIngredientList, int user);

    Ingredient addNewIngredient(Ingredient i);

    void updateI(Ingredient ingredient);

    void updateRI(RecipeIngredient ingredient, int recipeId);

    void updateUI(RecipeIngredient ri, int userId);

    void deleteRI(int ri, int recipeId);

    void deleteUI(int ri, int userId);

    void deleteI(Ingredient i);


    Warnings cookRecipe(List<RecipeIngredient> ri, int currentUserID);
}
