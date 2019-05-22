package ar.edu.itba.paw2019a2.webapp.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
public class IngredientsAmountValidator implements ConstraintValidator<IngredientsAmount, List<Integer>> {

    @Autowired
    private MessageSource messageSource;

    public void initialize(IngredientsAmount constraint) {
    }

    public boolean isValid(List<Integer> amountList, ConstraintValidatorContext context) {
        for(Integer i : amountList){
            if(i <= 0)
                return false;
        }
        return true;
    }
}
