package ar.edu.itba.paw.webapp.form;

import ar.edu.itba.paw.interfaces.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AvailableEmailValidator implements ConstraintValidator<AvailableEmail, String> {
    @Autowired
    private UserService userService;

    public void initialize(AvailableEmail constraint) {
    }

    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userService.findByEmail(email).isValuePresent();
    }
}