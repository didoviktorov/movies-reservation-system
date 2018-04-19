package org.movies.system.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;

public class DateValidator implements ConstraintValidator<ValidDate, Date> {

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null) {
            return false;
        }
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DATE, 1);
        today = c.getTime();

        boolean isValid = date.after(today);
        return isValid;
    }
}
