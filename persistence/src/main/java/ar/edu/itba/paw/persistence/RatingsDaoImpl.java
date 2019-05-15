package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.RatingsDao;
import ar.edu.itba.paw.model.Rating;
import ar.edu.itba.paw.model.Recipe;
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
public class RatingsDaoImpl implements RatingsDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsertRating;

    private final static RowMapper<Rating> ROW_MAPPER = (rs, rowNum) ->
            new Rating(rs.getInt("user_id"),
                    rs.getInt("recipe_id"),
                    rs.getInt("rating"),
                    rs.getDate("date"),
                    rs.getInt("status"));

    @Autowired
    public RatingsDaoImpl(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsertRating = new SimpleJdbcInsert(jdbcTemplate).withTableName("ratings");
    }


    @Override
    public void addNewRating(int user, int recipe, int rating) {
        Map<String, Object> map = new HashMap<>();

        map.put("user_id", user);
        map.put("recipe_id", recipe);
        map.put("rating", rating);

        jdbcInsertRating.execute(map);
    }

    @Override
    public void update(int user, int recipe, String k, Object v) {
        if (k.equals("status")) {
            jdbcTemplate.update(
                    "UPDATE ratings SET status = ? WHERE user_id = ? AND recipe_id = ? AND status != 0",
                    v, user, recipe);
        }
        if (k.equals("rating")) {
            jdbcTemplate.update(
                    "UPDATE ratings SET ratings = ? WHERE user_id = ? AND recipe_id = ? AND status != 0",
                    v, user, recipe);
        }
    }

    @Override
    public Optional<List<Rating>> getRatingsRecipe(int recipe) {
        final List<Rating> list =
                jdbcTemplate.query("SELECT * FROM ratings WHERE recipe_id	= ? AND status != 0",
                        ROW_MAPPER, recipe);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list);
    }

    @Override
    public Optional<List<Rating>> getRatingsUser(int user) {
        final List<Rating> list =
                jdbcTemplate.query("SELECT * FROM ratings WHERE user_id	= ? AND status != 0",
                        ROW_MAPPER, user);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list);
    }

    @Override
    public Optional<Rating> getSpecificRating(int user, int recipe) {
        final List<Rating> list =
                jdbcTemplate.query("SELECT * FROM ratings WHERE user_id	= ? AND recipe_id = ? AND status != 0",
                        ROW_MAPPER, user, recipe);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));

    }


}
