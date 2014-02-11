/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.managebeans.controllers;

import java.io.Serializable;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.shoppingbasket.entity.UserEntity;
import org.shoppingbasket.service.AdminManagementService;

/**
 *
 * @author Naveen_P08
 */
@Named
@SessionScoped
@DeclareRoles({"ADMIN_USER","REG_USER","UN_REG_USER","ORDER_PROCESS","ORDER_DELIVER","ORDER_QA"})
public class UserController implements Serializable {

    private String uname;

    private String upassword;

    private Boolean lerror;
    
    private UserEntity user;
    

    private Locale locale = context().getViewRoot().getLocale();

    @EJB
    private AdminManagementService adminService;

    private Logger logger = Logger.getLogger(UserController.class.getName());

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
            request.login(getUname(), getUpassword());
            this.user = adminService.getUserByName(getUname());
            ServletContext ctx = (ServletContext) context.getExternalContext().getContext();
        } catch (ServletException ex) {
            this.lerror = true;
            getLogger().log(Level.SEVERE, null, ex);
            return "login";
        }
        return "shopping";
    }

    @RolesAllowed("REG_USER")
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException ex) {
            getLogger().log(Level.SEVERE, null, ex);
        }
        this.user = null;
        return "logout";
    }
    
    @RolesAllowed("ADMIN_USER")
    public String admin(){
        return "admin";
    }
    
    @RolesAllowed("UN_REG_USER")
    public String register(String success) {
        adminService.registerUser(user);
        return success;
    }
    
    @RolesAllowed("REG_USER")
    public String unRegister(String success, String failure) {
        adminService.unRegisterUser(user);
        return success;
    }

    public UserEntity getAuthenticatedUser() {
        return getUser();
    }
    

    public boolean isLogged() {
        return getUser() != null;
    }

    /**
     * @return the user
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * @return the uname
     */
    public String getUname() {
        return uname;
    }

    /**
     * @param uname the uname to set
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     * @return the upassword
     */
    public String getUpassword() {
        return upassword;
    }

    /**
     * @param upassword the upassword to set
     */
    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    protected FacesContext context() {
        return (FacesContext.getCurrentInstance());
    }

    /**
     * @return the locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * @return the logger
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * @param logger the logger to set
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * @return the lerror
     */
    public Boolean getLerror() {
        return lerror;
    }

    /**
     * @param lerror the lerror to set
     */
    public void setLerror(Boolean lerror) {
        this.lerror = lerror;
    }

}
