/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.managebeans.controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Naveen_P08
 */
@SessionScoped
public class UserInfo implements Serializable{
    
    private String user_name;
    
    private String user_role;
    
    private String password;

    /**
     * @return the user_name
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * @param user_name the user_name to set
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * @return the user_role
     */
    public String getUser_role() {
        return user_role;
    }

    /**
     * @param user_role the user_role to set
     */
    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
