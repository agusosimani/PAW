package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.dao.IngredientsDao;
import ar.edu.itba.paw.model.Ingredient;
import ar.edu.itba.paw.model.RecipeIngredient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceImplTest {
    private int INGREDIENT_ID = 1;
    private String INGREDIENT_NAME = "Ingredient";
    private boolean INGREDIENT_BOOLEANS = false;
    private double INGREDIENT_DOUBLES = 1.0;
    private float INGREDIENT_FLOAT = (float)1.0;
    private String INGREDIENT_TYPE_OF_SERVING = "Units";
    private int INGREDIENT_USER_ID = 1;
    private String INGREDIENT_STATUS = "REGULAR";

    @InjectMocks
    private IngredientServiceImpl ingredientService = new IngredientServiceImpl();

    @Mock
    private IngredientsDao ingredientsDao;

    //findByUser

    //findUserIngredientsByName

    //findByRecipe

    //findRecipeIngredientByName

    @Test
    public void testAddNewRecipeIngredientAlreadyExists() {
        //1. Setup
        Ingredient ingredient = new Ingredient.Builder(INGREDIENT_ID, INGREDIENT_NAME, INGREDIENT_DOUBLES, INGREDIENT_TYPE_OF_SERVING, INGREDIENT_USER_ID, INGREDIENT_STATUS).build();
        RecipeIngredient recipeIngredient = new RecipeIngredient.Builder(ingredient, INGREDIENT_FLOAT).build();

        int RECIPE_ID = 1;

        Mockito.when(ingredientsDao.getById(INGREDIENT_ID)).thenReturn(Optional.of(ingredient));
        Mockito.when(ingredientsDao.getRecipeIngById(INGREDIENT_ID, RECIPE_ID)).thenReturn(Optional.of(recipeIngredient));

        //2.
        RecipeIngredient ret = ingredientService.addNewRecipeIngredient(recipeIngredient, RECIPE_ID);

        //3. Asserts
        Assert.assertEquals(2 * INGREDIENT_FLOAT, ret.getAmount(), 0.001);
    }

    @Test
    public void testAddNewRecipeIngredientNotExists() {
        //1. Setup
        Ingredient ingredient = new Ingredient.Builder(INGREDIENT_ID, INGREDIENT_NAME, INGREDIENT_DOUBLES, INGREDIENT_TYPE_OF_SERVING, INGREDIENT_USER_ID, INGREDIENT_STATUS).build();
        RecipeIngredient recipeIngredient = new RecipeIngredient.Builder(ingredient, INGREDIENT_FLOAT).build();

        int INGREDIENT_ID_LOCAL = 10;

        Mockito.when(ingredientsDao.getById(INGREDIENT_ID)).thenReturn(Optional.empty());
        Mockito.doAnswer((i) -> {
            ingredient.setId(INGREDIENT_ID_LOCAL);
            return ingredient;
        }).when(ingredientsDao).addNewIngredient(ingredient);

        //2.
        RecipeIngredient ret = ingredientService.addNewRecipeIngredient(recipeIngredient, 0);

        //3. Asserts
        Assert.assertEquals(INGREDIENT_ID_LOCAL, ret.getIngredient().getId());
    }

    @Test
    public void testAddNewUserIngredientAlreadyExists() {
        //1. Setup
        Ingredient ingredient = new Ingredient.Builder(INGREDIENT_ID, INGREDIENT_NAME, INGREDIENT_DOUBLES, INGREDIENT_TYPE_OF_SERVING, INGREDIENT_USER_ID, INGREDIENT_STATUS).build();
        RecipeIngredient recipeIngredient = new RecipeIngredient.Builder(ingredient, INGREDIENT_FLOAT).build();

        int USER_ID = 1;

        Mockito.when(ingredientsDao.getById(INGREDIENT_ID)).thenReturn(Optional.of(ingredient));
        Mockito.when(ingredientsDao.getUserIngById(INGREDIENT_ID, USER_ID)).thenReturn(Optional.of(recipeIngredient));

        //2.
        RecipeIngredient ret = ingredientService.addNewUserIngredient(recipeIngredient, USER_ID);

        //3. Asserts
        Assert.assertEquals(2 * INGREDIENT_FLOAT, ret.getAmount(), 0.001);
    }

    @Test
    public void testAddNewUserIngredientNotExists() {
        //1. Setup
        Ingredient ingredient = new Ingredient.Builder(INGREDIENT_ID, INGREDIENT_NAME, INGREDIENT_DOUBLES, INGREDIENT_TYPE_OF_SERVING, INGREDIENT_USER_ID, INGREDIENT_STATUS).build();
        RecipeIngredient recipeIngredient = new RecipeIngredient.Builder(ingredient, INGREDIENT_FLOAT).build();

        int INGREDIENT_ID_LOCAL = 10;

        Mockito.when(ingredientsDao.getById(INGREDIENT_ID)).thenReturn(Optional.empty());
        Mockito.doAnswer((i) -> {
            ingredient.setId(INGREDIENT_ID_LOCAL);
            return ingredient;
        }).when(ingredientsDao).addNewIngredient(ingredient);

        //2.
        RecipeIngredient ret = ingredientService.addNewUserIngredient(recipeIngredient, 0);

        //3. Asserts
        Assert.assertEquals(INGREDIENT_ID_LOCAL, ret.getIngredient().getId());
    }

    //updateI
}
