package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.IngredientsDao;
import ar.edu.itba.paw.model.Ingredient;
import ar.edu.itba.paw.model.Recipe;
import ar.edu.itba.paw.model.RecipeIngredient;
import ar.edu.itba.paw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class IngredientsDaoImpl implements IngredientsDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsertIng;
    private SimpleJdbcInsert jdbcInsertRecIng;
    private SimpleJdbcInsert jdbcInsertUserIng;


    private final static RowMapper<Ingredient> INGREDIENT_ROW_MAPPER = (rs, rowNum) -> new Ingredient.Builder(rs.getInt("ingredient_id"),
            rs.getString("name"),
            rs.getInt("serving"),
            rs.getString("name"),rs.getInt("user_id"),rs.getInt("status"))
            .calories(rs.getInt("calorie_count"))
            .carbohydrates(rs.getInt("carbohydrate_count"))
            .isVegan(rs.getInt("is_vegan") == 1)
            .isVegetararian(rs.getInt("is_vegetarian") == 1)
            .taccFree(rs.getInt("tacc_free") == 1).build();

    private final static RowMapper<RecipeIngredient> RECIPE_INGREDIENT_ROW_MAPPER = (rs, rowNum) -> {

        Ingredient ing = new Ingredient.Builder(rs.getInt("ingredient_id"),
                rs.getString("ingredientName"),
                rs.getInt("serving"),
                rs.getString("servingName"),rs.getInt("user_id"),rs.getInt("status"))
                .calories(rs.getInt("calorie_count"))
                .carbohydrates(rs.getInt("carbohydrate_count"))
                .isVegan(rs.getInt("is_vegan") == 1)
                .isVegetararian(rs.getInt("is_vegetarian") == 1)
                .taccFree(rs.getInt("tacc_free") == 1).build();

        return new RecipeIngredient.Builder(ing,rs.getInt("serving_amount"))
                .observation(rs.getString("obs")).build();
    };

    private final static RowMapper<Servings> INGREDIENT_SERVING_ROW_MAPPER = (rs,rowNum) ->
            new Servings (rs.getInt("serving_type_id"),rs.getString("name"));

    private static class Servings {
        int id;
        String name;

        Servings(int serving_type_id, String name) {
            this.id = serving_type_id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }



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
                jdbcTemplate.query("SELECT * FROM (user_ingredients LEFT OUTER JOIN" +
                                " (SELECT ingredient_id,ingredients.name as ingredientName,is_vegetarian,is_vegan," +
                                "tacc_free,protein_count,calorie_count,carbohydrate_count,fat_count,sugar_count," +
                                "serving,status,user_id, serving_types.name as servingName" +
                                " FROM ingredients left outer join " +
                                "serving_types on ingredients.serving_type_id = " +
                                "serving_types.serving_type_id where status = 1) AS foo ON  " +
                                "user_ingredients.ingredient_id = foo.ingredient_id) " +
                                " WHERE ingredient_id	= ? AND user_id = ? AND status = 1",
                        RECIPE_INGREDIENT_ROW_MAPPER, ingredientId,userId);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }

    @Override
    public Optional<Ingredient> getById(int id) {
        final List<Ingredient> list =
                jdbcTemplate.query("SELECT * FROM (ingredients LEFT OUTER JOIN serving_types ON " +
                                " ingredients.serving_type_id = serving_types.serving_type_id)" +
                                " WHERE ingredient_id	= ? AND status = 1",
                        INGREDIENT_ROW_MAPPER, id);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }

    @Override
    public Optional<List<Ingredient>> getAllIngredients() {
        final List<Ingredient> list =
                jdbcTemplate.query("SELECT * FROM (ingredients LEFT OUTER JOIN serving_types ON" +
                                " ingredients.serving_type_id = serving_types.serving_type_id)" +
                                " WHERE status = 1",
                        INGREDIENT_ROW_MAPPER);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list);
    }

    @Override
    public Optional<Ingredient> getByIngredientName(String name) {
        final List<Ingredient> list =
                jdbcTemplate.query("SELECT * FROM (ingredients " +
                                "LEFT OUTER JOIN serving_types ON ingredients.serving_type_id = serving_types.serving_type_id)"+
                                " WHERE  ingredients.name	= ? AND status != 0",
                        INGREDIENT_ROW_MAPPER, name);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }


    @Override
    public Optional<List<RecipeIngredient>> getByUserId(int id) {
        final List<RecipeIngredient> list =
                jdbcTemplate.query("SELECT * FROM (user_ingredients LEFT OUTER JOIN" +
                                " (SELECT ingredient_id,ingredients.name as ingredientName,is_vegetarian,is_vegan," +
                                "tacc_free,protein_count,calorie_count,carbohydrate_count,fat_count,sugar_count," +
                                "serving,status,user_id, serving_types.name as servingName" +
                                " FROM ingredients left outer join " +
                                "serving_types on ingredients.serving_type_id = " +
                                "serving_types.serving_type_id where status = 1) AS foo ON  " +
                                "user_ingredients.ingredient_id = foo.ingredient_id) " +
                                "WHERE (user_ingredients.user_id= ?) AND (user_ingredients.status != 0);",
                        RECIPE_INGREDIENT_ROW_MAPPER, id);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list);
    }

    @Override
    public Optional<List<RecipeIngredient>> getByRecipeId(int id) {
        final List<RecipeIngredient> list =
                jdbcTemplate.query("SELECT * FROM (recipes_ingredients LEFT OUTER JOIN" +
                                " (SELECT ingredient_id,ingredients.name as ingredientName,is_vegetarian,is_vegan," +
                                "tacc_free,protein_count,calorie_count,carbohydrate_count,fat_count,sugar_count," +
                                "serving,status,user_id, serving_types.name as servingName" +
                                " FROM ingredients left outer join " +
                                "serving_types on ingredients.serving_type_id = " +
                                "serving_types.serving_type_id where status = 1) AS foo ON  " +
                                "recipes_ingredients.ingredient_id = foo.ingredient_id) " +
                                "WHERE (recipes_ingredients.user_id= ?) AND (recipes_ingredients.status != 0);",
                        RECIPE_INGREDIENT_ROW_MAPPER, id);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list);
    }

    @Override
    public Ingredient addNewIngredient(Ingredient ing) {
        final Map<String, Object> map = new HashMap<>();

        map.put("name",ing.getName());
        map.put("serving", ing.getServing());
        map.put("user_id",ing.getUserId());

        map.put("serving_type_id",getServingTypeId(ing.getTypeOfServing()));

        map.put("is_vegan",ing.isVegan()?1:0);
        map.put("is_vegetarian",ing.isVegetarian()?1:0);
        map.put("tacc_free",ing.isTaccFree()?1:0);

        if(!(ing.getCalories() == 0))
            map.put("calorie_count", ing.getCalories());

        if(!(ing.getCarbohydrates() == 0))
            map.put("carbohydrate_count", ing.getCarbohydrates());

        if(!(ing.getProtein() == 0))
            map.put("protein_count", ing.getProtein());

        if(!(ing.getTotalFat() == 0))
            map.put("fat_count", ing.getTotalFat());

        if(!(ing.getStatus() == 0))
            map.put("status",ing.getStatus());

        if(!(ing.getSugar() == 0))
            map.put("sugar_count", ing.getSugar());

        final Number userId = jdbcInsertIng.executeAndReturnKey(map);

        ing.setId(userId.intValue());

        return ing;
    }

    private int getServingTypeId(String typeOfServing) {
            List<Servings> list = jdbcTemplate.query("SELECT * FROM serving_types WHERE name = ?",
                    INGREDIENT_SERVING_ROW_MAPPER, typeOfServing);

        if (list.isEmpty()) {
            return -1;
        }

        return list.get(0).getId();
    }

    @Override
    public RecipeIngredient addNewRecipeIngredient(Recipe rec, RecipeIngredient recIng) {
        final Map<String, Object> map = new HashMap<>();

        map.put("ingredient_id",recIng.getIngredient().getId());
        map.put("recipe_id",rec.getId());
        map.put("serving_amount",recIng.getAmount());

        if(recIng.getObservation() != null && !recIng.getObservation().equals(""))
            map.put("obs",recIng.getObservation());

        jdbcInsertRecIng.execute(map);

        return recIng;

    }

    @Override
    public RecipeIngredient addNewUserIngredient(User user, RecipeIngredient recipeIngredient) {
        final Map<String, Object> map = new HashMap<>();

        if(recipeIngredient.getObservation() != null && !recipeIngredient.getObservation().equals(""))
            map.put("obs",recipeIngredient.getObservation());

        map.put("status",1);
        map.put("ingredient_id",recipeIngredient.getIngredient().getId());
        map.put("user_id",user.getId());
        map.put("serving_amount",recipeIngredient.getAmount());

        jdbcInsertUserIng.execute(map);

        return recipeIngredient;
    }

    @Override
    public void updateIngredient(Ingredient ingredient, Map<String, Object> changes) {
        changes.forEach((k, v) -> update(ingredient, k, v));
    }

    private void update(Ingredient ingredient, String k, Object v) {
        jdbcTemplate.update("UPDATE ingredients SET ? = ? WHERE ingredient_id = ?",k,v,ingredient.getId());
    }

    @Override
    public void updateRecipeIngredient(RecipeIngredient ingredient, Map<String, Object> changes, Recipe recipe) {
            changes.forEach((k, v) -> updateRIR(ingredient, k, v,recipe));
    }

    @Override
    public void updateUserIngredient(RecipeIngredient ingredient, Map<String, Object> changes, User user) {
        changes.forEach((k, v) -> updateRIU(ingredient, k, v,user));

    }

    private void updateRIR(RecipeIngredient ingredient, String k, Object v, Recipe recipe) {
        jdbcTemplate.update("UPDATE recipes_ingredients SET ? = ? WHERE recipe_id = ? AND ingredient_id = ?"
                ,k,v,recipe.getId(),ingredient.getIngredient().getId());
    }

    private void updateRIU(RecipeIngredient ingredient, String k, Object v, User user) {
        jdbcTemplate.update("UPDATE user_ingredients SET ? = ? WHERE ingredient_id = ? AND user_id = ?",
                k,v,ingredient.getIngredient().getId(),user.getId());
    }


}
