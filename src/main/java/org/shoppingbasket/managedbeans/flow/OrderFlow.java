/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.managedbeans.flow;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.shoppingbasket.annotation.FlowScopeType;
import org.shoppingbasket.annotation.OrderDetailsType;
import org.shoppingbasket.annotation.ShoppingCartType;
import org.shoppingbasket.annotation.UserName;
import org.shoppingbasket.beans.OrderDetails;
import org.shoppingbasket.beans.ShoppingCart;
import org.shoppingbasket.entity.UserEntity;
import org.shoppingbasket.service.OrderManagementService;

/**
 *
 * @author Naveen_P08
 */
@Named
@FlowScoped("order")
public class OrderFlow extends BasicFlow implements Serializable {

    @Inject
    @ShoppingCartType
    private ShoppingCart cart;

    private UserEntity user;

    @Inject
    @OrderDetailsType
    private OrderDetails orderDetails;

    @Inject
    @UserName
    private String userName;

    @EJB
    private OrderManagementService orderService;

    @Inject
    @FlowScopeType
    Map<Object,Object> currentFlow;

    @PostConstruct
    public void init() {
        listUserOrders();
        if (currentFlow.get("cart") != null) {
            this.cart = (ShoppingCart) currentFlow.get("cart");
        }
        if (currentFlow.get("user") != null) {
            this.user = (UserEntity) currentFlow.get("user");
        }
     }

    public String listUserOrders() {
        if (getOrderDetails().getOrderList().isEmpty()) {
            getOrderDetails().setOrderList(orderService.listUserOrders(userName));
        }
        return "order";
    }

    public String getOrderDetails(Long orderId) {
        getOrderDetails().setCurrentOrder(orderService.getOrder(orderId));
        return "orderDetails";
    }

    @PreDestroy
    public void delete() {
        getOrderDetails().getOrderList().clear();
    }

    /**
     * @return the orderDetails
     */
    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    /**
     * @param orderDetails the orderDetails to set
     */
    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
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
