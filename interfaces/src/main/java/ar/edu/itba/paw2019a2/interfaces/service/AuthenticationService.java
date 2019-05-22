package ar.edu.itba.paw2019a2.interfaces.service;

import ar.edu.itba.paw2019a2.model.User;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface AuthenticationService {
    Authentication getAuthentication();
    boolean isUserLogged();
    Optional<User> getLoggedUser();
}
