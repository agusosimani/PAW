package ar.edu.itba.paw2019a2.persistence;

import ar.edu.itba.paw2019a2.interfaces.dao.RecipeDao;
import ar.edu.itba.paw2019a2.model.Recipe;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;
import javax.swing.text.html.Option;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql({"classpath:schema.sql", "classpath:init.sql"})
public class RecipeDaoTest {

    @Autowired
    private DataSource ds;

    @Autowired
    private RecipeDao recipeDao;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @After
    public void cleanUp() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "recipes_ingredients");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "recipes");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "ingredients");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users");
    }

    @Test
    public void testGetByIdSuccess() {
        Optional<Recipe> maybeRecipe = recipeDao.getById(1);

        Assert.assertTrue(maybeRecipe.isPresent());
        Assert.assertEquals(1, maybeRecipe.get().getId());
        Assert.assertEquals("recipe1", maybeRecipe.get().getName());
        Assert.assertEquals("description1", maybeRecipe.get().getDescription());
        Assert.assertEquals("instructions1", maybeRecipe.get().getInstructions());
    }

    @Test
    public void testGetByIdFail() {
        Optional<Recipe> maybeRecipe = recipeDao.getById(999);

        Assert.assertFalse(maybeRecipe.isPresent());
    }

    @Test
    public void testGetByNameSuccess() {
        Optional<Recipe> maybeRecipe = recipeDao.getByName("recipe1");

        Assert.assertTrue(maybeRecipe.isPresent());
        Assert.assertEquals(1, maybeRecipe.get().getId());
        Assert.assertEquals("recipe1", maybeRecipe.get().getName());
        Assert.assertEquals("description1", maybeRecipe.get().getDescription());
        Assert.assertEquals("instructions1", maybeRecipe.get().getInstructions());
    }

    @Test
    public void testGetByNameFail() {
        Optional<Recipe> maybeRecipe = recipeDao.getByName("random");

        Assert.assertFalse(maybeRecipe.isPresent());
    }

    @Test
    public void testGetByUserIdSuccess() {
        List<Recipe> recipeList = recipeDao.getByUserId(1);

        Assert.assertEquals(1, recipeList.size());
        Assert.assertEquals(1, recipeList.get(0).getId());
        Assert.assertEquals("recipe1", recipeList.get(0).getName());
        Assert.assertEquals("description1", recipeList.get(0).getDescription());
        Assert.assertEquals("instructions1", recipeList.get(0).getInstructions());
    }

    @Test
    public void testGetByUserIdFail() {
        List<Recipe> recipeList = recipeDao.getByUserId(999);

        Assert.assertEquals(0, recipeList.size());
    }

    @Test
    public void testGetAllRecipes() {
        List<Recipe> allRecipes = recipeDao.getAllRecipes();

        Assert.assertEquals(2, allRecipes.size());

        boolean flag1 = false;
        boolean flag2 = false;
        for (int i = 0; i < allRecipes.size(); i++) {
            flag1 = flag1 || allRecipes.get(i).getName().equals("recipe1");
            flag2 = flag2 || allRecipes.get(i).getName().equals("recipe2");
        }

        Assert.assertTrue(flag1);
        Assert.assertTrue(flag2);
    }

    @Test
    public void testAddNewRecipe() {
        Recipe recipe = recipeDao.addNewRecipe(new Recipe.Builder("recipe3", new ArrayList<>(), "instructions3", 2)
        .description("description3").build());

        Optional<Recipe> foundRecipe = recipeDao.getById(recipe.getId());

        Assert.assertTrue(foundRecipe.isPresent());
        Assert.assertEquals(recipe, foundRecipe.get());
    }

    @Test
    public void updateRecipe() {
        String newName = "recipe3";
        String newInstructions = "instructions3";
        String newDescription = "description3";
        float newRating = (float)3.0;

        Map<String, Object> map = new HashMap<>();
        map.put("recipe_name", newName);
        map.put("instructions", newInstructions);
        map.put("description", newDescription);
        map.put("rating", newRating);

        recipeDao.update(1, map);
        Optional<Recipe> updatedRecipe = recipeDao.getById(1);

        Assert.assertTrue(updatedRecipe.isPresent());
        Assert.assertEquals(updatedRecipe.get().getName(), newName);
        Assert.assertEquals(updatedRecipe.get().getInstructions(), newInstructions);
        Assert.assertEquals(updatedRecipe.get().getDescription(), newDescription);
        Assert.assertEquals(updatedRecipe.get().getRating(), newRating, 0.001);
    }

    @Test
    public void testGetAllRecipesByUserId() {
        List<Recipe> recipeList = recipeDao.getAllRecipesByUserId(1);

        Assert.assertEquals(1, recipeList.size());
    }
}
