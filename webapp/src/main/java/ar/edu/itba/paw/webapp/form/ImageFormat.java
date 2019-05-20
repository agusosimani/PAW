package ar.edu.itba.paw.webapp.form;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

@Target({FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = ImageFormatValidator.class)
@Documented
public @interface ImageFormat {
    String message() default "{ImageFormat}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}