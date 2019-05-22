package ar.edu.itba.paw2019a2.service;

import ar.edu.itba.paw2019a2.interfaces.dao.CommentsDao;
import ar.edu.itba.paw2019a2.interfaces.dao.IngredientsDao;
import ar.edu.itba.paw2019a2.interfaces.dao.RecipeDao;
import ar.edu.itba.paw2019a2.model.Ingredient;
import ar.edu.itba.paw2019a2.model.Recipe;
import ar.edu.itba.paw2019a2.model.RecipeIngredient;
import ar.edu.itba.paw2019a2.model.RecipeTag;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceImplTest {
    private int RECIPE_ID = 1;
    private String RECIPE_NAME = "Recipe";
    private String RECIPE_DESCRIPTION = "A description about the recipe";
    private String RECIPE_INSTRUCTIONS = "An instruction of the recipe";
    private int RECIPE_USER_ID = 1;

    private int RECIPE_ID2 = 2;
    private String RECIPE_NAME2 = "Recipe 2";
    private String RECIPE_DESCRIPTION2 = "Another description about the recipe";
    private String RECIPE_INSTRUCTIONS2 = "Another instruction of the recipe";
    private int RECIPE_USER_ID2 = 2;

    private int INGREDIENT_ID = 1;
    private String INGREDIENT_NAME = "Ingredient";
    private boolean INGREDIENT_BOOLEANS = false;
    private double INGREDIENT_DOUBLES = 1.0;
    private float INGREDIENT_FLOAT = 1.0f;
    private String INGREDIENT_TYPE_OF_SERVING = "Units";
    private int INGREDIENT_USER_ID = 1;
    private String INGREDIENT_STATUS = "REGULAR";

    private int RECIPE_TAG_ID = 0;
    private String RECIPE_TAG_TAG = "Italian";

    private int RECIPE_TAG_ID2 = 1;
    private String RECIPE_TAG_TAG2 = "French";

    @InjectMocks
    private RecipeServiceImpl recipeService = new RecipeServiceImpl();

    @Mock
    private RecipeDao mockRecipeDao;

    @Mock
    private IngredientsDao mockIngredientsDao;

    @Mock
    private CommentsDao mockCommentsDao;

    @Test
    public void testGetById() {
        //1. Setup
        List<RecipeIngredient> ingredientsList = new ArrayList<>();
        Ingredient ingredient = new Ingredient.Builder(INGREDIENT_ID, INGREDIENT_NAME, INGREDIENT_DOUBLES, INGREDIENT_TYPE_OF_SERVING, INGREDIENT_USER_ID, INGREDIENT_STATUS).build();
        RecipeIngredient recipeIngredient = new RecipeIngredient.Builder(ingredient, INGREDIENT_FLOAT).build();
        ingredientsList.add(recipeIngredient);

        List<RecipeTag> recipeTags = new ArrayList<>();
        recipeTags.add(new RecipeTag(RECIPE_TAG_TAG, RECIPE_TAG_ID));
        recipeTags.add(new RecipeTag(RECIPE_TAG_TAG2, RECIPE_TAG_ID2));

        Recipe recipe = new Recipe.Builder(RECIPE_ID, RECIPE_NAME, null, RECIPE_INSTRUCTIONS, RECIPE_USER_ID).build();

        Mockito.when(mockRecipeDao.getById(RECIPE_ID))
                .thenReturn(Optional.of(recipe));

        Mockito.when(mockRecipeDao.getAllRecipeTags(recipe))
                .thenReturn(recipeTags);

        Mockito.when(mockCommentsDao.getAllRecipeComments(RECIPE_ID)).thenReturn(new ArrayList<>());

        Mockito.when(mockIngredientsDao.getByRecipeId(RECIPE_ID)).thenReturn(ingredientsList);

        //2.
        Optional<Recipe> maybe = recipeService.getById(RECIPE_ID);

        //3. Asserts
        Assert.assertTrue(maybe.isPresent());
        Assert.assertEquals(RECIPE_NAME, maybe.get().getName());
        Assert.assertEquals(INGREDIENT_NAME, maybe.get().getIngredients().get(0).getIngredient().getName());
        Assert.assertEquals(2, maybe.get().getTags().size());
        Assert.assertEquals(0, maybe.get().getComments().size());
    }

    @Test
    public void testGetByIdWithIngredients() {
        //1. Setup
        List<RecipeIngredient> ingredientsList = new ArrayList<>();
        Ingredient ingredient = new Ingredient.Builder(INGREDIENT_ID, INGREDIENT_NAME, INGREDIENT_DOUBLES, INGREDIENT_TYPE_OF_SERVING, INGREDIENT_USER_ID, INGREDIENT_STATUS).build();
        RecipeIngredient recipeIngredient = new RecipeIngredient.Builder(ingredient, INGREDIENT_FLOAT).build();
        ingredientsList.add(recipeIngredient);

        Recipe recipe = new Recipe.Builder(RECIPE_ID, RECIPE_NAME, null, RECIPE_INSTRUCTIONS, RECIPE_USER_ID).build();

        Mockito.when(mockRecipeDao.getById(RECIPE_ID))
                .thenReturn(Optional.of(recipe));

        Mockito.when(mockIngredientsDao.getByRecipeId(RECIPE_ID)).thenReturn(ingredientsList);

        //2.
        Optional<Recipe> maybe = recipeService.getByIdWithIngredients(RECIPE_ID);

        //3. Asserts
        Assert.assertTrue(maybe.isPresent());
        Assert.assertEquals(RECIPE_NAME, maybe.get().getName());
        Assert.assertEquals(INGREDIENT_NAME, maybe.get().getIngredients().get(0).getIngredient().getName());
    }

    @Test
    public void testFindUserRecipeByName() {
        //1. Setup
        Recipe recipe = new Recipe.Builder(RECIPE_ID, RECIPE_NAME, null, RECIPE_INSTRUCTIONS, RECIPE_USER_ID).build();
        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe);

        Mockito.when(mockRecipeDao.getByUserId(RECIPE_USER_ID))
                .thenReturn(recipeList);

        //2.
        Recipe ret = recipeService.findUserRecipeByName(RECIPE_USER_ID, RECIPE_NAME).getValue();

        //3. Asserts
        Assert.assertEquals(recipe, ret);
    }

    //testAddNewRecipe

    @Test
    public void testUpdateRecipe() {
        //1. Setup
        Recipe oldRecipe = new Recipe.Builder(RECIPE_ID, RECIPE_NAME, new ArrayList<>(), RECIPE_INSTRUCTIONS, RECIPE_USER_ID).build();
        Recipe newRecipe = new Recipe.Builder(RECIPE_ID, RECIPE_NAME2,new ArrayList<>(), RECIPE_INSTRUCTIONS2, RECIPE_USER_ID).build();

        Mockito.when(mockRecipeDao.getById(RECIPE_ID)).thenReturn(Optional.of(oldRecipe));

        Mockito.doAnswer((i) -> {
            //3. Asserts
            Assert.assertEquals(2, ((Map<String,Object>)i.getArgument(1)).keySet().size());
            Assert.assertEquals(RECIPE_NAME2, ((Map<String,Object>)i.getArgument(1)).get("recipe_name"));
            Assert.assertEquals(RECIPE_INSTRUCTIONS2, ((Map<String,Object>)i.getArgument(1)).get("instructions"));
            Assert.assertNull(((Map<String,Object>)i.getArgument(1)).get("description"));
            return null;
        }).when(mockRecipeDao).update(any(int.class), any());

        //2.
        recipeService.update(newRecipe);
    }

    //getRecipesBasedOnOrderTagsCookable
}
