package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.IngredientsDao;
import ar.edu.itba.paw.interfaces.dao.RecipeDao;
import ar.edu.itba.paw.interfaces.dao.UserDao;
import ar.edu.itba.paw.interfaces.service.RecipeService;
import ar.edu.itba.paw.model.Recipe;
import ar.edu.itba.paw.model.RecipeIngredient;
import ar.edu.itba.paw.model.User;
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
    public Optional<List<Recipe>> findByUser(User user) {
        return recipeDao.getByUserId(user.getId());
    }

    @Override
    public Recipe findUserRecipeByName(User u, String name) {
        Optional<List<Recipe>> op = this.findByUser(u);
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
    public void update(Recipe recipe, String change, Object value) {
        Map<String,Object> map = new HashMap<>();
        map.put(change,value);
        this.update(recipe,map);
    }

    @Transactional
    @Override
    public void update(Recipe recipe, Map<String, Object> map) throws RuntimeException {
        map.forEach((k,v)->{
            if(!k.equals("name") && !k.equals("description") && !k.equals("instructions") &&
                    !k.equals("status")) {
                throw new RuntimeException();
            }
        });
        recipeDao.update(recipe,map);
    }

    @Override
    public void deleteRecipe(Recipe recipe) {
        this.update(recipe,"status", 0);
    }
}
