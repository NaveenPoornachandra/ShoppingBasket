/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import org.shoppingbasket.annotation.OrderDetailsType;
import org.shoppingbasket.entity.IOrder;

/**
 *
 * @author Naveen_P08
 */
@Dependent
@OrderDetailsType
public class OrderDetails {
    
    private List<IOrder> orderList;
    
    private IOrder currentOrder;
    
    @PostConstruct
    public void init(){
        setOrderList(new ArrayList<>());
    }

    /**
     * @return the orderList
     */
    public List<IOrder> getOrderList() {
        return orderList;
    }

    /**
     * @param orderList the orderList to set
     */
    public void setOrderList(List<IOrder> orderList) {
        this.orderList = orderList;
    }

    /**
     * @return the currentOrder
     */
    public IOrder getCurrentOrder() {
        return currentOrder;
    }

    /**
     * @param currentOrder the currentOrder to set
     */
    public void setCurrentOrder(IOrder currentOrder) {
        this.currentOrder = currentOrder;
    }
    
}
