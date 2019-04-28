package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Ingredient;
import ar.edu.itba.paw.model.RecipeIngredient;
import ar.edu.itba.paw.model.User;

import java.util.List;
import java.util.Optional;

public interface IngredientsDao {
    Optional<RecipeIngredient> getUserIngById(final int recipeId);
    Optional<Ingredient> getById(final int id);
    Optional<List<RecipeIngredient>> getByUserId(final int id);
    Optional<List<RecipeIngredient>> getByRecipeId(final int id);
    Ingredient addNewIngredient(Ingredient ing);
    RecipeIngredient addNewRecipeIngredient(RecipeIngredient recIng);
    RecipeIngredient addNewUserIngredient(User user, RecipeIngredient recipeIngredient);
}
