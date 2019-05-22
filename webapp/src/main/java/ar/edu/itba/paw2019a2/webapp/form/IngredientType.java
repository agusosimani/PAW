package ar.edu.itba.paw2019a2.webapp.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IngredientTypeValidator.class)
@Documented
public @interface IngredientType {



    String message() default "{Error.Ingredient.Repeated}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}