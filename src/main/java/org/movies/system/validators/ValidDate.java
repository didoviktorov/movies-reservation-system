package org.movies.system.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = DateValidator.class)
public @interface ValidDate {
    String message() default "Invalid Date - you must choose date one day after tomorrow";

    boolean canBeEmpty() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
