package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.Either;
import ar.edu.itba.paw.model.Enum.Warnings;
import ar.edu.itba.paw.model.User;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> getById(final int id);

    Optional<User> getByIdComplete(int id);

    Optional<User> findByUsername(final String username);

    Either<User, Warnings> signUpUser(User user);

    void update(User user);

    void deleteAccount(User user);

}
