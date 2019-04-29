package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.UserDao;
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
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    private final static RowMapper<User> ROW_MAPPER = (rs, rowNum) ->
            new User.Builder(rs.getInt("user_id"),rs.getString("username"),
            rs.getString("password"),rs.getString("mail"))
            .gender(rs.getInt("gender")).name(rs.getString("name"))
            .surname(rs.getString("surname")).status(rs.getInt("status")).build();

    @Autowired
    public UserDaoImpl(final DataSource ds) {

        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("user_id");
    }

    @Override
    public Optional<User> getById(final int id) {
        return jdbcTemplate.query("SELECT	*	FROM	users	WHERE	user_id	=	?",
                ROW_MAPPER, id).stream().findAny();
    }

    @Override
    public User signUpUser(User user) {
        //return Transactions.executeInTransaction(jdbcTemplate,() -> create(user));
        return create(user);
    }

    @Override
    public void update(User user, Map<String,Object> changes) {
        changes.forEach((k, v) -> Transactions.executeInTransaction(jdbcTemplate,() -> update(user, k, v)));
    }

    //TODO: UPDATE DEL OBJETO USUARIO
    private User update(User user, String k, Object v) {
        jdbcTemplate.update("UPDATE users SET ? = ? WHERE user_id = ?",k,v,user.getId());
        return user;
    }

    @Override
    public Optional<User> findByUsername(final String username) {
        final List<User> list = jdbcTemplate.query("SELECT	*	FROM	users	WHERE	username	=	?", ROW_MAPPER, username);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        final List<User> list = jdbcTemplate.query("SELECT	*	FROM	users	WHERE	email	=	?", ROW_MAPPER, email);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }



    private User create(User user) {
        final Map<String, Object> map = new HashMap<>();

        map.put("username",user.getUsername());
        map.put("password",user.getPassword());
        map.put("mail",user.getEmail());

        if(!user.getName().isEmpty() && !user.getName().equals(""))
            map.put("name",user.getName());
        if(!user.getSurname().isEmpty() && !user.getSurname().equals(""))
            map.put("surname",user.getSurname());
        if(!(user.getGender() == 0))
            map.put("gender",user.getGender());
        if(!(user.getStatus() == 0))
            map.put("status",user.getStatus());


        final Number userId = jdbcInsert.executeAndReturnKey(map);

        user.setId(userId.intValue());

        return user;
    }

}