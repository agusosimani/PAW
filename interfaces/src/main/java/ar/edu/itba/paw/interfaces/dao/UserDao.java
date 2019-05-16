package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.User;

import java.util.Map;
import java.util.Optional;

public interface UserDao {
    Optional<User> getById(final int id);
    User signUpUser(User user);

    void update(User user, Map<String,Object> changes);

    Optional<User> getByEmail(final String email);
    Optional<User> getByUsername(final String username);

}
