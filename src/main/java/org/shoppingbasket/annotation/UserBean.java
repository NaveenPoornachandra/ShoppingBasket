/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import javax.inject.Qualifier;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Target;

/**
 *
 * @author Naveen_P08
 */
@Qualifier
@Retention(RUNTIME)
@Target({FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.TYPE})
public @interface UserBean {
    
}
