package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.*;
import ar.edu.itba.paw.interfaces.service.RecipeService;
import ar.edu.itba.paw.model.*;
import ar.edu.itba.paw.model.Enum.Warnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    RecipeDao recipeDao;

    @Autowired
    UserDao userDao;

    @Autowired
    IngredientsDao ingredientsDao;

    @Autowired
    RatingsDao ratingsDao;

    @Autowired
    CommentsDao commentsDao;

    @Override
    public Optional<Recipe> getById(int id) {

        Optional<Recipe> maybe = recipeDao.getById(id);
        if (maybe.isPresent()) {
            Recipe recipe = maybe.get();
            List<RecipeTag> tags = recipeDao.getAllRecipeTags(recipe);
            recipe.setTags(tags);

            List<RecipeIngredient> ingredientsList = ingredientsDao.getByRecipeId(recipe.getId());
            recipe.setIngredients(ingredientsList);

            List<Comment> commentsList = commentsDao.getAllRecipeComments(id);
            recipe.setComments(commentsList);

            return Optional.of(recipe);
        }
        return maybe;
    }

    @Override
    public Optional<Recipe> getByIdWithIngredients(int id) {
        Optional<Recipe> maybeRecipe = recipeDao.getById(id);

        if (maybeRecipe.isPresent()) {
            Recipe recipe = maybeRecipe.get();

            List<RecipeIngredient> ingredientsList = ingredientsDao.getByRecipeId(recipe.getId());
            recipe.setIngredients(ingredientsList);
            return Optional.of(recipe);
        }
        return maybeRecipe;
    }

    @Override
    public Optional<Recipe> findByName(String name) {
        return recipeDao.getByName(name);
    }

    @Override
    public List<Recipe> findByUser(int userId) {
        return recipeDao.getByUserId(userId);
    }

    @Override
    public int userRecipesNumber(int userId) {
        return findByUser(userId).size();
    }

    @Override
    public Recipe findUserRecipeByName(int userId, String name) {
        List<Recipe> list = this.findByUser(userId);
        for (Recipe r : list) {
            if (r.getName().equals("name")) {
                return r;
            }
        }
        //TODO crear excepcion
        throw new RuntimeException();
    }

    @Transactional
    @Override
    public Recipe addNewRecipe(Recipe recipe) throws RuntimeException{

        if(recipe.getName().isEmpty() || recipe.getInstructions().isEmpty()) {
            throw new RuntimeException();
        }

        Recipe rec = recipeDao.addNewRecipe(recipe);

        for (RecipeTag rt : rec.getTags()) {
            recipeDao.addNewRecipeTag(rt);
        }

        for (RecipeIngredient rt : rec.getIngredients()) {
            if (rt.getObservation() == null)
                rt.setObservation("");
            ingredientsDao.addNewRecipeIngredient(rec.getId(), rt);
        }

        for (Comment comment :recipe.getComments()) {
            commentsDao.addNewComment(comment);
        }
        return rec;
    }

    @Override
    @Transactional
    public void update(Recipe recipe) {
        Optional<Recipe> maybeOldRecipe = recipeDao.getById(recipe.getId());
        if (maybeOldRecipe.isPresent()) {
            Recipe oldRecipe = maybeOldRecipe.get();
            Map<String, Object> map = new HashMap<>();

            if (!oldRecipe.getDescription().equals(recipe.getDescription())) {
                map.put("description", recipe.getDescription());
            }
            if (!oldRecipe.getName().equals(recipe.getName())) {
                map.put("recipe_name", recipe.getName());
            }
            if (!oldRecipe.getInstructions().equals(recipe.getInstructions())) {
                map.put("instructions", recipe.getInstructions());
            }

            recipeDao.update(recipe.getId(), map);
        }

    }

    @Transactional
    @Override
    public void deleteRecipe(Recipe recipe) {
        Map<String, Object> map = new HashMap<>();
        map.put("recipe_status", "DELETED");
        recipeDao.update(recipe.getId(), map);
    }

    @Override
    public List<RecipeTag> getAllRecipeTags(Recipe recipe) {
        return recipeDao.getAllRecipeTags(recipe);
    }

    @Transactional
    @Override
    public void removeTagFromRecipe(Recipe recipe, RecipeTag tag) {
        recipeDao.removeTagFromRecipe(tag);
    }

    @Transactional
    @Override
    public void addNewRecipeTag(RecipeTag tag) {

        recipeDao.addNewRecipeTag(tag);
    }

    @Override
    @Transactional
    public void addNewRating(int user, int recipe, float rating) {
        Optional<Rating> maybeRating = ratingsDao.getSpecificRating(user, recipe);
        if (maybeRating.isPresent()) {
            ratingsDao.update(user, recipe, "status", "REGULAR");
            updateRatingRecipe(recipe, rating);

        } else
            ratingsDao.addNewRating(user, recipe, rating);
    }

    @Override
    @Transactional
    public void updateRating(int user, int recipe, float rating) {
        ratingsDao.update(user, recipe, "rating", rating);
        updateRatingRecipe(recipe, rating);
    }

    private void updateRatingRecipe(int recipe, float rating) {
        Optional<Float> maybeTotalRating = ratingsDao.getRecipeRating(recipe);
        Map<String,Object> map= new HashMap<>();
        if(maybeTotalRating.isPresent()) {
            float totalRating = maybeTotalRating.get();
            map.put("rating",totalRating);
            recipeDao.update(recipe,map);
        }
        else {
            map.put("rating",rating);
            recipeDao.update(recipe,map);
        }
    }

    @Override
    @Transactional
    public void deleteRating(int user, int recipe) {
        ratingsDao.update(user, recipe, "status", "DELETED");
        Optional<Float> maybeTotalRating = ratingsDao.getRecipeRating(recipe);
        Map<String,Object> map= new HashMap<>();
        if(maybeTotalRating.isPresent()) {
            float totalRating = maybeTotalRating.get();
            map.put("rating",totalRating);
            recipeDao.update(recipe,map);
        }
    }

    @Override
    public List<Recipe> getAllRecipesByUserId(int userId) {
        return recipeDao.getAllRecipesByUserId(userId);
    }

    @Override
    public List<Recipe> getRecipes() {
        List<Recipe> rList = recipeDao.getAllRecipes();
        for (Recipe rep : rList) {
            List<RecipeTag> tags = recipeDao.getAllRecipeTags(rep);
            rep.setTags(tags);
        }
        return rList;
    }

    @Override
    public List<RecipeTag> getAllTags() {
        return recipeDao.getAllTags();
    }


    @Override
    public List<Recipe> FilterRecipesByTags(List<String> tags) {
        return recipeDao.getRecipesWithTags(tags);
    }

    @Override
    public List<Recipe> getAllRecipesByDate() {
        return recipeDao.getAllRecipesOrderedByDate();
    }

    @Override
    public List<Recipe> getAllRecipesByRating() {
        return recipeDao.getAllRecipesOrderedByRating();
    }

    @Override
    public Optional<Float> getUserRating(int userId, int recipeId) {
        Optional<Rating> maybeRating = ratingsDao.getSpecificRating(userId,recipeId);
        return maybeRating.map(Rating::getRating);
    }

    @Transactional
    @Override
    public Either<Comment, Warnings> addComment(Comment comment) {
        if(comment == null)
            return Either.alternative(Warnings.valueOf("CouldNotAddComment"));
        if(comment.getMessage().isEmpty()|| comment.getMessage().equals(""))
            return Either.alternative(Warnings.valueOf("CouldNotAddComment"));
        return Either.value(commentsDao.addNewComment(comment));
    }

    @Override
    public List<Comment> getRecipeComments(int recipeId) {
        return commentsDao.getAllRecipeComments(recipeId);
    }

    




}
