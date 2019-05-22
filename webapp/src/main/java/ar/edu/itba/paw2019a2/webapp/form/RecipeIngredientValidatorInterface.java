package ar.edu.itba.paw2019a2.webapp.form;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RecipeIngredientValidator.class)
@Documented
public @interface RecipeIngredientValidatorInterface {

    String message() default "{Error.Ingredient.Invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}