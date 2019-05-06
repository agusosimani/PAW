package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.Recipe;
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

    void update(Recipe recipe, String change, Object value);

    void update(Recipe recipe, Map<String,Object> map);

    void deleteRecipe(Recipe recipe);

}
