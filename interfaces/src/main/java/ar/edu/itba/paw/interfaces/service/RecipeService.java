package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.model.Enum.Warnings;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RecipeService {

    Optional<Recipe> getById(final int id);

    List<Recipe> getRecipes();

    Optional<Recipe> getByIdWithIngredients(final int id);

    Optional<Recipe> findByName(final String name);

    List<Recipe> findByUser(int userId);

    int userRecipesNumber(int userId);

    Recipe findUserRecipeByName(int userId, String name);

    Recipe addNewRecipe(Recipe recipe);

    void update(Recipe recipe);

    void deleteRecipe(int recipeId);

    List<RecipeTag> getAllRecipeTags(Recipe recipe);

    void removeTagFromRecipe(Recipe recipe, RecipeTag tag);

    void addNewRecipeTag(RecipeTag tag);

    void addNewRating(int user, int recipe, float rating);

    void updateRating(int user, int recipe, float rating);

    void deleteRating(int user, int recipe);

    List<Recipe> getAllRecipesByUserId(int userId);

    List<RecipeTag> getAllTags();

    List<Recipe> FilterRecipesByTags(List<String> tags);

    List<Recipe> getAllRecipesByDate();

    List<Recipe> getAllRecipesByRating();

    Optional<Float> getUserRating(int userId, int recipeId);

    Either<Comment, Warnings> addComment(Comment comment);

    List<Comment> getRecipeComments(int recipeId);

    void addNewCookList(int userId, RecipeList recipeList);

    void addRecipeToCookList(int listId, int recipeId);

    Either<List<Recipe>,Warnings> getRecipesFromCookList(int listId);

    Either<List<RecipeList>, Warnings> getUserCookLists(int userId);

    Warnings deleteRecipeFromCookList(int listId, int recipeId, int userId);

    Warnings deleteCookList(int listId, int userId);

    Warnings changeCookListName(int listId, String name, int userId);
}
