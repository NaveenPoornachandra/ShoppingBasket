/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.shoppingbasket.dao.PaymentAccessDAO;
import org.shoppingbasket.dao.UserAccessDAO;
import org.shoppingbasket.entity.IBasket;
import org.shoppingbasket.entity.IPayment;
import org.shoppingbasket.entity.IPaymentCash;
import org.shoppingbasket.entity.IPaymentCreditCard;
import org.shoppingbasket.entity.IPaymentDebitCard;
import org.shoppingbasket.entity.enums.PaymentMode;
import org.shoppingbasket.entity.UserEntity;
import org.shoppingbasket.patterns.PaymentStratergy;

/**
 *
 * @author Naveen_P08
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PaymentManagementService {
    
    @EJB
    private UserAccessDAO userDAO;
    
    @EJB
    private PaymentAccessDAO paymentDAO;
    
    @EJB
    private ShoppingCartManagementService cartService;


    public void processCommonPayment(IPayment iPayment,IBasket basket,String userName) {
        if(basket.getId() == null)
            cartService.saveBasket(basket);
        iPayment.setPaymentDate(new Date());
        UserEntity user = userDAO.find(userName);
        iPayment.setBasket(basket);
        iPayment.setPayer(user);

    }

    public IPayment processCCPayment(IPaymentCreditCard ccPayment,IBasket basket, String userName,PaymentStratergy stratergy) {
        ccPayment.setPaymentMode(PaymentMode.CREIDT);
        processCommonPayment(ccPayment,basket, userName);
        if (ccPayment.getId() != null) {
            paymentDAO.update(ccPayment);
        } else {
            paymentDAO.create(ccPayment);
        }
        stratergy.doPayment();
        return ccPayment;
    }

    public IPayment processDBPayment(IPaymentDebitCard dbPayment,IBasket basket, String userName,PaymentStratergy stratergy) {
        dbPayment.setPaymentMode(PaymentMode.DEBIT);
        processCommonPayment(dbPayment,basket,userName);
        if (dbPayment.getId() != null) {
            paymentDAO.update(dbPayment);
        } else {
            paymentDAO.create(dbPayment);
        }
        stratergy.doPayment();
        return dbPayment;
    }

    public IPayment processCSHPayment(IPaymentCash cshPayment,IBasket basket, String userName,PaymentStratergy stratergy) {
        cshPayment.setPaymentMode(PaymentMode.CASH);
        processCommonPayment(cshPayment,basket,userName);
        if (cshPayment.getId() != null) {
            paymentDAO.update(cshPayment);
        } else {
            paymentDAO.create(cshPayment);
        }
        stratergy.doPayment();
        return cshPayment;
    }

    public List<IPayment> loadUserPayment(String userName) {
        Map<String, String> params = new HashMap<>();
        params.put("uname", userName);
        return paymentDAO.findWithNamedQuery("IPayment.ByUser", params);
    }
    
    public void updatePayment(IPayment payment){
        paymentDAO.update(payment);
    }
}
