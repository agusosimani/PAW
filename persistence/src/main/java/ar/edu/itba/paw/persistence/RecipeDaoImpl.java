package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.RecipeDao;
import ar.edu.itba.paw.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class RecipeDaoImpl implements RecipeDao {

    private JdbcTemplate jdbcTemplate;
    private final static RowMapper<Recipe> ROW_MAPPER = new RowMapper<Recipe>() {
        @Override
        public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Recipe.Builder(rs.getInt("recipe_id"),rs.getString("name"),
                    null, rs.getString("instructions"),rs.getInt("user_id"), 0)
                    .description(rs.getString("description")).build();
        }
    };


    @Autowired
    public RecipeDaoImpl(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }


    @Override
    public Optional<Recipe> getById(int id) {
        final List<Recipe> list = jdbcTemplate.query("SELECT	*	FROM	recipes WHERE   recipe_id	=	?", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }

    @Override
    public Optional<List<Recipe>> getByUserId(int id) {
        final List<Recipe> list = jdbcTemplate.query("SELECT	*	FROM	recipes WHERE   user_id	=	?", ROW_MAPPER, id);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list);
    }
}
