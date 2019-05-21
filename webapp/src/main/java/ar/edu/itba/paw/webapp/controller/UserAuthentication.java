package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.AuthenticationService;
import ar.edu.itba.paw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@ControllerAdvice
public class UserAuthentication {
    @Autowired
    AuthenticationService authenticationService;

    @ModelAttribute("isUserLogged")
    public boolean isUserLogged() {
        return authenticationService.isUserLogged();
    }

    @ModelAttribute("getLoggedUser")
    public Optional<User> getLoggedUser() {
        return authenticationService.getLoggedUser();
    }
}
