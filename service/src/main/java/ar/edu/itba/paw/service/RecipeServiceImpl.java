package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.IngredientsDao;
import ar.edu.itba.paw.interfaces.dao.RatingsDao;
import ar.edu.itba.paw.interfaces.dao.RecipeDao;
import ar.edu.itba.paw.interfaces.dao.UserDao;
import ar.edu.itba.paw.interfaces.service.RecipeService;
import ar.edu.itba.paw.model.*;
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

    @Override
    public Optional<Recipe> getById(int id) {
        return recipeDao.getById(id);
    }

    @Override
    public Optional<Recipe> getByIdWithIngredients(int id) {
        Optional<Recipe> maybeRecipe = recipeDao.getById(id);

        if(maybeRecipe.isPresent()) {
            Recipe recipe = maybeRecipe.get();

            Optional<List<RecipeIngredient>> maybeIngList = ingredientsDao.getByRecipeId(recipe.getId());
            if(maybeIngList.isPresent()) {
                List<RecipeIngredient> ingredientsList = maybeIngList.get();
                recipe.setIngredients(ingredientsList);

                return Optional.of(recipe);
            }
        }
        return maybeRecipe;
    }

    @Override
    public Optional<Recipe> findByName(String name) {
        return recipeDao.getByName(name);
    }

    @Override
    public Optional<List<Recipe>> findByUser(int userId) {
        return recipeDao.getByUserId(userId);
    }

    @Override
    public int userRecipesNumber(int userId) {
        int count = 0;
        Optional<List<Recipe>> maybeRecipe = findByUser(userId);
        if(maybeRecipe.isPresent()) {
            count = maybeRecipe.get().size();
        }
        return count;
    }

    @Override
    public Recipe findUserRecipeByName(int userId, String name) {
        Optional<List<Recipe>> op = this.findByUser(userId);
        if(op.isPresent()){
            List<Recipe> list = op.get();
            for(Recipe r : list) {
                if(r.getName().equals("name")) {
                    return r;
                }
            }
        }
        //TODO crear excepcion
        throw new RuntimeException();
    }

    @Transactional
    @Override
    public Recipe addNewRecipe(Recipe recipe) {
        return recipeDao.addNewRecipe(recipe);
    }

    @Override
    @Transactional
    public void update(Recipe recipe) {
        Optional<Recipe> maybeOldRecipe = recipeDao.getById(recipe.getId());
        if(maybeOldRecipe.isPresent()) {
           Recipe oldRecipe = maybeOldRecipe.get();
           Map<String,Object> map = new HashMap<>();

           if(!oldRecipe.getDescription().equals(recipe.getDescription())){
               map.put("description",recipe.getDescription());
           }
           if(!oldRecipe.getName().equals(recipe.getName())){
               map.put("name",recipe.getName());
           }
           if(!oldRecipe.getInstructions().equals(recipe.getInstructions())) {
               map.put("instructions",recipe.getInstructions());
           }

            recipeDao.update(recipe,map);
        }

    }

    @Transactional
    @Override
    public void deleteRecipe(Recipe recipe) {
        Map<String,Object> map = new HashMap<>();
        map.put("status",0);
        recipeDao.update(recipe,map);
    }

    @Override
    public Optional<List<RecipeTag>> getAllRecipeTags(Recipe recipe) {
        return recipeDao.getAllRecipeTags(recipe);
    }

    @Transactional
    @Override
    public void removeTagFromRecipe(Recipe recipe, RecipeTag tag) {
        recipeDao.removeTagFromRecipe(recipe, tag);
    }

    @Transactional
    @Override
    public void addNewRecipeTag(Recipe recipe, RecipeTag tag) {

        recipeDao.addNewRecipeTag(recipe, tag);
    }

    @Override
    @Transactional
    public void addNewRating(int user, int recipe, int rating) {
        Optional<Rating> maybeRating = ratingsDao.getSpecificRating(user, recipe);
        if (maybeRating.isPresent())
            ratingsDao.update(user,recipe,"status",1);
        else
            ratingsDao.addNewRating(user, recipe, rating);
    }

    @Override
    @Transactional
    public void updateRating(int user, int recipe, int rating) {
        ratingsDao.update(user,recipe,"rating",rating);
    }

    @Override
    @Transactional
    public void deleteRating(int user, int recipe, int rating) {
        ratingsDao.update(user,recipe,"status",0);
    }

    @Override
    public Optional<List<Recipe>> getAllRecipesByUserId(int userId) {
        return recipeDao.getAllRecipesByUserId(userId);
    }

    @Override
    public Optional<List<Recipe>> getRecipes() {
        return recipeDao.getAllRecipes();
    }
}
