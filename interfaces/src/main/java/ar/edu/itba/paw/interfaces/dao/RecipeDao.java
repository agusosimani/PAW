package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Recipe;
import ar.edu.itba.paw.model.RecipeTag;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RecipeDao {
    Optional<Recipe> getById(final int id);

    Optional<Recipe> getByName(final String name);

    Optional<List<Recipe>> getByUserId(final int id);

    Recipe addNewRecipe(Recipe recipe);

    void update(Recipe recipe, Map<String,Object> changes);

    Optional<RecipeTag> getTagByName(String name);

    Optional<List<RecipeTag>> getAllRecipeTags(Recipe recipe);

    void removeTagFromRecipe(Recipe recipe, RecipeTag tag);

    void addNewRecipeTag(Recipe recipe, RecipeTag tag);

}
