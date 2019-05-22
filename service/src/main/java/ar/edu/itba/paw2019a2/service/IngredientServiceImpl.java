package ar.edu.itba.paw2019a2.service;

import ar.edu.itba.paw2019a2.interfaces.dao.IngredientsDao;
import ar.edu.itba.paw2019a2.interfaces.dao.RecipeDao;
import ar.edu.itba.paw2019a2.interfaces.dao.UserDao;
import ar.edu.itba.paw2019a2.interfaces.service.IngredientService;
import ar.edu.itba.paw2019a2.model.Either;
import ar.edu.itba.paw2019a2.model.Enum.Warnings;
import ar.edu.itba.paw2019a2.model.Ingredient;
import ar.edu.itba.paw2019a2.model.RecipeIngredient;
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
    public List<Ingredient> getAllIngredients() {
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
    public List<RecipeIngredient> findByUser(int userId) {
        List<RecipeIngredient> list = ingredientsDao.getByUserId(userId);

        for (RecipeIngredient i : list) {
            Optional<Ingredient> ingredient = ingredientsDao.getById(i.getIngredient().getId());
            ingredient.ifPresent(i::setIngredient);
        }
        return list;
    }

    @Override
    public Either<RecipeIngredient,Warnings> findUserIngredientByName(int u, String name) {
        List<RecipeIngredient> list = this.findByUser(u);

        for (RecipeIngredient r : list) {
            if (r.getIngredient().getName().equals(name)) {
                return Either.value(r);
            }
        }
        return Either.alternative(Warnings.valueOf("CouldNotFindIngredient"));
    }

    @Override
    public List<RecipeIngredient> findByRecipe(int recipeId) {
        List<RecipeIngredient> list = ingredientsDao.getByRecipeId(recipeId);
        for (RecipeIngredient i : list) {
            Optional<Ingredient> ingredient = ingredientsDao.getById(i.getIngredient().getId());
            ingredient.ifPresent(i::setIngredient);
        }
        return list;
    }

    @Override
    public Either<RecipeIngredient,Warnings> findRecipeIngredientByName(int recipe, String name) {
        List<RecipeIngredient> list = this.findByRecipe(recipe);
        for (RecipeIngredient r : list) {
            if (r.getIngredient().getName().equals(name)) {
                return Either.value(r);
            }
        }
        return Either.alternative(Warnings.valueOf("CouldNotFindIngredient"));
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
                map.put("ri_status", "REGULAR");
                map.put("serving_amount", 0);
                ingredientsDao.updateRecipeIngredient(ri.getIngredient().getId(), map, recipe);
                this.updateRI(ri, recipe);

            } else {
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
                map.put("ui_status", "REGULAR");
                map.put("serving_amount", 0);
                ingredientsDao.updateUserIngredient(ri.getIngredient().getId(), map, user);
                this.updateUI(ri, user);

            } else {
                ingredientsDao.addNewUserIngredient(user, ri);
            }
        }
        return ri;
    }

    @Override
    public void addNewUserIngredient(List<RecipeIngredient> recipeIngredientList, int user) {
        for (RecipeIngredient ri: recipeIngredientList) {
            addNewUserIngredient(ri,user);
        }
    }

        @Transactional
    @Override
    public Ingredient addNewIngredient(Ingredient i) {
        return ingredientsDao.addNewIngredient(i);
    }

    @Transactional
    @Override
    public void updateI(Ingredient ingredient) {
        Optional<Ingredient> maybeIngredient = ingredientsDao.getById(ingredient.getId());
        if (maybeIngredient.isPresent()) {
            Ingredient oldIngredient = maybeIngredient.get();
            Map<String, Object> map = new HashMap<>();
            if (!oldIngredient.getName().equals(ingredient.getName())) {
                map.put("ingredient_name", ingredient.getName());
            }
            if (oldIngredient.getCalories() != (ingredient.getCalories())) {
                map.put("calorie_count", ingredient.getCalories());
            }
            if (oldIngredient.getCarbohydrates() != (ingredient.getCarbohydrates())) {
                map.put("carbohydrate_count", ingredient.getCarbohydrates());
            }
            if (oldIngredient.isTaccFree() != (ingredient.isTaccFree())) {
                map.put("tacc_free", ingredient.isTaccFree());
            }
            if (oldIngredient.isVegetarian() != (ingredient.isVegetarian())) {
                map.put("is_vegetarian", ingredient.isVegetarian());
            }
            if (oldIngredient.isVegan() != (ingredient.isVegan())) {
                map.put("is_vegan", ingredient.isVegan());
            }
            if (oldIngredient.getProtein() != (ingredient.getProtein())) {
                map.put("protein_count", ingredient.getProtein());
            }
            if (oldIngredient.getTotalFat() != (ingredient.getTotalFat())) {
                map.put("fat_count", ingredient.getTotalFat());
            }
            if (oldIngredient.getSugar() != (ingredient.getSugar())) {
                map.put("sugar_count", ingredient.getSugar());
            }
            if (!oldIngredient.getTypeOfServing().equals(ingredient.getTypeOfServing())) {
                map.put("serving_type", ingredient.getTypeOfServing());
            }
            if (oldIngredient.getServing() != ingredient.getServing()) {
                map.put("serving", ingredient.getServing());
            }

            ingredientsDao.updateIngredient(oldIngredient, map);
        }
    }

    @Transactional
    @Override
    public void updateRI(RecipeIngredient ri, int recipe) {
        Optional<RecipeIngredient> maybeRI = ingredientsDao.getUserIngById(ri.getIngredient().getId(), recipe);
        if (maybeRI.isPresent()) {
            RecipeIngredient recipeIngredient = maybeRI.get();

            ingredientsDao.updateRecipeIngredient(recipeIngredient.getIngredient().getId(), updateRUIInternal(recipeIngredient, ri), recipe);
        }
    }

    private Map<String, Object> updateRUIInternal(RecipeIngredient old, RecipeIngredient newRI) {
        Map<String, Object> map = new HashMap<>();
        if (newRI.getObservation() != null && !newRI.getObservation().equals(old.getObservation())) {
            map.put("obs", newRI.getObservation());
        }
        if (old.getAmount() != newRI.getAmount()) {
            map.put("serving_amount", newRI.getAmount());
        }
        return map;
    }


    @Transactional
    @Override
    public void updateUI(RecipeIngredient newRecipeIngredient, int user) {
        Optional<RecipeIngredient> maybeRI = ingredientsDao.getUserIngById(newRecipeIngredient.getIngredient().getId(), user);
        if (maybeRI.isPresent()) {
            RecipeIngredient oldrecipeIngredient = maybeRI.get();
            ingredientsDao.updateUserIngredient(oldrecipeIngredient.getIngredient().getId(), updateRUIInternal(oldrecipeIngredient, newRecipeIngredient), user);
        }
    }


    @Override
    public void deleteRI(int ri, int recipe) {
        Map<String, Object> map = new HashMap<>();
        map.put("ri_status", "DELETED");
        ingredientsDao.updateRecipeIngredient(ri, map, recipe);
    }

    @Override
    public void deleteUI(int ri, int user) {
        Map<String, Object> map = new HashMap<>();
        map.put("ui_status", "DELETED");
        ingredientsDao.updateUserIngredient(ri, map, user);
    }

    @Override
    public void deleteI(Ingredient i) {
        Map<String, Object> map = new HashMap<>();
        map.put("ingredient_status", "DELETED");
        ingredientsDao.updateIngredient(i, map);
    }

    @Override
    public Warnings cookRecipe(List<RecipeIngredient> ri, int userId) {
        List<RecipeIngredient> rep = ingredientsDao.getByUserId(userId);

        for (RecipeIngredient recipeIngredient : ri) {

            boolean flag = false;

            for (RecipeIngredient userIngredient : rep) {
                if ((userIngredient.getIngredient().equals(recipeIngredient.getIngredient()))) {
                    flag = true;
                    if (userIngredient.getAmount() - recipeIngredient.getAmount() < 0) {
                        return Warnings.valueOf("CantCookRecipe");
                    }
                }
            }
            if (!flag) {
                return Warnings.valueOf("CantCookRecipe");
            }
        }

        for (RecipeIngredient recipeIngredient : ri) {

            for (RecipeIngredient userIngredient : rep) {

                if (userIngredient.getIngredient().equals(recipeIngredient.getIngredient())) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("serving_amount", userIngredient.getAmount() - recipeIngredient.getAmount());
                    ingredientsDao.updateUserIngredient(recipeIngredient.getIngredient().getId(), map, userId);
                }
            }
        }
        return Warnings.valueOf("Success");
    }
}
