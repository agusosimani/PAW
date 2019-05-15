package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.RecipeDao;
import ar.edu.itba.paw.model.Rating;
import ar.edu.itba.paw.model.Recipe;
import ar.edu.itba.paw.model.RecipeTag;
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
public class RecipeDaoImpl implements RecipeDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsertRecipe;
    private SimpleJdbcInsert jdbcInsertTag;

    private final static RowMapper<Recipe> ROW_MAPPER = (rs, rowNum) ->
            new Recipe.Builder(
                    rs.getInt("recipe_id"),
                    rs.getString("name"),
            null,
                    rs.getString("instructions"),
                    rs.getInt("user_id"))
            .description(rs.getString("description"))
            //        .image(rs.getBytes("image"))
                    .build();

    private final static RowMapper<RecipeTag> TAG_ROW_MAPPER = (rs, rowNum) ->
            new RecipeTag(rs.getInt("tag_id"),rs.getString("name"));




    @Autowired
    public RecipeDaoImpl(final DataSource ds) {

        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsertRecipe = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("recipes")
                .usingGeneratedKeyColumns("recipe_id");
        jdbcInsertTag = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("recipe_tags");
    }


    @Override
    public Optional<Recipe> getById(int id) {
        final List<Recipe> list = jdbcTemplate.query("SELECT	*	FROM recipes WHERE   recipe_id	=	? AND status != 0", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }

    @Override
    public Optional<Recipe> getByName(String name) {
        final List<Recipe> list = jdbcTemplate.query("SELECT	*	FROM recipes WHERE   name	=	? AND status != 0", ROW_MAPPER, name);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }

    @Override
    public Optional<List<Recipe>> getByUserId(int id) {
        final List<Recipe> list = jdbcTemplate.query("SELECT	*	FROM recipes WHERE   user_id	=	? AND status != 0", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list);
    }

    @Override
    public Optional<List<Recipe>> getAllRecipes() {
        final List<Recipe> list = jdbcTemplate.query("SELECT	*	FROM recipes WHERE status != 0", ROW_MAPPER);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list);
    }

    @Override
    public Optional<List<Recipe>> getAllRecipesOrderedByRating() {
        final List<Recipe> list =
                jdbcTemplate.query("SELECT	*	FROM recipes WHERE status != 0 ORDER BY rating", ROW_MAPPER);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list);
    }

    @Override
    public Recipe addNewRecipe(Recipe recipe) {
        final Map<String, Object> map = new HashMap<>();

        map.put("name",recipe.getName());
        map.put("instructions",recipe.getInstructions());
        map.put("user_id",recipe.getUserId());
        map.put("status",1);

        if(!recipe.getDescription().isEmpty() && !recipe.getDescription().equals(""))
            map.put("description",recipe.getDescription());

        if (recipe.getImage() != null) {
            map.put("image", recipe.getImage());
        }

        final Number recipeId = jdbcInsertRecipe.executeAndReturnKey(map);

        recipe.setId(recipeId.intValue());
        return recipe;
    }

    @Override
    public void update(Recipe recipe, Map<String, Object> changes) {
        changes.forEach((k, v) -> update(recipe, k, v));
    }

    private void update(Recipe recipe, String k, Object v) {
        if(k.equals("name")){
            jdbcTemplate.update("UPDATE recipes SET name = ? WHERE recipe_id = ?",v,recipe.getId());
        }
        if(k.equals("instructions")){
            jdbcTemplate.update("UPDATE recipes SET instructions = ? WHERE recipe_id = ?",v,recipe.getId());
        }
        if(k.equals("status")){
            jdbcTemplate.update("UPDATE recipes SET status = ? WHERE recipe_id = ?",v,recipe.getId());
        }
        if(k.equals("image")){
            jdbcTemplate.update("UPDATE recipes SET image = ? WHERE recipe_id = ?",v,recipe.getId());
        }
        if(k.equals("description")){
            jdbcTemplate.update("UPDATE recipes SET description = ? WHERE recipe_id = ?",v,recipe.getId());
        }

    }

    @Override
    public Optional<RecipeTag> getTagByName(String name) {
        final List<RecipeTag> list = jdbcTemplate.query("SELECT	*	FROM tags WHERE   name	=	? AND status != 0", TAG_ROW_MAPPER, name);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }

    @Override
    public Optional<List<RecipeTag>> getAllRecipeTags(Recipe recipe) {

        final List<RecipeTag> list = jdbcTemplate
                .query("SELECT	 *	FROM (recipe_tags LEFT OUTER JOIN tags ON " +
                                "tags.tag_id = recipe_tags.tag_id) WHERE   recipe_id	=	? AND status != 0",
                        TAG_ROW_MAPPER, recipe.getId());
        if (list.isEmpty()) {
            return Optional.empty();
        }
        recipe.setTags(list);

        return Optional.of(list);
    }

    @Override
    public void removeTagFromRecipe(Recipe recipe, RecipeTag tag) {
        jdbcTemplate.update("UPDATE recipe_tags SET status = 0 WHERE recipe_id = ? AND tag_id = ?",
                recipe.getId(),tag.getId());
    }

    @Override
    public void addNewRecipeTag(Recipe recipe, RecipeTag tag) {
        final Map<String, Object> map = new HashMap<>();

        map.put("recipe_id",recipe.getId());
        map.put("tag_id",tag.getId());

        jdbcInsertTag.execute(map);
    }

    @Override
    public Optional<List<Recipe>> getAllRecipesByUserId(int userId){
        final List<Recipe> list = jdbcTemplate.query(
                "SELECT	*	FROM recipes WHERE user_id = ? AND status != 0",
                ROW_MAPPER, userId);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list);
    }

}
