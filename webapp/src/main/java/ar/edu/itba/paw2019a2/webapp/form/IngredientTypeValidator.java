package ar.edu.itba.paw2019a2.webapp.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IngredientTypeValidator implements ConstraintValidator<IngredientType, List<Integer>> {

    @Autowired
    private MessageSource messageSource;

    public void initialize(IngredientType constraint) {

    }

    public boolean isValid(List<Integer> typesList, ConstraintValidatorContext context) {
        Set inputSet = new HashSet(typesList);
        if(inputSet.size()< typesList.size()){
            return false;
        }
        return true;
    }
}
