package ar.edu.itba.paw2019a2.interfaces.dao;

import ar.edu.itba.paw2019a2.model.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingsDao {

    void addNewRating(int userId, int recipeId, float rating);

    void update(int userId, int recipeId, String k, Object v);

    List<Rating> getRatingsRecipe(int recipeId);

    List<Rating> getRatingsUser(int userId);

    Optional<Rating> getSpecificRating(int userId, int recipeId);

    Optional<Float> getRecipeRating(int recipeId);

    List<Rating> getRatingsBiggerThanFour(int userId);

    int getAmountRatings(int recipeId);
}
