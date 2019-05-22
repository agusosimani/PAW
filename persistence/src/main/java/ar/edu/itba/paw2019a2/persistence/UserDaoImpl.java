package ar.edu.itba.paw2019a2.persistence;

import ar.edu.itba.paw2019a2.interfaces.dao.UserDao;
import ar.edu.itba.paw2019a2.model.Enum.Warnings;
import ar.edu.itba.paw2019a2.model.User;
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
            .gender(rs.getString("gender")).name(rs.getString("name"))
            .surname(rs.getString("surname")).status(rs.getString("user_status"))
                    .enabled(rs.getBoolean("enabled")).isAdmin(rs.getBoolean("is_admin")).build();


    @Autowired
    public UserDaoImpl(final DataSource ds) {

        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("user_id");
    }

    @Override
    public Optional<User> getById(final int id) {
        return jdbcTemplate.query("SELECT	*	FROM	users	WHERE	user_id	=	? AND user_status = 'REGULAR'",
                ROW_MAPPER, id).stream().findAny();
    }

    @Override
    public User signUpUser(User user) {
        final Map<String, Object> map = new HashMap<>();

        map.put("username",user.getUsername());
        map.put("password",user.getPassword());
        map.put("mail",user.getEmail());

        if(!user.getName().isEmpty()  && !user.getName().equals(""))
            map.put("name",user.getName());
        if(!user.getSurname().isEmpty() && !user.getSurname().equals(""))
            map.put("surname",user.getSurname());
        if(!user.getGender().isEmpty() && !user.getSurname().equals(""))
            map.put("gender",user.getGender());

        map.put("user_status","REGULAR");
        map.put("enabled",false);
        map.put("is_admin",false);

//        if(!user.getImage().isEmpty())
//            map.put("image",user.getImage());

        final Number userId = jdbcInsert.executeAndReturnKey(map);

        user.setId(userId.intValue());

        return user;
    }

    @Override
    public void update(User user, Map<String,Object> changes) {
        changes.forEach((k, v) -> update(user, k, v));
    }

    private void update(User user, String k, Object v) {
        if(k.equals("password")){
            jdbcTemplate.update("UPDATE users SET password = ? WHERE user_id = ?",v,user.getId());
        }
        if(k.equals("mail")){
            jdbcTemplate.update("UPDATE users SET mail = ? WHERE user_id = ?",v,user.getId());
        }
        if(k.equals("name")){
            jdbcTemplate.update("UPDATE users SET name = ? WHERE user_id = ?",v,user.getId());
        }
        if(k.equals("surname")){
            jdbcTemplate.update("UPDATE users SET surname = ? WHERE user_id = ?",v,user.getId());
        }
        if(k.equals("gender")){
            jdbcTemplate.update("UPDATE users SET gender = ? WHERE user_id = ?",v,user.getId());
        }
        if(k.equals("user_status")){
            System.out.println(v);
            jdbcTemplate.update("UPDATE users SET user_status = ? WHERE user_id = ?",v,user.getId());
        }
    }

    @Override
    public Optional<User> getByUsername(final String username) {
        final List<User> list = jdbcTemplate.query("SELECT	*	FROM	users	WHERE	username	=	? AND user_status = 'REGULAR'", ROW_MAPPER, username);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }

    @Override
    public Optional<User> getByEmail(final String email) {
        final List<User> list = jdbcTemplate.query("SELECT	*	FROM	users	WHERE	mail	=	? AND user_status = 'REGULAR'", ROW_MAPPER, email);
        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }

    public Warnings setUserStatus(final int userId, final boolean status) {
        if (getById(userId).isPresent()) {
            try {
                jdbcTemplate.update("UPDATE users SET enabled = ? WHERE user_id = ? ",
                        status,
                        userId);
                return Warnings.valueOf("Success");
            } catch (Exception e ) {
                System.err.println(e.getMessage());
                return Warnings.valueOf("ServerError");
            }
        } else {
            return Warnings.valueOf("NoSuchUser");
        }
    }

    @Override
    public void updatePassword(int id, String password) {
        jdbcTemplate.update(String.format("UPDATE users SET password = ? WHERE user_id = ? ",
                password,
                id));
    }
}