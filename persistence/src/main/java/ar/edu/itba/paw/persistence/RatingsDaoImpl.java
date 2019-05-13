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
    public void addNewRating(User user, Recipe recipe, int rating) {
        Map<String,Object> map = new HashMap<>();

        map.put("user_id",user.getId());
        map.put("recipe_id",recipe.getId());
        map.put("rating",rating);

        jdbcInsertRating.execute(map);
    }

    @Override
    public void update(User user, Recipe recipe, String k, Object v) {
        jdbcTemplate.update(
                "UPDATE ratings SET ? = ? WHERE user_id = ? AND recipe_id = ? AND status != 0",
                k,v,user.getId(),recipe.getId());
    }

    @Override
    public Optional<List<Rating>> getRatingsRecipe(Recipe recipe) {
        final List<Rating> list =
                jdbcTemplate.query("SELECT * FROM ratings WHERE recipe_id	= ? AND status != 0",
                        ROW_MAPPER, recipe.getId());
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list);
    }

    @Override
    public Optional<List<Rating>> getRatingsUser(User user) {
        final List<Rating> list =
                jdbcTemplate.query("SELECT * FROM ratings WHERE user_id	= ? AND status != 0",
                        ROW_MAPPER, user.getId());
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list);
    }

    @Override
    public Optional<Rating> getSpecificRating(User user, Recipe recipe) {
        final List<Rating> list =
                jdbcTemplate.query("SELECT * FROM ratings WHERE user_id	= ? AND recipe_id = ? AND status != 0",
                        ROW_MAPPER, user.getId(), recipe.getId());
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));

    }



}
