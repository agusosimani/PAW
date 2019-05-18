package ar.edu.itba.paw.webapp.form;

import ar.edu.itba.paw.interfaces.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AvailableUsernameValidator implements ConstraintValidator<AvailableUsername, String> {
    @Autowired
    private UserService userService;

    public void initialize(AvailableUsername constraint) {
    }

    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !userService.findByUsername(username).isPresent();
    }
}