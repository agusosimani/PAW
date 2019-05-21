package ar.edu.itba.paw.interfaces.service;

import ar.edu.itba.paw.model.User;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface AuthenticationService {
    Authentication getAuthentication();
    boolean isUserLogged();
    Optional<User> getLoggedUser();
}
