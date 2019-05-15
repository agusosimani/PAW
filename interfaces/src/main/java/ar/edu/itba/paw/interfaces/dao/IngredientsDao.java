package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Ingredient;
import ar.edu.itba.paw.model.Recipe;
import ar.edu.itba.paw.model.RecipeIngredient;
import ar.edu.itba.paw.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IngredientsDao {

    Optional<RecipeIngredient> getUserIngById(final int ingredientId,final int userId);
    Optional<List<Ingredient>> getAllIngredients();

    Optional<RecipeIngredient> getDeletedUserIngById(int ingredientId, int userId);

    Optional<Ingredient> getById(final int id);
    Optional<Ingredient> getByIngredientName(String name);
    Optional<List<RecipeIngredient>> getByUserId(final int id);
    Optional<List<RecipeIngredient>> getByRecipeId(final int id);

    Ingredient addNewIngredient(Ingredient ing);
    RecipeIngredient addNewRecipeIngredient(Recipe rec, RecipeIngredient recIng);
    RecipeIngredient addNewUserIngredient(User user, RecipeIngredient recipeIngredient);

    void updateIngredient(Ingredient ingredient, Map<String,Object> changes);
    void updateRecipeIngredient(RecipeIngredient ingredient, Map<String, Object> changes, Recipe recipe);
    void updateUserIngredient(RecipeIngredient ingredient, Map<String, Object> changes, User user);

}
