package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Rating;
import ar.edu.itba.paw.model.Recipe;
import ar.edu.itba.paw.model.User;

import java.util.List;
import java.util.Optional;

public interface RatingsDao {

    void addNewRating(User user, Recipe recipe, int rating);

    void update(User user, Recipe recipe, String k, Object v);

    Optional<List<Rating>> getRatingsRecipe(Recipe recipe);

    Optional<List<Rating>> getRatingsUser(User user);

    Optional<Rating> getSpecificRating(User user, Recipe recipe);
}
