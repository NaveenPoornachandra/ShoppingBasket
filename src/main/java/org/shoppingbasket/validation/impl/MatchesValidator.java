/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.validation.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;
import org.shoppingbasket.validation.Matches;

/**
 *
 * @author Naveen_P08
 */
public class MatchesValidator implements ConstraintValidator<Matches, Object>{
    
    private String field;
    
    private String verifyField;

    @Override
    public void initialize(Matches constraintAnnotation) {
        field = constraintAnnotation.field();
        verifyField = constraintAnnotation.verifyField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean matches;
        try {
            Object fieldObj = BeanUtils.getProperty(value, field);
            Object verifyFieldObj = BeanUtils.getProperty(value, verifyField);
            System.out.println("---------"+fieldObj);
             System.out.println("---------"+verifyFieldObj);
            matches = ((fieldObj == null)&&(verifyFieldObj == null))
                    ||(fieldObj != null&&fieldObj.equals(verifyFieldObj));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            Logger.getLogger(MatchesValidator.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        if(!matches){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("message").addPropertyNode(verifyField).addBeanNode()
                    .addConstraintViolation();
        }
        return matches;
    }
    
}
