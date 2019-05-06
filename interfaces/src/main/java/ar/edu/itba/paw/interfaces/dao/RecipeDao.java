package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Recipe;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RecipeDao {
    Optional<Recipe> getById(final int id);

    Optional<Recipe> getByName(final String name);

    Optional<List<Recipe>> getByUserId(final int id);

    Recipe addNewRecipe(Recipe recipe);

    void update(Recipe recipe, Map<String,Object> changes);

}
