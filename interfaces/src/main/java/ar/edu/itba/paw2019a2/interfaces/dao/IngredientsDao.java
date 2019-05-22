package ar.edu.itba.paw2019a2.interfaces.dao;

import ar.edu.itba.paw2019a2.model.Ingredient;
import ar.edu.itba.paw2019a2.model.RecipeIngredient;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IngredientsDao {

    Optional<RecipeIngredient> getUserIngById(final int ingredientId,final int userId);
    Optional<RecipeIngredient> getRecipeIngById(final int ingredientId,final int recipeId);

    List<Ingredient> getAllIngredients();

    Optional<RecipeIngredient> getDeletedUserIngById(int ingredientId, int userId);
    Optional<RecipeIngredient> getDeletedRecipeIngById(int ingredientId, int recipeId);

    Optional<Ingredient> getById(final int id);
    Optional<Ingredient> getByIngredientName(String name);
    List<RecipeIngredient> getByUserId(final int id);
    List<RecipeIngredient> getByRecipeId(final int id);

    Ingredient addNewIngredient(Ingredient ing);
    RecipeIngredient addNewRecipeIngredient(int rec, RecipeIngredient recIng);
    RecipeIngredient addNewUserIngredient(int userId, RecipeIngredient recipeIngredient);

    void updateIngredient(Ingredient ingredient, Map<String,Object> changes);
    void updateRecipeIngredient(int ingredient, Map<String, Object> changes, int recipeId);
    void updateUserIngredient(int ingredient, Map<String, Object> changes, int userId);

    void deleteRI(int id);
    boolean isRecipeIngredientDeleted(int id);

}
