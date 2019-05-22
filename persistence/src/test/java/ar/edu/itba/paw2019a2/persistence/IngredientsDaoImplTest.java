package ar.edu.itba.paw2019a2.persistence;

import ar.edu.itba.paw2019a2.interfaces.dao.IngredientsDao;
import ar.edu.itba.paw2019a2.model.Ingredient;
import ar.edu.itba.paw2019a2.model.RecipeIngredient;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql({"classpath:schema.sql", "classpath:init.sql"})
public class IngredientsDaoImplTest {

    @Autowired
    private DataSource ds;

    @Autowired
    private IngredientsDao ingredientsDao;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @After
    public void cleanUp() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "user_ingredients");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "recipes_ingredients");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "recipes");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "ingredients");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users");
    }

    @Test
    public void testGetUserIngredientById() {
        Optional<RecipeIngredient> maybeIngredient = ingredientsDao.getUserIngById(1, 1);

        Assert.assertTrue(maybeIngredient.isPresent());
        Assert.assertEquals(maybeIngredient.get().getObservation(), "obs");
        Assert.assertEquals(1, maybeIngredient.get().getIngredient().getId());
    }

    @Test
    public void testGetByIdSuccess() {
        Optional<Ingredient> maybeIngredient = ingredientsDao.getById(2);

        Assert.assertTrue(maybeIngredient.isPresent());
        Assert.assertEquals(maybeIngredient.get().getId(), 2);
        Assert.assertEquals(maybeIngredient.get().getName(), "Salmon");
        Assert.assertEquals(maybeIngredient.get().getServing(), 100, 0.001);
    }

    @Test
    public void testGetByIdFail() {
        Optional<Ingredient> maybeIngredient = ingredientsDao.getById(1000);

        Assert.assertFalse(maybeIngredient.isPresent());
    }

    @Test
    public void testGetAllIngredients() {
        List<Ingredient> allIngredients = ingredientsDao.getAllIngredients();

        Assert.assertEquals(2, allIngredients.size());

        boolean flag1 = false;
        boolean flag2 = false;
        for (int i = 0; i < allIngredients.size(); i++) {
            flag1 = flag1 || allIngredients.get(i).getName().equals("ChickenBreast");
            flag2 = flag2 || allIngredients.get(i).getName().equals("Salmon");
        }

        Assert.assertTrue(flag1);
        Assert.assertTrue(flag2);
    }

    @Test
    public void testGetByIngredientNameSuccess() {
        Optional<Ingredient> maybeIngredient = ingredientsDao.getByIngredientName("Salmon");

        Assert.assertTrue(maybeIngredient.isPresent());
        Assert.assertEquals(2, maybeIngredient.get().getId());
        Assert.assertEquals("Salmon", maybeIngredient.get().getName());
        Assert.assertEquals("Grams", maybeIngredient.get().getTypeOfServing());
    }

    @Test
    public void testGetByIngredientNameFail() {
        Optional<Ingredient> maybeIngredient = ingredientsDao.getByIngredientName("random");

        Assert.assertTrue(!maybeIngredient.isPresent());
    }

    @Test
    public void testGetByUserId() {
        List<RecipeIngredient> userIngredients = ingredientsDao.getByUserId(2);

        Assert.assertEquals(1, userIngredients.size());
        Assert.assertEquals("Salmon", userIngredients.get(0).getIngredient().getName());
    }

    @Test
    public void testAddNewIngredient() {
        Ingredient ingredient = ingredientsDao.addNewIngredient(
                new Ingredient.Builder("Ingredient", 5.0, "Grams", 1)
        .calories(5.0).isVegan(false).isVegetararian(false).carbohydrates(4.0).proteins(3.0).build());

        Optional<Ingredient> foundIngredient = ingredientsDao.getById(ingredient.getId());

        Assert.assertTrue(foundIngredient.isPresent());
        Assert.assertEquals(ingredient, foundIngredient.get());
    }

    @Test
    public void testUpdate() {
        String newName = "Ingredient";
        String newServingType = "Units";
        float floats = 100.0f;
        boolean booleans = true;

        Map<String, Object> map = new HashMap<>();
        map.put("ingredient_name", newName);
        map.put("serving", floats);
        map.put("serving_type", newServingType);
        map.put("is_vegan", booleans);
        map.put("is_vegetarian", booleans);
        map.put("tacc_free", booleans);
        map.put("sugar_count", floats);

        Ingredient ingredient = ingredientsDao.getById(2).get();
        ingredientsDao.updateIngredient(ingredient, map);
        Optional<Ingredient> updatedIngredient = ingredientsDao.getById(2);

        Assert.assertTrue(updatedIngredient.isPresent());
        Assert.assertEquals(updatedIngredient.get().getName(), newName);
        Assert.assertEquals(updatedIngredient.get().getServing(), floats, 0.001);
        Assert.assertEquals(updatedIngredient.get().getTypeOfServing(), newServingType);
        Assert.assertEquals(updatedIngredient.get().isVegan(), booleans);
        Assert.assertEquals(updatedIngredient.get().isVegetarian(), booleans);
        Assert.assertEquals(updatedIngredient.get().isTaccFree(), booleans);
        Assert.assertEquals(updatedIngredient.get().getSugar(), floats, 0.001);
    }
}
