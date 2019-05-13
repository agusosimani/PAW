package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.Recipe;
import ar.edu.itba.paw.model.RecipeTag;
import ar.edu.itba.paw.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RecipeService {

    Optional<Recipe> getById(final int id);

    Optional<Recipe> getByIdWithIngredients(final int id);

    Optional<Recipe> findByName(final String name);

    Optional<List<Recipe>> findByUser(User user);

    Recipe findUserRecipeByName(User u, String name);

    Recipe addNewRecipe(Recipe recipe);

    void update(Recipe recipe);

    void deleteRecipe(Recipe recipe);

    Optional<List<RecipeTag>> getAllRecipeTags(Recipe recipe);

    void removeTagFromRecipe(Recipe recipe, RecipeTag tag);

    void addNewRecipeTag(Recipe recipe, RecipeTag tag);

    void addNewRating(User user, Recipe recipe, int rating);

    void updateRating(User user, Recipe recipe, int rating);

    void deleteRating(User user, Recipe recipe, int rating);
}
