/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.shoppingbasket.entity.IPayment;

/**
 *
 * @author Naveen_P08
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PaymentAccessDAO extends DataAccessDAO<IPayment>{

    public PaymentAccessDAO() {
        super(IPayment.class);
    }
   
}
