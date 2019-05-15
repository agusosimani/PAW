package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.Recipe;
import ar.edu.itba.paw.model.RecipeTag;
import ar.edu.itba.paw.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RecipeService {

    Optional<Recipe> getById(final int id);

    Optional<List<Recipe>> getRecipes();

    Optional<Recipe> getByIdWithIngredients(final int id);

    Optional<Recipe> findByName(final String name);

    Optional<List<Recipe>> findByUser(int userId);

    int userRecipesNumber(int userId);

    Recipe findUserRecipeByName(int userId, String name);

    Recipe addNewRecipe(Recipe recipe);

    void update(Recipe recipe);

    void deleteRecipe(Recipe recipe);

    Optional<List<RecipeTag>> getAllRecipeTags(Recipe recipe);

    void removeTagFromRecipe(Recipe recipe, RecipeTag tag);

    void addNewRecipeTag(Recipe recipe, RecipeTag tag);

    void addNewRating(int user, int recipe, int rating);

    void updateRating(int user, int recipe, int rating);

    void deleteRating(int user, int recipe, int rating);

    Optional<List<Recipe>> getAllRecipesByUserId(int userId);

    Optional<List<RecipeTag>> getAllTags();
}
