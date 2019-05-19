package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Recipe;
import ar.edu.itba.paw.model.RecipeList;
import ar.edu.itba.paw.model.RecipeTag;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RecipeDao {
    Optional<Recipe> getById(final int id);

    Optional<Recipe> getByName(final String name);

    List<Recipe> getByUserId(final int id);

    List<Recipe> getAllRecipes();

    List<Recipe> getAllRecipesOrderedByDate();

    Recipe addNewRecipe(Recipe recipe);

    List<Recipe> getAllRecipesOrderedByRating();

    void update(int recipe, Map<String,Object> changes);

    List<RecipeTag> getAllTags();

    List<RecipeTag> getAllRecipeTags(Recipe recipe);

    void removeTagFromRecipe(RecipeTag tag);

    void addNewRecipeTag(RecipeTag tag);

    List<Recipe> getAllRecipesByUserId(int userId);

    List<Recipe> getRecipesWithTags(List<String> tags);

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
}
