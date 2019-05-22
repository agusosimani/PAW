package ar.edu.itba.paw2019a2.webapp.form;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

@Target({FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = AvailableEmailValidator.class)
@Documented
public @interface AvailableEmail {
    String message() default "{AvailableEmail}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}