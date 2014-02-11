/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.shoppingbasket.validation.impl.MatchesValidator;

/**
 *
 * @author Naveen_P08
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MatchesValidator.class)
public @interface Matches {

    String message() default "{org.shoppingbasket.validation.Matches}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    String field();
    
    String verifyField();
}
