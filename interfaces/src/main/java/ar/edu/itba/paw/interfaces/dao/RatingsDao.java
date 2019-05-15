package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Rating;
import ar.edu.itba.paw.model.Recipe;
import ar.edu.itba.paw.model.User;

import java.util.List;
import java.util.Optional;

public interface RatingsDao {

    void addNewRating(int userId, int recipeId, int rating);

    void update(int userId, int recipeId, String k, Object v);

    Optional<List<Rating>> getRatingsRecipe(int recipeId);

    Optional<List<Rating>> getRatingsUser(int userId);

    Optional<Rating> getSpecificRating(int userId, int recipeId);
}
