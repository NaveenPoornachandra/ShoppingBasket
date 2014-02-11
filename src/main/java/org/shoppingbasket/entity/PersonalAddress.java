/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Naveen_P08
 */
@Entity
@DiscriminatorValue(value = "PERSONAL")
public class PersonalAddress extends ShoppingUserAddress implements Serializable {

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @Column(name = "EMAIL_NOTIFICATION")
    private Boolean emailNotification;

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the emailNotification
     */
    public Boolean getEmailNotification() {
        return emailNotification;
    }

    /**
     * @param emailNotification the emailNotification to set
     */
    public void setEmailNotification(Boolean emailNotification) {
        this.emailNotification = emailNotification;
    }

}
