package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.RecipeDao;
import ar.edu.itba.paw.model.Recipe;
import ar.edu.itba.paw.model.RecipeTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.*;

@Repository
public class RecipeDaoImpl implements RecipeDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsertRecipe;
    private SimpleJdbcInsert jdbcInsertTag;

    private final static RowMapper<Recipe> ROW_MAPPER = (rs, rowNum) ->
            new Recipe.Builder(
                    rs.getInt("recipe_id"),
                    rs.getString("recipe_name"),
            null,
                    rs.getString("instructions"),
                    rs.getInt("user_id"))
            .description(rs.getString("description"))
            //        .image(rs.getBytes("image"))
                    .build();

    private final static RowMapper<RecipeTag> TAG_ROW_MAPPER = (rs, rowNum) ->
            new RecipeTag(rs.getString("tag"),rs.getInt("recipe_id"));




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
        final List<Recipe> list = jdbcTemplate.query("SELECT	*	FROM recipes WHERE   recipe_id	=	? AND recipe_status = 'REGULAR'", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }

    @Override
    public Optional<Recipe> getByName(String name) {
        final List<Recipe> list = jdbcTemplate.query("SELECT	*	FROM recipes WHERE   name	=	? AND recipe_status = 'REGULAR'", ROW_MAPPER, name);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }

    @Override
    public List<Recipe> getByUserId(int id) {
        final List<Recipe> list = jdbcTemplate.query("SELECT	*	FROM recipes WHERE   user_id	=	? AND recipe_status = 'REGULAR'", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return list;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        final List<Recipe> list = jdbcTemplate.query("SELECT	*	FROM recipes WHERE recipe_status = 'REGULAR'", ROW_MAPPER);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return list;
    }

    @Override
    public List<Recipe> getAllRecipesOrderedByRating() {
        final List<Recipe> list =
                jdbcTemplate.query("SELECT	*	FROM recipes WHERE recipe_status = 'REGULAR' ORDER BY rating", ROW_MAPPER);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return list;
    }

    //TODO Probar una vez que el cambio lo termine
    @Override
    public List<Recipe> getAllRecipesOrderedByDate() {
        final List<Recipe> list =
                jdbcTemplate.query("SELECT	*	FROM recipes WHERE recipe_status = 'REGULAR' ORDER BY date_created", ROW_MAPPER);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return list;
    }

    @Override
    public Recipe addNewRecipe(Recipe recipe) {
        final Map<String, Object> map = new HashMap<>();

        map.put("recipe_name",recipe.getName());
        map.put("instructions",recipe.getInstructions());
        map.put("user_id",recipe.getUserId());
        map.put("recipe_status","REGULAR");

        if(!recipe.getDescription().isEmpty())
            map.put("description",recipe.getDescription());

        if (recipe.getImage() != null) {
            map.put("image", recipe.getImage());
        }

        Date date= new Date();
        long time = date. getTime();
        map.put("date_created", new Timestamp(time));

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
            jdbcTemplate.update("UPDATE recipes SET recipe_name = ? WHERE recipe_id = ?",v,recipe.getId());
        }
        if(k.equals("instructions")){
            jdbcTemplate.update("UPDATE recipes SET instructions = ? WHERE recipe_id = ?",v,recipe.getId());
        }
        if(k.equals("status")){
            jdbcTemplate.update("UPDATE recipes SET recipe_status = ? WHERE recipe_id = ?",v,recipe.getId());
        }
        if(k.equals("image")){
            jdbcTemplate.update("UPDATE recipes SET image = ? WHERE recipe_id = ?",v,recipe.getId());
        }
        if(k.equals("description")){
            jdbcTemplate.update("UPDATE recipes SET description = ? WHERE recipe_id = ?",v,recipe.getId());
        }

    }

    @Override
    public List<RecipeTag> getAllTags() {
        final List<RecipeTag> list = jdbcTemplate.query("SELECT  *	FROM recipe_tags", TAG_ROW_MAPPER);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return list;
    }

    @Override
    public List<RecipeTag> getAllRecipeTags(Recipe recipe) {

        final List<RecipeTag> list = jdbcTemplate
                .query("SELECT	 *	FROM recipe_tags WHERE   recipe_id	=	? AND tags_status = 'REGULAR'",
                        TAG_ROW_MAPPER, recipe.getId());
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        recipe.setTags(list);

        return list;
    }

    @Override
    public void removeTagFromRecipe(RecipeTag tag) {
        jdbcTemplate.update("UPDATE recipe_tags SET tags_status = 'DELETED' WHERE recipe_id = ? AND tag = ?",
                tag.getRecipeId(),tag.getTag());
    }

    @Override
    public void addNewRecipeTag(RecipeTag tag) {
        final Map<String, Object> map = new HashMap<>();

        map.put("recipe_id",tag.getRecipeId());
        map.put("tag_id",tag.getTag());
        map.put("tags_status","REGULAR");

        jdbcInsertTag.execute(map);
    }

    @Override
    public List<Recipe> getAllRecipesByUserId(int userId){
        final List<Recipe> list = jdbcTemplate.query(
                "SELECT	*	FROM recipes WHERE user_id = ? AND recipe_status = 'REGULAR'",
                ROW_MAPPER, userId);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return list;
    }

}
