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
@DiscriminatorValue(value = "DELIVERY")
public class DeliveryAddress extends ShoppingUserAddress implements Serializable {
    
    @Column(name = "PREFERRED_TIME")
    private Integer preferredTime;
    
    @Column(name = "COURIER_NAME")
    private String courierName;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "DELIVERED_DATE")
    private Date deliveredDate;

    /**
     * @return the preferredTime
     */
    public Integer getPreferredTime() {
        return preferredTime;
    }

    /**
     * @param preferredTime the preferredTime to set
     */
    public void setPreferredTime(Integer preferredTime) {
        this.preferredTime = preferredTime;
    }

    /**
     * @return the courierName
     */
    public String getCourierName() {
        return courierName;
    }

    /**
     * @param courierName the courierName to set
     */
    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    /**
     * @return the deliveredDate
     */
    public Date getDeliveredDate() {
        return deliveredDate;
    }

    /**
     * @param deliveredDate the deliveredDate to set
     */
    public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
    }
        
}
