package ar.edu.itba.paw2019a2.interfaces.service;

import ar.edu.itba.paw2019a2.model.*;
import ar.edu.itba.paw2019a2.model.Enum.Order;
import ar.edu.itba.paw2019a2.model.Enum.Warnings;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RecipeService {

    Optional<Recipe> getById(final int id);

    Optional<Recipe> getByIdWithIngredients(final int id);

    List<Recipe> findByUser(int userId);

    int userRecipesNumber(int userId);

    Either<Recipe,Warnings> findUserRecipeByName(int userId, String name);

    Either<Recipe,Warnings> addNewRecipe(Recipe recipe);

    void update(Recipe recipe);

    void deleteRecipe(int recipeId);

    void addNewRating(int user, int recipe, float rating);

    List<Recipe> getAllRecipesByUserId(int userId);

    List<Recipe> getFavouriteRecipes(int userId);

    Optional<Float> getUserRating(int userId, int recipeId);

    Either<Comment, Warnings> addComment(Comment comment);

    Warnings deleteRecipeFromCookList(int listId, int recipeId, int userId);

    List<Comment> getRecipeComments(int recipeId);

    RecipeList addNewCookListWithoutIngredients(int userId, String name);

    void addRecipeToCookList(int listId, int recipeId);

    List<RecipeList> getUserCookLists(int userId);

    Either<RecipeList,Warnings> getCookList(int cookListId);

    Warnings deleteCookList(int listId, int userId);

    Set<Recipe> getRecipesBasedOnOrderTagsCookable(List<String> tags, Order order, int userId,String search, int limit);

    Set<Recipe> getRecipesBasedOnOrderTags(List<String> tags, Order order,String search, int limit);

    List<RecipeIngredient> getIngredientsCookedRangeTime(int userId, Date from, Date to);

    Set<Recipe> getRecipesOrderCooked(int userId);
}
