package ar.edu.itba.paw2019a2.persistence;

import ar.edu.itba.paw2019a2.interfaces.dao.RecipeDao;
import ar.edu.itba.paw2019a2.model.Enum.Order;
import ar.edu.itba.paw2019a2.model.Enum.Status;
import ar.edu.itba.paw2019a2.model.Enum.Tag;
import ar.edu.itba.paw2019a2.model.Recipe;
import ar.edu.itba.paw2019a2.model.RecipeList;
import ar.edu.itba.paw2019a2.model.RecipeTag;
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
    private SimpleJdbcInsert jdbcInsertUserList;
    private SimpleJdbcInsert jdbcInsertRecipeList;
    private SimpleJdbcInsert jdbcInsertRecentlyCooked;

    private final static RowMapper<Recipe> ROW_MAPPER = (rs, rowNum) ->
            new Recipe.Builder(
                    rs.getInt("recipe_id"),
                    rs.getString("recipe_name"),
                    null,
                    rs.getString("instructions"),
                    rs.getInt("user_id"))
                    .description(rs.getString("description"))
                    .image(rs.getBytes("image"))
                    .rating(rs.getFloat("rating"))
                    .build();

    private final static RowMapper<RecipeTag> TAG_ROW_MAPPER = (rs, rowNum) ->
            new RecipeTag(rs.getString("tag"), rs.getInt("recipe_id"));

    private final static RowMapper<RecipeList> LIST_ROW_MAPPER = (rs, rowNum) ->
            new RecipeList(rs.getInt("recipe_list_id"), rs.getString("list_name"));


    @Autowired
    public RecipeDaoImpl(final DataSource ds) {

        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsertRecipe = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("recipes")
                .usingGeneratedKeyColumns("recipe_id");
        jdbcInsertTag = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("recipe_tags");
        jdbcInsertUserList = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("user_recipe_list")
                .usingGeneratedKeyColumns("recipe_list_id");
        jdbcInsertRecipeList = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("recipe_list");
        jdbcInsertRecentlyCooked = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("recently_cooked")
                .usingGeneratedKeyColumns("rc_id");
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
        final List<Recipe> list = jdbcTemplate.query("SELECT	*	FROM recipes WHERE   recipe_name	=	? AND recipe_status = 'REGULAR'", ROW_MAPPER, name);
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
    public List<Recipe> getAllRecipes(String search) {
        String searchAux = '%' +
                search +
                '%';
        final List<Recipe> list = jdbcTemplate.query("SELECT	*	FROM recipes" +
                " WHERE recipe_status =" +
                " 'REGULAR' AND recipe_name LIKE ? ORDER BY recipe_name", ROW_MAPPER, searchAux);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return list;
    }

    @Override
    public List<Recipe> getAllRecipesOrderedByRating(String search) {

        String searchAux = '%' +
                search +
                '%';

        final List<Recipe> list =
                jdbcTemplate.query("SELECT	*	FROM recipes WHERE recipe_status = 'REGULAR' AND recipe_name LIKE ? ORDER BY rating DESC, recipe_name", ROW_MAPPER, searchAux);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return list;
    }

    @Override
    public List<Recipe> getAllRecipesOrderedByDateNew(String search) {

        String searchAux = '%' +
                search +
                '%';

        final List<Recipe> list =
                jdbcTemplate.query("SELECT	*	FROM recipes WHERE recipe_status = 'REGULAR' AND recipe_name LIKE ? ORDER BY date_created DESC, recipe_name", ROW_MAPPER,searchAux);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return list;
    }

    @Override
    public List<Recipe> getAllRecipesOrderByRising(String search) {

        String searchAux = '%' +
                search +
                '%';

        final List<Recipe> list =
                jdbcTemplate.query("SELECT	*	FROM recipes " +
                        "WHERE recipe_status = 'REGULAR' " +
                        "AND rating >= 4 AND recipe_name LIKE ? ORDER BY date_created DESC, recipe_name", ROW_MAPPER,searchAux);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<Recipe> getAllRecipesOrderedByDateOld(String search) {

        String searchAux = '%' +
                search +
                '%';

        final List<Recipe> list =
                jdbcTemplate.query("SELECT	*	FROM recipes WHERE " +
                        "recipe_status = 'REGULAR' AND recipe_name LIKE ? ORDER BY date_created, recipe_name", ROW_MAPPER,searchAux);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return list;
    }

    @Override
    public boolean isTagDeleted(String tag, int id) {
        final List<Recipe> list =
                jdbcTemplate.query("SELECT	*	FROM recipe_tags " +
                        "WHERE tags_status = 'DELETED'" +
                        "AND tag = ? AND recipe_id = ?", ROW_MAPPER,tag,id);
        return !list.isEmpty();
    }

    @Override
    public void changeTagStatus(String tag, int id, Status status) {
        if(status.name().equals("REGULAR"))
            jdbcTemplate.update("UPDATE recipe_tags SET tags_status = 'REGULAR' WHERE recipe_id = ? AND tag = ?",
                    id, tag);
        else
            jdbcTemplate.update("UPDATE recipe_tags SET tags_status = 'DELETED' WHERE recipe_id = ? AND tag = ?",
                    id, tag);
    }

    @Override
    public List<Recipe> getRecipesWithTagAndOrder(Order order, List<String> tags, String search) {

        if(search == null)
            search = "";


        if (tags.size() == 0) {
            return this.getAllRecipesOnOrder(order,search);
        }

        List<String>list = new ArrayList<>();

        for (String tag:tags) {
            for (Tag c : Tag.values()) {
                if (c.name().equals(tag)) {
                    list.add(tag);
                }
            }
        }

        return jdbcTemplate.query(queryBuilder(order,list,search),ROW_MAPPER);

    }

    private String queryBuilder(Order order, List<String> tags,String search) {

        StringBuilder sb = new StringBuilder();

        if (order == null) {
            sb.append("SELECT	*	FROM recipe_tags LEFT OUTER JOIN recipes " +
                    "ON (recipe_tags.recipe_id = recipes.recipe_id) WHERE (recipe_tags.tag in(");
            queryAppender(tags, sb);
            sb.append(")) AND recipe_status = 'REGULAR' AND recipe_name LIKE \'%");
            sb.append(search);
            sb.append("%\' ORDER BY recipe_name");

        }
        else if (order.equals(Order.Rising)) {
            sb.append("SELECT	 *	FROM recipe_tags LEFT OUTER JOIN recipes " +
                    "ON (recipe_tags.recipe_id = recipes.recipe_id)" +
                    " WHERE (recipe_tags.tag in(");
            queryAppender(tags, sb);
            sb.append(")) AND recipes.rating >= 4 AND recipe_status = 'REGULAR' AND recipe_name LIKE \'%");
            sb.append(search);
            sb.append("%\' ORDER BY recipes.date_created DESC, recipe_name");

        } else if (order.equals(Order.TopRated)) {
            sb.append("SELECT	 *	FROM recipe_tags LEFT OUTER JOIN recipes ON (recipe_tags.recipe_id = recipes.recipe_id) WHERE (recipe_tags.tag in(");
            queryAppender(tags, sb);
            sb.append("))  AND recipe_status = 'REGULAR' AND recipe_name LIKE \'%");
            sb.append(search);
            sb.append("%\' ORDER BY recipes.rating DESC, recipe_name");


        } else if (order.equals(Order.New)) {

            sb.append("SELECT	 *	FROM recipe_tags LEFT OUTER JOIN recipes " +
                    "ON (recipe_tags.recipe_id = recipes.recipe_id)" +
                    " WHERE (recipe_tags.tag in(");
            queryAppender(tags, sb);
            sb.append("))  AND recipe_status = 'REGULAR' AND recipe_name LIKE \'%")
                    .append(search)
                    .append("%\' ORDER BY recipes.date_created DESC,recipe_name");

        } else if (order.equals(Order.Old)) {

            sb.append("SELECT	 *	FROM recipe_tags LEFT OUTER JOIN recipes " +
                    "ON (recipe_tags.recipe_id = recipes.recipe_id)" +
                    " WHERE (recipe_tags.tag in(");
            queryAppender(tags, sb);
            sb.append("))  AND recipe_status = 'REGULAR' AND recipe_name LIKE \'%")
                    .append(search)
                    .append("%\' ORDER BY recipes.date_created,recipe_name");

        } else {

            sb.append("SELECT	*	FROM recipe_tags LEFT OUTER JOIN recipes " +
                    "ON (recipe_tags.recipe_id = recipes.recipe_id) WHERE (recipe_tags.tag in(");
            queryAppender(tags, sb);
            sb.append(")) AND recipe_status = 'REGULAR' AND recipe_name LIKE \'%")
                    .append(search)
                    .append("%\' ORDER BY recipe_name");

        }
        return sb.toString();
    }

    private void queryAppender(List<String> tags, StringBuilder sb) {
        for (int i = 0; i < tags.size(); i++) {
            sb.append("\'");
            sb.append(tags.get(i));
            sb.append("\'");
            if (i < tags.size() - 1)
                sb.append(',');
        }
    }

    private List<Recipe> getAllRecipesOnOrder(Order order, String search) {

        if (order == null) {
            return this.getAllRecipes(search);
        }

        if (order.equals(Order.Rising)) {
            return this.getAllRecipesOrderByRising(search);
        }
        if (order.equals(Order.TopRated)) {
            return this.getAllRecipesOrderedByRating(search);
        }
        if (order.equals(Order.New)) {
            return this.getAllRecipesOrderedByDateNew(search);
        }
        if (order.equals(Order.Old)) {
            return this.getAllRecipesOrderedByDateOld(search);
        } else {
            return this.getAllRecipes(search);
        }
    }

    @Override
    public Recipe addNewRecipe(Recipe recipe) {
        final Map<String, Object> map = new HashMap<>();

        map.put("recipe_name", recipe.getName());
        map.put("instructions", recipe.getInstructions());
        map.put("user_id", recipe.getUserId());
        map.put("recipe_status", "REGULAR");

        if (!recipe.getDescription().isEmpty())
            map.put("description", recipe.getDescription());

        if (recipe.getImage() != null) {
            map.put("image", recipe.getImage());
        }

        Date date = new Date();
        long time = date.getTime();
        map.put("date_created", new Timestamp(time));

        map.put("rating", recipe.getRating());

        final Number recipeId = jdbcInsertRecipe.executeAndReturnKey(map);

        recipe.setId(recipeId.intValue());
        return recipe;
    }

    @Override
    public void update(int recipe, Map<String, Object> changes) {
        changes.forEach((k, v) -> update(recipe, k, v));
    }

    private void update(int recipe, String k, Object v) {
        if (k.equals("recipe_name")) {
            jdbcTemplate.update("UPDATE recipes SET recipe_name = ? WHERE recipe_id = ?", v, recipe);
        }
        if (k.equals("instructions")) {
            jdbcTemplate.update("UPDATE recipes SET instructions = ? WHERE recipe_id = ?", v, recipe);
        }
        if (k.equals("recipe_status")) {
            jdbcTemplate.update("UPDATE recipes SET recipe_status = ? WHERE recipe_id = ?", v, recipe);
        }
        if (k.equals("image")) {
            jdbcTemplate.update("UPDATE recipes SET image = ? WHERE recipe_id = ?", v, recipe);
        }
        if (k.equals("description")) {
            jdbcTemplate.update("UPDATE recipes SET description = ? WHERE recipe_id = ?", v, recipe);
        }
        if (k.equals("rating")) {
            jdbcTemplate.update("UPDATE recipes SET rating = ? WHERE recipe_id = ?", v, recipe);
        }
    }

    @Override
    public List<Recipe> getAllRecipesByUserId(int userId) {
        final List<Recipe> list = jdbcTemplate.query(
                "SELECT	*	FROM recipes WHERE user_id = ? AND recipe_status = 'REGULAR'",
                ROW_MAPPER, userId);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return list;
    }

    //TAGS

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
        List<String> tagList = new ArrayList<>();
        for (RecipeTag rt : list) {
            tagList.add(rt.getTag());
        }

        recipe.setTags(tagList);

        return list;
    }

    @Override
    public void removeTagFromRecipe(RecipeTag tag) {
        jdbcTemplate.update("UPDATE recipe_tags SET tags_status = 'DELETED' WHERE recipe_id = ? AND tag = ?",
                tag.getRecipeId(), tag.getTag());
    }

    @Override
    public void addNewRecipeTag(RecipeTag tag) {
        final Map<String, Object> map = new HashMap<>();

        map.put("recipe_id", tag.getRecipeId());
        map.put("tag", tag.getTag());
        map.put("tags_status", "REGULAR");

        jdbcInsertTag.execute(map);
    }

    //PARA LAS LISTAS
    @Override
    public void addNewUserList(RecipeList rl, int userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("list_name", rl.getName());

        map.put("user_id", userId);

        map.put("ur_status", Status.REGULAR.toString());


        Number id = jdbcInsertUserList.executeAndReturnKey(map);
        rl.setId(id.intValue());

    }

    @Override
    public RecipeList addNewUserList(String name, int userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("list_name", name);

        map.put("user_id", userId);

        map.put("ur_status", Status.REGULAR.toString());


        return new RecipeList(jdbcInsertUserList.executeAndReturnKey(map).intValue(), name);

    }

    @Override
    public void addRecipeToUserList(int listId, int recipeId) {
        Map<String, Object> map = new HashMap<>();

        map.put("recipe_id", recipeId);
        map.put("recipe_list_id", listId);

        map.put("rl_status", Status.REGULAR.toString());

        jdbcInsertRecipeList.execute(map);

    }

    @Override
    public List<RecipeList> getCookLists() {
        final List<RecipeList> list = jdbcTemplate.query(
                "SELECT	*	FROM user_recipe_list  WHERE ur_status = 'REGULAR'",
                LIST_ROW_MAPPER);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<RecipeList> getUserCookLists(int userId) {
        final List<RecipeList> list = jdbcTemplate.query(
                "SELECT	*	FROM user_recipe_list  WHERE ur_status = 'REGULAR' AND user_id = ?",
                LIST_ROW_MAPPER, userId);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<Recipe> getRecipesfromCookList(int listId) {
        final List<Recipe> list = jdbcTemplate.query(
                "SELECT	*	FROM recipe_list LEFT OUTER JOIN recipes" +
                        " ON recipe_list.recipe_id = recipes.recipe_id" +
                        " WHERE rl_status = 'REGULAR' AND recipe_list_id = ?;",
                ROW_MAPPER, listId);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public void updateURList(int recipe_list_id, Map<String, Object> changes) {
        changes.forEach((k, v) -> updateURList(recipe_list_id, k, v));
    }

    private void updateURList(int recipeListId, String k, Object v) {
        if (k.equals("list_name"))
            jdbcTemplate.update("UPDATE user_recipe_list SET list_name = ? WHERE recipe_list_id = ?", v, recipeListId);
        if (k.equals("ur_status"))
            jdbcTemplate.update("UPDATE user_recipe_list SET ur_status = ? WHERE recipe_list_id = ?", v, recipeListId);
    }

    @Override
    public void updateRList(int recipeListId, int recipeId, String status) {
        jdbcTemplate.update("UPDATE recipe_list SET rl_status = ? WHERE recipe_list_id = ? AND recipe_id = ?",
                status, recipeListId, recipeId);
    }

    @Override
    public boolean checkCookListUser(int listId, int userId) {
        final List<RecipeList> list = jdbcTemplate.query(
                "select * FROM user_recipe_list WHERE ur_status = 'REGULAR'" +
                        "AND recipe_list_id = ? AND user_id = ? ", LIST_ROW_MAPPER, listId, userId);

        return !list.isEmpty();
    }

    @Override
    public Optional<RecipeList> getCookList(int cookListId) {
        final List<RecipeList> list = jdbcTemplate.query(
                "SELECT	*	FROM user_recipe_list  WHERE ur_status = 'REGULAR' AND recipe_list_id = ?",
                LIST_ROW_MAPPER, cookListId);
        if (list.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(list.get(0));
    }

    //RECENTLY COOKED

    @Override
    public void addRecentlyCooked(int userId, int recipeId) {
        Map<String,Object> map = new HashMap<>();
        map.put("rc_user_id",userId);
        map.put("rc_recipe_id",recipeId);

        Date date = new Date();
        long time = date.getTime();
        map.put("date_cooked", new Timestamp(time));

        jdbcInsertRecentlyCooked.execute(map);
    }

    @Override
    public List<Recipe> getRecipesInCookOrder(int userId) {
        final List<Recipe> list =
                jdbcTemplate.query("SELECT	*	FROM recently_cooked " +
                        "LEFT OUTER JOIN recipes ON (recipe_id = rc_recipe_id) WHERE " +
                        "recipe_status = 'REGULAR' AND rc_user_id = ? ORDER BY date_cooked DESC", ROW_MAPPER,userId);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return list;
    }

    @Override
    public List<Recipe> getRecipesCookedInBetweenDates(int userId, Date from, Date to) {
        Timestamp fromT = new Timestamp(from.getTime());
        Timestamp toT = new Timestamp(to.getTime());

        final List<Recipe> list =
                jdbcTemplate.query("SELECT	*	FROM recently_cooked " +
                        "LEFT OUTER JOIN recipes ON (recipe_id = rc_recipe_id) WHERE " +
                        "recipe_status = 'REGULAR' AND rc_user_id = ? AND date_cooked >= ? AND date_cooked <= ? ORDER BY date_cooked DESC", ROW_MAPPER,userId,fromT,toT);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return list;

    }


}
