package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.RatingsDao;
import ar.edu.itba.paw.model.Enum.Status;
import ar.edu.itba.paw.model.Rating;
import ar.edu.itba.paw.model.Recipe;
import ar.edu.itba.paw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.*;

@Repository
public class RatingsDaoImpl implements RatingsDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsertRating;

    private final static RowMapper<Rating> ROW_MAPPER = (rs, rowNum) ->
            new Rating(rs.getInt("user_id"),
                    rs.getInt("recipe_id"),
                    rs.getFloat("rating"),
                    rs.getDate("date"),
                    rs.getString("status"));

    private final static RowMapper<Float> ROW_MAPPER_RECIPE_TOTAL_RATING = (rs, rowNum) ->
            rs.getFloat("totalRating");

    @Autowired
    public RatingsDaoImpl(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsertRating = new SimpleJdbcInsert(jdbcTemplate).withTableName("ratings");
    }


    @Override
    public void addNewRating(int user, int recipe, float rating) {
        Map<String, Object> map = new HashMap<>();

        map.put("user_id", user);
        map.put("recipe_id", recipe);
        map.put("rating", rating);

        Date date= new Date();
        long time = date.getTime();
        map.put("date", new Timestamp(time));

        map.put("status", Status.REGULAR.toString());

        jdbcInsertRating.execute(map);
    }

    @Override
    public void update(int user, int recipe, String k, Object v) {
        if (k.equals("status")) {
            jdbcTemplate.update(
                    "UPDATE ratings SET status = ? WHERE user_id = ? AND recipe_id = ? AND status = 'REGULAR'",
                    v, user, recipe);
        }
        if (k.equals("rating")) {
            jdbcTemplate.update(
                    "UPDATE ratings SET rating = ? WHERE user_id = ? AND recipe_id = ? AND status = 'REGULAR'",
                    v, user, recipe);
        }
    }

    @Override
    public List<Rating> getRatingsRecipe(int recipe) {
        final List<Rating> list =
                jdbcTemplate.query("SELECT * FROM ratings WHERE recipe_id	= ? AND status = 'REGULAR'",
                        ROW_MAPPER, recipe);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return list;
    }

    @Override
    public List<Rating> getRatingsUser(int user) {
        final List<Rating> list =
                jdbcTemplate.query("SELECT * FROM ratings WHERE user_id	= ? AND status = 'REGULAR'",
                        ROW_MAPPER, user);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return list;
    }

    @Override
    public Optional<Rating> getSpecificRating(int user, int recipe) {
        final List<Rating> list =
                jdbcTemplate.query("SELECT * FROM ratings WHERE user_id	= ? AND recipe_id = ? AND status = 'REGULAR'",
                        ROW_MAPPER, user, recipe);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));

    }

    @Override
    public Optional<Float> getRecipeRating(int recipeId){
        final List<Float> list =
                jdbcTemplate.query("SELECT avg(rating) as totalRating FROM ratings WHERE recipe_id = ? AND status = 'REGULAR'",
                        ROW_MAPPER_RECIPE_TOTAL_RATING, recipeId);
        if(list.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(list.get(0));
    }


}
