/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import org.shoppingbasket.annotation.ShoppingCartType;
import org.shoppingbasket.entity.DeliveryAddress;
import org.shoppingbasket.entity.IBasket;
import org.shoppingbasket.entity.IOrder;
import org.shoppingbasket.entity.IPaymentCash;
import org.shoppingbasket.entity.IPaymentCreditCard;
import org.shoppingbasket.entity.IPaymentDebitCard;
import org.shoppingbasket.entity.PersonalAddress;

/**
 *
 * @author Naveen_P08
 */
@Dependent
@ShoppingCartType
public class ShoppingCart implements Serializable{
    
    private IBasket basket;
    
    private List<IBasket> userBaskets;
    
    private PersonalAddress paddress;
    
    private DeliveryAddress daddress;
    
    private Integer itemCount;
    
    private Boolean isSameAsPersonal;
    
    private IPaymentCash cshPayment;
    
    private IPaymentCreditCard ccPayment;
    
    private IPaymentDebitCard dbPayment;
    
    private String paymentType;
    
    private IOrder order;
    
    @PostConstruct
    public void init(){
        basket = new IBasket();
        basket.setBitems(new HashSet<>());
        setPaddress(new PersonalAddress());
        setDaddress(new DeliveryAddress());
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR)+5);
        daddress.setDeliveredDate(cal.getTime());
        daddress.setPreferredTime(10);
        cshPayment = new IPaymentCash();
        ccPayment = new IPaymentCreditCard();
        dbPayment = new IPaymentDebitCard();
        this.paymentType="cc";
    }

    /**
     * @return the basket
     */
    public IBasket getBasket() {
        return basket;
    }

    /**
     * @param basket the basket to set
     */
    public void setBasket(IBasket basket) {
        this.basket = basket;
    }

    /**
     * @return the itemCount
     */
    public Integer getItemCount() {
        return itemCount;
    }

    /**
     * @param itemCount the itemCount to set
     */
    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    /**
     * @return the userBasket
     */
    public List<IBasket> getUserBaskets() {
        return userBaskets;
    }

    /**
     * @param userBaskets the userBasket to set
     */
    public void setUserBaskets(List<IBasket> userBaskets) {
        this.userBaskets = userBaskets;
    }

    /**
     * @return the paddress
     */
    public PersonalAddress getPaddress() {
        return paddress;
    }

    /**
     * @param paddress the paddress to set
     */
    public void setPaddress(PersonalAddress paddress) {
        this.paddress = paddress;
    }

    /**
     * @return the daddress
     */
    public DeliveryAddress getDaddress() {
        return daddress;
    }

    /**
     * @param daddress the daddress to set
     */
    public void setDaddress(DeliveryAddress daddress) {
        this.daddress = daddress;
    }

    /**
     * @return the isSameAsPersonal
     */
    public Boolean getIsSameAsPersonal() {
        return isSameAsPersonal;
    }

    /**
     * @param isSameAsPersonal the isSameAsPersonal to set
     */
    public void setIsSameAsPersonal(Boolean isSameAsPersonal) {
        this.isSameAsPersonal = isSameAsPersonal;
    }

    /**
     * @return the cshPayment
     */
    public IPaymentCash getCshPayment() {
        return cshPayment;
    }

    /**
     * @param cshPayment the cshPayment to set
     */
    public void setCshPayment(IPaymentCash cshPayment) {
        this.cshPayment = cshPayment;
    }

    /**
     * @return the ccPayment
     */
    public IPaymentCreditCard getCcPayment() {
        return ccPayment;
    }

    /**
     * @param ccPayment the ccPayment to set
     */
    public void setCcPayment(IPaymentCreditCard ccPayment) {
        this.ccPayment = ccPayment;
    }

    /**
     * @return the dbPayment
     */
    public IPaymentDebitCard getDbPayment() {
        return dbPayment;
    }

    /**
     * @param dbPayment the dbPayment to set
     */
    public void setDbPayment(IPaymentDebitCard dbPayment) {
        this.dbPayment = dbPayment;
    }

    /**
     * @return the paymentType
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * @param paymentType the paymentType to set
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * @return the order
     */
    public IOrder getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(IOrder order) {
        this.order = order;
    }
    
    public void clear(){
        basket = new IBasket();
        basket.setBitems(new HashSet<>());
    }
    
}
