package ar.edu.itba.paw2019a2.interfaces.dao;

import ar.edu.itba.paw2019a2.model.Enum.Order;
import ar.edu.itba.paw2019a2.model.Enum.Status;
import ar.edu.itba.paw2019a2.model.Recipe;
import ar.edu.itba.paw2019a2.model.RecipeList;
import ar.edu.itba.paw2019a2.model.RecipeTag;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RecipeDao {
    Optional<Recipe> getById(final int id);

    Optional<Recipe> getByName(final String name);

    List<Recipe> getByUserId(final int id);

    List<Recipe> getAllRecipes(String search, int limit);

    List<Recipe> getAllRecipesOrderedByDateNew(String search, int limit);

    List<Recipe> getAllRecipesOrderedByDateOld(String search, int limit);

    List<Recipe> getRecipesWithTagAndOrder(Order order, List<String> tags, String search, int limit);

    Recipe addNewRecipe(Recipe recipe);

    List<Recipe> getAllRecipesOrderedByRating(String search, int limit);

    void update(int recipe, Map<String,Object> changes);

    List<RecipeTag> getAllTags();

    List<RecipeTag> getAllRecipeTags(Recipe recipe);

    void removeTagFromRecipe(RecipeTag tag);

    void addNewRecipeTag(RecipeTag tag);

    List<Recipe> getAllRecipesByUserId(int userId);

    //PARA LAS LISTAS
    void addNewUserList(RecipeList rl, int userId);

    RecipeList addNewUserList(String name, int userId);

    void addRecipeToUserList(int listId, int recipeId);

    List<RecipeList> getCookLists();

    List<RecipeList> getUserCookLists(int userId);

    List<Recipe> getRecipesfromCookList(int listId);

    void updateURList(int recipe_list_id, Map<String, Object> changes);

    void updateRList(int recipeListId, int recipeId, String status);

    boolean checkCookListUser(int listId, int userId);

    Optional<RecipeList> getCookList(int cookListId);

    List<Recipe> getAllRecipesOrderByRising(String search, int limit);

    boolean isTagDeleted(String tag, int id);

    void changeTagStatus(String tag, int id, Status regular);

    void addRecentlyCooked(int userId, int recipeId);

    List<Recipe> getRecipesInCookOrder(int userId);

    List<Recipe> getRecipesCookedInBetweenDates(int userId, Date from, Date to);

    int amountOfRecipesApplied(Order order, List<String> tags, String search);
}
