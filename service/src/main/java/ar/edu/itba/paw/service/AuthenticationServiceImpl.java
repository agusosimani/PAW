package ar.edu.itba.paw.service;

import ar.edu.itba.paw.interfaces.service.AuthenticationService;
import ar.edu.itba.paw.interfaces.service.UserService;
import ar.edu.itba.paw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserService us;

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public boolean isUserLogged() {
        Authentication authentication = getAuthentication();
        return authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public Optional<User> getLoggedUser() {
        Authentication authentication = getAuthentication();
        String currentUserName = authentication.getName();
        if(isUserLogged())
            return Optional.of(us.findByUsername(currentUserName).getValue());
        return Optional.empty();
    }
}
