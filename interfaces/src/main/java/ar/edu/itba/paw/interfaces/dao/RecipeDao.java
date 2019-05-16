package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Recipe;
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

}
