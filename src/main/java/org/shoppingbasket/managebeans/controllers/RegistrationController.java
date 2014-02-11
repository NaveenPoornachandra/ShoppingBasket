/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.managebeans.controllers;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.convert.DateTimeConverter;
import javax.inject.Named;
import org.shoppingbasket.entity.UserEntity;
import org.shoppingbasket.service.AdminManagementService;

/**
 *
 * @author Naveen_P08
 */
@Named
@RequestScoped
public class RegistrationController {

    private Boolean rerror;

    private UserEntity user = new UserEntity();

    private DateTimeConverter dateTimeConverter = new DateTimeConverter();

    private String erroMsg;

    @EJB
    private AdminManagementService adminService;

    public String registerUser() {
        try {
            adminService.createUser(getUser());
        return "login";
        } catch (Exception exc) {
            this.setRerror((Boolean) true);
            return "register";
        }
    }

    /**
     * @return the rerror
     */
    public Boolean getRerror() {
        return rerror;
    }

    /**
     * @param rerror the rerror to set
     */
    public void setRerror(Boolean rerror) {
        this.rerror = rerror;
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
     * @return the erroMsg
     */
    public String getErroMsg() {
        return erroMsg;
    }

    /**
     * @param erroMsg the erroMsg to set
     */
    public void setErroMsg(String erroMsg) {
        this.erroMsg = erroMsg;
    }

    /**
     * @return the dateTimeConverter
     */
    public DateTimeConverter getDateTimeConverter() {
        dateTimeConverter.setPattern("MM/DD/YYYY");
        return dateTimeConverter;
    }

    /**
     * @param dateTimeConverter the dateTimeConverter to set
     */
    public void setDateTimeConverter(DateTimeConverter dateTimeConverter) {
        this.dateTimeConverter = dateTimeConverter;
    }

}
