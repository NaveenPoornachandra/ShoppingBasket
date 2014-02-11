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
@DiscriminatorValue(value = "DEBIT")
public class IPaymentDebitCard extends IPayment implements Serializable {
   
    @Column(name = "DB_NUMBER")
    private String dbNumber;
    
    @Column(name = "DB_HOLDER_NAME")
    private String dbName;

    /**
     * @return the dbNumber
     */
    public String getDbNumber() {
        return dbNumber;
    }

    /**
     * @param dbNumber the dbNumber to set
     */
    public void setDbNumber(String dbNumber) {
        this.dbNumber = dbNumber;
    }

    /**
     * @return the dbName
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * @param dbName the dbName to set
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
     
}
