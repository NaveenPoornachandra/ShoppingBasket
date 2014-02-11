/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.managedbeans.flow;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.shoppingbasket.annotation.CCPaymentType;
import org.shoppingbasket.annotation.CSHPaymentType;
import org.shoppingbasket.annotation.DBPaymentType;
import org.shoppingbasket.annotation.FlowScopeType;
import org.shoppingbasket.annotation.ShoppingCartType;
import org.shoppingbasket.beans.ShoppingCart;
import org.shoppingbasket.entity.IOrder;
import org.shoppingbasket.entity.IPayment;
import org.shoppingbasket.entity.UserEntity;
import org.shoppingbasket.patterns.impl.CCPaymentStratergy;
import org.shoppingbasket.patterns.impl.CSHPaymentStratergy;
import org.shoppingbasket.patterns.impl.DBPaymentStratergy;
import org.shoppingbasket.service.OrderManagementService;
import org.shoppingbasket.service.PaymentManagementService;

/**
 *
 * @author Naveen_P08
 */
@Named
@FlowScoped("payment")
public class PaymentFlow implements Serializable {

    @Inject
    @ShoppingCartType
    private ShoppingCart cart;

    @EJB
    PaymentManagementService service;

    @EJB
    OrderManagementService orderService;

    @Inject
    @CCPaymentType
    CCPaymentStratergy ccPayment;

    @Inject
    @CSHPaymentType
    CSHPaymentStratergy cshPayment;

    @Inject
    @DBPaymentType
    DBPaymentStratergy dbPayment;

    private UserEntity user;
    
    @Inject
    @FlowScopeType
    Map<Object,Object> currentFlow;

    @PostConstruct
    public void init() {
        if (currentFlow.get("cart") != null) {
            this.cart = (ShoppingCart) currentFlow.get("cart");
        }
        if (currentFlow.get("user") != null) {
            this.user = (UserEntity) currentFlow.get("user");
        }
     }

    /**
     * @return the logout
     */
    public String getLogout() {
        return "/logout";
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return "/login";
    }

    public String getAdmin() {
        return "/admin";
    }

    /**
     * @return the cart
     */
    public ShoppingCart getCart() {
        return cart;
    }

    /**
     * @param cart the cart to set
     */
    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public String payByCreditCard() {
        IPayment payemnt = service.processCCPayment(this.cart.getCcPayment(), this.cart.getBasket(), this.user.getId(),ccPayment);
        return createOrder(payemnt);
    }

    public String payByDebitCard() {
        IPayment payemnt = service.processDBPayment(this.cart.getDbPayment(), this.cart.getBasket(), this.user.getId(),dbPayment);
        return createOrder(payemnt);
    }

    public String payByCash() {
        IPayment payemnt = service.processCSHPayment(this.cart.getCshPayment(), this.cart.getBasket(), this.user.getId(),cshPayment);
        return createOrder(payemnt);
    }

    public String createOrder(IPayment payment) {
        IOrder order = orderService.createOrder(this.getUser(), this.cart.getBasket(), payment);
        this.cart.setOrder(order);
        service.updatePayment(payment);
        return "payment1a";
    }

    public void paymentChange(ValueChangeEvent event) throws AbortProcessingException {
        this.cart.setPaymentType(String.valueOf(event.getNewValue()));
    }

    public String continueShopping() {
        this.cart.clear();
        return "shopping";
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

}
