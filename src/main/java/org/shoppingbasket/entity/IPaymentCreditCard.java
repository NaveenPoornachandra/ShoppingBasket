/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Naveen_P08
 */
@Entity 
@DiscriminatorValue(value = "CREIDT")
public class IPaymentCreditCard extends IPayment implements Serializable  {
    private static final long serialVersionUID = 1L;
    
    @Column(name = "CC_HOLDER_NAME")
    private String ccName;
    
    @Column(name = "CC_NUMBER")
    private String ccNumber;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "CC_EXPIRY_DATE")
    private Date  ccExpiryDate;

    /**
     * @return the ccName
     */
    public String getCcName() {
        return ccName;
    }

    /**
     * @param ccName the ccName to set
     */
    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    /**
     * @return the ccNumber
     */
    public String getCcNumber() {
        return ccNumber;
    }

    /**
     * @param ccNumber the ccNumber to set
     */
    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    /**
     * @return the ccExpiryDate
     */
    public Date getCcExpiryDate() {
        return ccExpiryDate;
    }

    /**
     * @param ccExpiryDate the ccExpiryDate to set
     */
    public void setCcExpiryDate(Date ccExpiryDate) {
        this.ccExpiryDate = ccExpiryDate;
    }
    
    
}
