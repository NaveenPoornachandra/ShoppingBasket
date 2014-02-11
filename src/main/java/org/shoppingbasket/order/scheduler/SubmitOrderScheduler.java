/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.order.scheduler;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.shoppingbasket.annotation.Logging;
import org.shoppingbasket.entity.IOrder;
import org.shoppingbasket.jms.ProcessOrderMsgService;
import org.shoppingbasket.service.OrderManagementService;

/**
 *
 * @author Naveen_P08
 */
@Startup
@Singleton
public class SubmitOrderScheduler {
    
    @Resource SessionContext ctx;
    
    @Inject
    @Logging
    Logger logger;
    
    @EJB
    private ProcessOrderMsgService msgService;
    
    @EJB
    private OrderManagementService orderService;
    
    @Schedule(hour="*", minute="*/1", second="10", info = "Every 2 second timer")
    public void process(){
         logger.log(Level.INFO,"Submit Order Scheduler started......");
         List<IOrder> orders = orderService.listCreatedOrders();
         for(IOrder order:orders){
             orderService.submitOrder(order);
             msgService.submitOrder(String.valueOf(order.getId()));
         }
         logger.log(Level.INFO,"Submit Order Scheduler Completed @ ".concat(new Date().toString()));
    }
    
    
}
