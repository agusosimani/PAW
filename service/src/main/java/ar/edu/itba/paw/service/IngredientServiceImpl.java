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
    public Optional<List<Ingredient>> getAllIngredients() {
        return ingredientsDao.getAllIngredients();
    }

    @Override
    public Optional<Ingredient> getById(int id) {
        return ingredientsDao.getById(id);
    }

    @Override
    public Optional<Ingredient> findByName(String name) {
        return ingredientsDao.getByIngredientName(name);
    }

    @Override
    public Optional<List<RecipeIngredient>> findByUser(int userId) {
        Optional<List<RecipeIngredient>> maybeList = ingredientsDao.getByUserId(userId);

        if (maybeList.isPresent()) {
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
    public RecipeIngredient findUserIngredientByName(int u, String name) {
        Optional<List<RecipeIngredient>> op = this.findByUser(u);
        if (op.isPresent()) {
            List<RecipeIngredient> list = op.get();
            for (RecipeIngredient r : list) {
                if (r.getIngredient().getName().equals(name)) {
                    return r;
                }
            }
        }
        //TODO crear excepcion
        throw new RuntimeException();
    }

    @Override
    public Optional<List<RecipeIngredient>> findByRecipe(int recipeId) {
        Optional<List<RecipeIngredient>> maybeList = ingredientsDao.getByRecipeId(recipeId);
        if (maybeList.isPresent()) {
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
    public RecipeIngredient findRecipeIngredientByName(int recipe, String name) {
        Optional<List<RecipeIngredient>> op = this.findByRecipe(recipe);
        if (op.isPresent()) {
            List<RecipeIngredient> list = op.get();
            for (RecipeIngredient r : list) {
                if (r.getIngredient().getName().equals(name)) {
                    return r;
                }
            }
        }
        //TODO crear excepcion
        throw new RuntimeException();
    }

    @Transactional
    @Override
    public RecipeIngredient addNewRecipeIngredient(RecipeIngredient ri, int recipe) {
        Optional<Ingredient> maybeIngredient = ingredientsDao.getById(ri.getIngredient().getId());
        if (!maybeIngredient.isPresent()) {
            Ingredient i = this.addNewIngredient(ri.getIngredient());
            ri.setIngredient(i);
            ingredientsDao.addNewRecipeIngredient(recipe, ri);
            return ri;
        }
        Ingredient ing = maybeIngredient.get();
        Optional<RecipeIngredient> maybeRI = ingredientsDao.getRecipeIngById(ing.getId(), recipe);
        if (maybeRI.isPresent()) {
            ri.setAmount(ri.getAmount() + maybeRI.get().getAmount());
            this.updateRI(ri, recipe);

        } else {
            Optional<RecipeIngredient> maybeDeleted = ingredientsDao.getDeletedRecipeIngById(ing.getId(), recipe);
            if (maybeDeleted.isPresent()) {
                Map<String, Object> map = new HashMap<>();
                map.put("status", 1);
                map.put("serving_amount",0);
                ingredientsDao.updateRecipeIngredient(ri.getIngredient().getId(), map, recipe);
                this.updateRI(ri, recipe);

            }
            else {
                ingredientsDao.addNewRecipeIngredient(recipe, ri);
            }
        }
        return ri;
    }

    @Transactional
    @Override
    public RecipeIngredient addNewUserIngredient(RecipeIngredient ri, int user) {
        Optional<Ingredient> maybeIngredient = ingredientsDao.getById(ri.getIngredient().getId());
        if (!maybeIngredient.isPresent()) {
            Ingredient i = this.addNewIngredient(ri.getIngredient());
            ri.setIngredient(i);
            ingredientsDao.addNewUserIngredient(user, ri);
            return ri;
        }
        Ingredient ing = maybeIngredient.get();
        Optional<RecipeIngredient> maybeRI = ingredientsDao.getUserIngById(ing.getId(), user);
        if (maybeRI.isPresent()) {
            ri.setAmount(ri.getAmount() + maybeRI.get().getAmount());
            this.updateUI(ri, user);

        } else {
            Optional<RecipeIngredient> maybeDeleted = ingredientsDao.getDeletedUserIngById(ing.getId(), user);
            if (maybeDeleted.isPresent()) {
                Map<String, Object> map = new HashMap<>();
                map.put("status", 1);
                map.put("serving_amount",0);
                ingredientsDao.updateUserIngredient(ri.getIngredient().getId(), map, user);
                this.updateUI(ri, user);

            }
            else {
                ingredientsDao.addNewUserIngredient(user, ri);
            }
        }
        return ri;
    }

    @Transactional
    @Override
    public Ingredient addNewIngredient(Ingredient i) {
        return ingredientsDao.addNewIngredient(i);
    }

    @Override
    public void updateI(Ingredient ingredient, String change, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(change, value);
        this.updateI(ingredient, map);
    }

    @Transactional
    @Override
    public void updateI(Ingredient ingredient, Map<String, Object> map) {
        map.forEach((k, v) -> {
            if (!k.equals("name") && !k.equals("is_vegetarian") && !k.equals("is_vegan") &&
                    !k.equals("status") && !k.equals("tacc_free") && !k.equals("protein_count") &&
                    !k.equals("calorie_count") && !k.equals("fat_count") && !k.equals("sugar_count") &&
                    !k.equals("serving")) {
                throw new RuntimeException();
            }
        });
        ingredientsDao.updateIngredient(ingredient, map);
    }

    @Transactional
    @Override
    public void updateRI(RecipeIngredient ri, int recipe) {
        Optional<RecipeIngredient> maybeRI = ingredientsDao.getUserIngById(ri.getIngredient().getId(), recipe);
        if (maybeRI.isPresent()) {
            RecipeIngredient recipeIngredient = maybeRI.get();

            ingredientsDao.updateRecipeIngredient(recipeIngredient.getIngredient().getId(), updateRUIInternal(recipeIngredient,ri), recipe);
        }
    }

    private Map<String,Object> updateRUIInternal(RecipeIngredient old,RecipeIngredient newRI) {
        Map<String, Object> map = new HashMap<>();
        if (!old.getObservation().equals(newRI.getObservation())) {
            map.put("obs", newRI.getObservation());
        }
        if (old.getAmount() != newRI.getAmount()) {
            map.put("serving_amount", newRI.getAmount());
        }
        System.out.printf("entro");
        return map;
    }


    @Transactional
    @Override
    public void updateUI(RecipeIngredient newRecipeIngredient, int user) {
        Optional<RecipeIngredient> maybeRI = ingredientsDao.getUserIngById(newRecipeIngredient.getIngredient().getId(), user);
        if (maybeRI.isPresent()) {
            RecipeIngredient oldrecipeIngredient = maybeRI.get();
            ingredientsDao.updateUserIngredient(oldrecipeIngredient.getIngredient().getId(), updateRUIInternal(oldrecipeIngredient,newRecipeIngredient), user);
        }
    }


    @Override
    public void deleteRI(int ri, int recipe) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        ingredientsDao.updateRecipeIngredient(ri, map, recipe);
    }

    @Override
    public void deleteUI(int ri, int user) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        ingredientsDao.updateUserIngredient(ri, map, user);
    }

    @Override
    public void deleteI(Ingredient i) {
        this.updateI(i, "status", 0);
    }

    @Override
    public Boolean cookRecipe(RecipeIngredient ri, int userId) {
//        Optional<RecipeIngredient> rep = ingredientsDao.getUserIngById(ri.getIngredient().getId(),userId);
//        if(rep.isPresent()) {
//            RecipeIngredient recipeIngredient = rep.get();
//            Optional<List<RecipeIngredient>> maybeList = ingredientsDao.getByUserId(userId);
//            if(maybeList.isPresent()) {
//                List<RecipeIngredient> list = maybeList.get();
//
//            }
//        }
//    } TODO: Hacerlo
        return false;
    }
}
