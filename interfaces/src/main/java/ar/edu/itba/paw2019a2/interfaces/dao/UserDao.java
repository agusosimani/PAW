package ar.edu.itba.paw2019a2.interfaces.dao;

import ar.edu.itba.paw2019a2.model.Enum.Warnings;
import ar.edu.itba.paw2019a2.model.User;

import java.util.Map;
import java.util.Optional;

public interface UserDao {
    Optional<User> getById(final int id);
    User signUpUser(User user);

    void update(User user, Map<String,Object> changes);

    Optional<User> getByEmail(final String email);
    Optional<User> getByUsername(final String username);
    Warnings setUserStatus(final int userId, boolean status);

}
