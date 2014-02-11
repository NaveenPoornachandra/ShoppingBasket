/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.managedbeans.flow;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.shoppingbasket.annotation.FlowScopeType;
import org.shoppingbasket.annotation.Logging;
import org.shoppingbasket.annotation.ShoppingCartType;
import org.shoppingbasket.annotation.UserName;
import org.shoppingbasket.beans.ShoppingCart;
import org.shoppingbasket.entity.IOrder;
import org.shoppingbasket.entity.UserEntity;
import org.shoppingbasket.service.OrderManagementService;

/**
 *
 * @author Naveen_P08
 */
@Named
@FlowScoped("process")
public class ProcessFlow {

    @Inject
    @Logging
    Logger logger;

    @Inject
    @ShoppingCartType
    private ShoppingCart cart;

    private UserEntity user;

    @Inject
    @UserName
    private String currentUserName;

    private List<IOrder> processingOrders;

    private List<IOrder> deliveryOrders;

    @EJB
    private OrderManagementService orderService;
    
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

    public String loadUserProcOrders() {
        this.setProcessingOrders(orderService.listProcessingOrders(currentUserName));
        return "orderProcess";
    }

    public String loadUserDelevOrders() {
        this.setDeliveryOrders(orderService.listDeliveryOrders(currentUserName));
        return "deliveryProcess";
    }

    public void processStage(Long orderId) {
        logger.log(Level.INFO, "Cancelling Orderd :".concat(String.valueOf(orderId)));
        for (IOrder order : processingOrders) {
            if (order.getId().equals(orderId)) {
                IOrder orderU = orderService.processOrder(order);
                order.setVersion(orderU.getVersion());
            }
        }
    }

    public void deliveryStage(Long orderId) {
        logger.log(Level.INFO, "Cancelling Orderd :".concat(String.valueOf(orderId)));
        for (IOrder order : deliveryOrders) {
            if (order.getId().equals(orderId)) {
                IOrder orderU = orderService.deliverOrder(order);
                order.setVersion(orderU.getVersion());
            }
        }
    }

    /**
     * @return the processingOrders
     */
    public List<IOrder> getProcessingOrders() {
        return processingOrders;
    }

    /**
     * @param processingOrders the processingOrders to set
     */
    public void setProcessingOrders(List<IOrder> processingOrders) {
        this.processingOrders = processingOrders;
    }

    /**
     * @return the deliveryOrders
     */
    public List<IOrder> getDeliveryOrders() {
        return deliveryOrders;
    }

    /**
     * @param deliveryOrders the deliveryOrders to set
     */
    public void setDeliveryOrders(List<IOrder> deliveryOrders) {
        this.deliveryOrders = deliveryOrders;
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
