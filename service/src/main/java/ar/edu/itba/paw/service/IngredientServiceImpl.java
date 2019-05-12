package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.IngredientsDao;
import ar.edu.itba.paw.interfaces.dao.RecipeDao;
import ar.edu.itba.paw.interfaces.dao.UserDao;
import ar.edu.itba.paw.interfaces.service.IngredientService;
import ar.edu.itba.paw.model.Ingredient;
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
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    RecipeDao recipeDao;

    @Autowired
    UserDao userDao;

    @Autowired
    IngredientsDao ingredientsDao;


    @Override
    public Optional<Ingredient> getById(int id) {
        return ingredientsDao.getById(id);
    }

    @Override
    public Optional<Ingredient> findByName(String name) {
        return ingredientsDao.getByIngredientName(name);
    }

    @Override
    public Optional<List<RecipeIngredient>> findByUser(User user) {
        Optional<List<RecipeIngredient>> maybeList = ingredientsDao.getByUserId(user.getId());

        if(maybeList.isPresent()) {
            List<RecipeIngredient> list = maybeList.get();
            for (RecipeIngredient i : list) {
                Optional<Ingredient> ingredient = ingredientsDao.getById(i.getIngredient().getId());
                ingredient.ifPresent(i::setIngredient);
            }
            return Optional.of(list);
        }


        return maybeList;
    }

    @Override
    public RecipeIngredient findUserIngredientByName(User u, String name) {
        Optional<List<RecipeIngredient>> op = this.findByUser(u);
        if(op.isPresent()){
            List<RecipeIngredient> list = op.get();
            for(RecipeIngredient r : list) {
                if(r.getIngredient().getName().equals(name)) {
                    return r;
                }
            }
        }
        //TODO crear excepcion
        throw new RuntimeException();
    }

    @Override
    public Optional<List<RecipeIngredient>> findByRecipe(Recipe recipe) {
        Optional<List<RecipeIngredient>> maybeList = ingredientsDao.getByRecipeId(recipe.getId());
        if(maybeList.isPresent()) {
            List<RecipeIngredient> list = maybeList.get();
            for (RecipeIngredient i : list) {
                Optional<Ingredient> ingredient = ingredientsDao.getById(i.getIngredient().getId());
                ingredient.ifPresent(i::setIngredient);
            }
            return Optional.of(list);
        }
        return maybeList;
    }

    @Override
    public RecipeIngredient findRecipeIngredientByName(Recipe recipe, String name) {
        Optional<List<RecipeIngredient>> op = this.findByRecipe(recipe);
        if(op.isPresent()){
            List<RecipeIngredient> list = op.get();
            for(RecipeIngredient r : list) {
                if(r.getIngredient().getName().equals(name)) {
                    return r;
                }
            }
        }
        //TODO crear excepcion
        throw new RuntimeException();
    }

    @Transactional
    @Override
    public RecipeIngredient addNewRecipeIngredient(RecipeIngredient ri, Recipe recipe) {
        Optional<Ingredient> maybeIngredient = ingredientsDao.getById(ri.getIngredient().getId());
        if (!maybeIngredient.isPresent()) {
            Ingredient ing =this.addNewIngredient(ri.getIngredient());
            ri.setIngredient(ing);
        }
        ingredientsDao.addNewRecipeIngredient(recipe,ri);
        return ri;
    }

    @Transactional
    @Override
    public RecipeIngredient addNewUserIngredient(RecipeIngredient ri, User user) {
        Optional<Ingredient> maybeIngredient = ingredientsDao.getById(ri.getIngredient().getId());
        if (!maybeIngredient.isPresent()) {
            Ingredient i =this.addNewIngredient(ri.getIngredient());
            ri.setIngredient(i);
        }
        ingredientsDao.addNewUserIngredient(user,ri);
        return ri;
    }

    @Transactional
    @Override
    public Ingredient addNewIngredient(Ingredient i) {
        return ingredientsDao.addNewIngredient(i);
    }

    @Override
    public void updateI(Ingredient ingredient, String change, Object value) {
        Map<String,Object> map = new HashMap<>();
        map.put(change,value);
        this.updateI(ingredient,map);
    }

    @Transactional
    @Override
    public void updateI(Ingredient ingredient, Map<String, Object> map) {
        map.forEach((k,v)->{
            if(!k.equals("name") && !k.equals("is_vegetarian") && !k.equals("is_vegan") &&
                    !k.equals("status")&& !k.equals("tacc_free") && !k.equals("protein_count") &&
                    !k.equals("calorie_count") && !k.equals("fat_count") && !k.equals("sugar_count") &&
                    !k.equals("serving")) {
                throw new RuntimeException();
            }
        });
        ingredientsDao.updateIngredient(ingredient,map);
    }

    @Override
    public void updateRI(RecipeIngredient ingredient,Recipe recipe, String change, Object value) {
        Map<String,Object> map = new HashMap<>();
        map.put(change,value);
        this.updateRI(ingredient,recipe,map);
    }


    //TODO: Agregar status a ri y ui en la db
    @Transactional
    @Override
    public void updateRI(RecipeIngredient ingredient,Recipe recipe, Map<String, Object> map) {
        map.forEach((k,v)->{
            if(!k.equals("obs") && !k.equals("serving_amount")) {
                throw new RuntimeException();
            }
        });
        ingredientsDao.updateRecipeIngredient(ingredient,map,recipe);
    }

    @Override
    public void updateUI(RecipeIngredient ingredient, User user, String change, Object value) {
        Map<String,Object> map = new HashMap<>();
        map.put(change,value);
        this.updateUI(ingredient,user,map);
    }

    @Transactional
    @Override
    public void updateUI(RecipeIngredient ingredient, User user, Map<String, Object> map) {
        map.forEach((k,v)->{
            if(!k.equals("obs") && !k.equals("serving_amount")) {
                throw new RuntimeException();
            }
        });
        ingredientsDao.updateUserIngredient(ingredient,map,user);
    }

    @Override
    public void deleteRI(RecipeIngredient ri,Recipe recipe) {
        this.updateRI(ri,recipe,"status",0);
    }

    @Override
    public void deleteUI(RecipeIngredient ui, User user) {
        this.updateUI(ui, user,"status",0);

    }

    @Override
    public void deleteI(Ingredient i) {
        this.updateI(i,"status",0);
    }
}
