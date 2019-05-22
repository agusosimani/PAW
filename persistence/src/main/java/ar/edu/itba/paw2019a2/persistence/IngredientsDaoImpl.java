package ar.edu.itba.paw2019a2.persistence;

import ar.edu.itba.paw2019a2.interfaces.dao.IngredientsDao;
import ar.edu.itba.paw2019a2.model.Ingredient;
import ar.edu.itba.paw2019a2.model.RecipeIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class IngredientsDaoImpl implements IngredientsDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsertIng;
    private SimpleJdbcInsert jdbcInsertRecIng;
    private SimpleJdbcInsert jdbcInsertUserIng;


    private final static RowMapper<Ingredient> INGREDIENT_ROW_MAPPER = (rs, rowNum) ->
            new Ingredient.Builder(rs.getInt("ingredient_id"),
            rs.getString("ingredient_name"),
            rs.getInt("serving"),
            rs.getString("serving_type"), rs.getInt("user_id"),
            rs.getString("ingredient_status"))

            .calories(rs.getInt("calorie_count"))
            .carbohydrates(rs.getInt("carbohydrate_count"))
            .isVegan(rs.getBoolean("is_vegan"))
            .isVegetararian(rs.getBoolean("is_vegetarian"))
            .taccFree(rs.getBoolean("tacc_free")).build();

    private final static RowMapper<RecipeIngredient> RECIPE_INGREDIENT_ROW_MAPPER = (rs, rowNum) -> {

        Ingredient ing = new Ingredient.Builder(rs.getInt("ingredient_id"),
                rs.getString("ingredient_name"),
                rs.getInt("serving"),
                rs.getString("serving_type"), rs.getInt("user_id"),
                rs.getString("ingredient_status"))

                .calories(rs.getInt("calorie_count"))
                .carbohydrates(rs.getInt("carbohydrate_count"))
                .isVegan(rs.getBoolean("is_vegan"))
                .isVegetararian(rs.getBoolean("is_vegetarian"))
                .taccFree(rs.getBoolean("tacc_free")).build();

        return new RecipeIngredient.Builder(ing, rs.getFloat("serving_amount"))
                .observation(rs.getString("obs")).build();
    };


    @Autowired
    public IngredientsDaoImpl(final DataSource ds) {

        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsertIng = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("ingredients")
                .usingGeneratedKeyColumns("ingredient_id");
        jdbcInsertRecIng = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("recipes_ingredients");
        jdbcInsertUserIng = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("user_ingredients");
    }


    @Override
    public Optional<RecipeIngredient> getUserIngById(int ingredientId, int userId) {
        final List<RecipeIngredient> list =
                jdbcTemplate.query("SELECT * FROM user_ingredients LEFT OUTER JOIN ingredients" +
                        " ON user_ingredients.ingredient_id = ingredients.ingredient_id" +
                        " WHERE user_ingredients.ingredient_id= ? AND user_ingredients.ui_status = 'REGULAR' AND" +
                        " user_ingredients.user_id = ?", RECIPE_INGREDIENT_ROW_MAPPER, ingredientId, userId);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }

    @Override
    public Optional<RecipeIngredient> getRecipeIngById(int ingredientId, int recipeId) {
        final List<RecipeIngredient> list =
                jdbcTemplate.query("SELECT * FROM recipes_ingredients LEFT OUTER JOIN ingredients" +
                        " ON recipes_ingredients.ingredient_id = ingredients.ingredient_id" +
                        " WHERE recipes_ingredients.ingredient_id= ? AND ri_status = 'REGULAR' AND" +
                        " recipes_ingredients.recipe_id = ?", RECIPE_INGREDIENT_ROW_MAPPER, ingredientId, recipeId);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }

    @Override
    public Optional<RecipeIngredient> getDeletedUserIngById(int ingredientId, int userId) {
        final List<RecipeIngredient> list =
                jdbcTemplate.query("SELECT * FROM user_ingredients LEFT OUTER JOIN ingredients" +
                        " ON user_ingredients.ingredient_id = ingredients.ingredient_id" +
                        " WHERE user_ingredients.ingredient_id= ? AND user_ingredients.ui_status = 'DELETED' AND" +
                        " user_ingredients.user_id = ?", RECIPE_INGREDIENT_ROW_MAPPER, ingredientId, userId);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }

    @Override
    public Optional<RecipeIngredient> getDeletedRecipeIngById(int ingredientId, int recipeId) {
        final List<RecipeIngredient> list =
                jdbcTemplate.query("SELECT * FROM recipes_ingredients LEFT OUTER JOIN ingredients" +
                        " ON recipes_ingredients.ingredient_id = ingredients.ingredient_id" +
                        " WHERE recipes_ingredients.ingredient_id= ? AND ri_status = 'DELETED' AND" +
                        " recipes_ingredients.recipe_id = ?", RECIPE_INGREDIENT_ROW_MAPPER, ingredientId, recipeId);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }


    @Override
    public Optional<Ingredient> getById(int id) {
        final List<Ingredient> list =
                jdbcTemplate.query("SELECT * FROM ingredients" +
                                " WHERE ingredient_id	= ? AND ingredient_status = 'REGULAR'",
                        INGREDIENT_ROW_MAPPER, id);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        final List<Ingredient> list =
                jdbcTemplate.query("SELECT * FROM ingredients WHERE ingredient_status = 'REGULAR'",
                        INGREDIENT_ROW_MAPPER);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return list;
    }

    @Override
    public Optional<Ingredient> getByIngredientName(String name) {
        final List<Ingredient> list =
                jdbcTemplate.query("SELECT * FROM ingredients" +
                                " WHERE  ingredient_name	= ? AND ingredient_status = 'REGULAR'",
                        INGREDIENT_ROW_MAPPER, name);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }


    @Override
    public List<RecipeIngredient> getByUserId(int id) {
        final List<RecipeIngredient> list =
                jdbcTemplate.query("SELECT * FROM (user_ingredients LEFT OUTER JOIN" +
                                " ingredients ON " +
                                "user_ingredients.ingredient_id = ingredients.ingredient_id) " +
                                "WHERE (user_ingredients.user_id= ?) AND (ui_status = 'REGULAR');",
                        RECIPE_INGREDIENT_ROW_MAPPER, id);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return list;
    }

    @Override
    public List<RecipeIngredient> getByRecipeId(int id) {
        final List<RecipeIngredient> list =
                jdbcTemplate.query("SELECT * FROM (recipes_ingredients LEFT OUTER JOIN" +
                                " ingredients ON " +
                                "recipes_ingredients.ingredient_id = ingredients.ingredient_id) " +
                                "WHERE (recipes_ingredients.recipe_id= ?) AND (ri_status = 'REGULAR');",
                        RECIPE_INGREDIENT_ROW_MAPPER, id);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return list;
    }

    @Override
    public Ingredient addNewIngredient(Ingredient ing) {
        final Map<String, Object> map = new HashMap<>();

        map.put("ingredient_name", ing.getName());
        map.put("serving", ing.getServing());
        map.put("user_id", ing.getUserId());

        map.put("serving_type", ing.getTypeOfServing());

        map.put("is_vegan", ing.isVegan());
        map.put("is_vegetarian", ing.isVegetarian());
        map.put("tacc_free", ing.isTaccFree());

        if (!(ing.getCalories() == -1))
            map.put("calorie_count", ing.getCalories());

        if (!(ing.getCarbohydrates() == -1))
            map.put("carbohydrate_count", ing.getCarbohydrates());

        if (!(ing.getProtein() == -1))
            map.put("protein_count", ing.getProtein());

        if (!(ing.getTotalFat() == -1))
            map.put("fat_count", ing.getTotalFat());

        map.put("ingredient_status", "REGULAR");

        if (!(ing.getSugar() == -1))
            map.put("sugar_count", ing.getSugar());

        final Number userId = jdbcInsertIng.executeAndReturnKey(map);

        ing.setId(userId.intValue());

        return ing;
    }

    @Override
    public RecipeIngredient addNewRecipeIngredient(int rec, RecipeIngredient recIng) {
        final Map<String, Object> map = new HashMap<>();

        map.put("ingredient_id", recIng.getIngredient().getId());
        map.put("recipe_id", rec);
        map.put("serving_amount", recIng.getAmount());
        map.put("ri_status", "REGULAR");

        if (recIng.getObservation() != null && !recIng.getObservation().equals(""))
            map.put("obs", recIng.getObservation());

        jdbcInsertRecIng.execute(map);

        return recIng;

    }

    @Override
    public RecipeIngredient addNewUserIngredient(int userId, RecipeIngredient recipeIngredient) {
        final Map<String, Object> map = new HashMap<>();

        if (recipeIngredient.getObservation() != null && !recipeIngredient.getObservation().equals(""))
            map.put("obs", recipeIngredient.getObservation());

        map.put("ui_status", "REGULAR");
        map.put("ingredient_id", recipeIngredient.getIngredient().getId());
        map.put("user_id", userId);
        map.put("serving_amount", recipeIngredient.getAmount());

        jdbcInsertUserIng.execute(map);

        return recipeIngredient;
    }

    @Override
    public void updateIngredient(Ingredient ingredient, Map<String, Object> changes) {
        changes.forEach((k, v) -> update(ingredient, k, v));
    }

    private void update(Ingredient ingredient, String k, Object v) {
        if (!k.equals("ingredient_name")) {
            jdbcTemplate.update("UPDATE ingredients SET ingredient_name = ? WHERE ingredient_id = ?", v, ingredient.getId());
        }
        if (!k.equals("ingredient_status")) {
            jdbcTemplate.update("UPDATE ingredients SET ingredient_status = ? WHERE ingredient_id = ?", v, ingredient.getId());
        }
        if (!k.equals("is_vegetarian")) {
            jdbcTemplate.update("UPDATE ingredients SET is_vegetarian = ? WHERE ingredient_id = ?", v, ingredient.getId());
        }
        if (!k.equals("is_vegan")) {
            jdbcTemplate.update("UPDATE ingredients SET is_vegan = ? WHERE ingredient_id = ?", v, ingredient.getId());
        }
        if (!k.equals("tacc_free")) {
            jdbcTemplate.update("UPDATE ingredients SET tacc_free = ? WHERE ingredient_id = ?", v, ingredient.getId());
        }
        if (!k.equals("protein_count")) {
            jdbcTemplate.update("UPDATE ingredients SET protein_count = ? WHERE ingredient_id = ?", v, ingredient.getId());
        }
        if (!k.equals("calorie_count")) {
            jdbcTemplate.update("UPDATE ingredients SET calorie_count = ? WHERE ingredient_id = ?", v, ingredient.getId());
        }
        if (!k.equals("carbohydrate_count")) {
            jdbcTemplate.update("UPDATE ingredients SET carbohydrate_count = ? WHERE ingredient_id = ?", v, ingredient.getId());
        }
        if (!k.equals("fat_count")) {
            jdbcTemplate.update("UPDATE ingredients SET fat_count = ? WHERE ingredient_id = ?", v, ingredient.getId());
        }
        if (!k.equals("sugar_count")) {
            jdbcTemplate.update("UPDATE ingredients SET sugar_count = ? WHERE ingredient_id = ?", v, ingredient.getId());
        }
        if (!k.equals("serving_type")) {
            jdbcTemplate.update("UPDATE ingredients SET serving_type = ? WHERE ingredient_id = ?", v, ingredient.getId());
        }
        if (!k.equals("serving")) {
            jdbcTemplate.update("UPDATE ingredients SET serving = ? WHERE ingredient_id = ?", v, ingredient.getId());
        }


    }

    @Override
    public void updateRecipeIngredient(int ingredient, Map<String, Object> changes, int recipe) {
        changes.forEach((k, v) -> updateRIR(ingredient, k, v, recipe));
    }

    @Override
    public void updateUserIngredient(int ingredient, Map<String, Object> changes, int userId) {
        changes.forEach((k, v) -> updateRIU(ingredient, k, v, userId));

    }

    private void updateRIR(int ingredient, String k, Object v, int recipe) {
        if (k.equals("serving_amount")) {
            jdbcTemplate.update("UPDATE recipes_ingredients SET serving_amount = ? WHERE ingredient_id = ? AND recipe_id = ?", v, ingredient, recipe);
        }
        if (k.equals("ri_status")) {
            jdbcTemplate.update("UPDATE recipes_ingredients SET ri_status = ? WHERE ingredient_id = ? AND recipe_id = ?", v, ingredient, recipe);
        }
        if (k.equals("obs")) {
            jdbcTemplate.update("UPDATE recipes_ingredients SET obs = ? WHERE ingredient_id = ? AND recipe_id = ?", v, ingredient, recipe);
        }
    }

    private void updateRIU(int ingredient, String k, Object v, int userId) {
        if (k.equals("serving_amount")) {
            jdbcTemplate.update("UPDATE user_ingredients SET serving_amount = ? WHERE ingredient_id = ? AND user_id = ?", v, ingredient, userId);
        }
        if (k.equals("ui_status")) {
            jdbcTemplate.update("UPDATE user_ingredients SET ui_status = ? WHERE ingredient_id = ? AND user_id = ?", v, ingredient, userId);
        }
        if (k.equals("obs")) {
            jdbcTemplate.update("UPDATE user_ingredients SET obs = ? WHERE ingredient_id = ? AND user_id = ?", v, ingredient, userId);
        }
    }


}
