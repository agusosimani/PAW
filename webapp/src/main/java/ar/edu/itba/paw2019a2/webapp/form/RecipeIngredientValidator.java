package ar.edu.itba.paw2019a2.webapp.form;

import ar.edu.itba.paw2019a2.model.Ingredient;
import ar.edu.itba.paw2019a2.model.RecipeIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecipeIngredientValidator implements ConstraintValidator<RecipeIngredientValidatorInterface, List<RecipeIngredient>> {

    @Autowired
    private MessageSource messageSource;

    public void initialize(RecipeIngredientValidatorInterface constraint) {

    }

    public boolean isValid(List<RecipeIngredient> recipeIngredientList, ConstraintValidatorContext context) {

        return false;
    }
}
