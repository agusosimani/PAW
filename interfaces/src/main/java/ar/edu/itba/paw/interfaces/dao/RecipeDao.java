package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeDao {
    Optional<Recipe> getById(final int id);
    Optional<List<Recipe>> getByUserId(final int id);
}
