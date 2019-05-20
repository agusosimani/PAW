package ar.edu.itba.paw.webapp.form;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {
    public void initialize(PasswordMatch constraint) {
    }

    public boolean isValid(Object object, ConstraintValidatorContext context) {
        boolean isValid = ((RegisterForm)object).getPassword().equals(((RegisterForm)object).getRepeatPassword());

        if ( !isValid ) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate( "{PasswordMatch}" )
                    .addNode( "repeatPassword" ).addConstraintViolation();
        }

        return isValid;
    }
}