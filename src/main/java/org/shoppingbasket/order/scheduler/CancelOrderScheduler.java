/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.order.scheduler;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.apache.commons.lang.StringUtils;
import org.shoppingbasket.annotation.Logging;
import org.shoppingbasket.entity.IOrder;
import org.shoppingbasket.jms.CompleteOrderMsgService;
import org.shoppingbasket.service.OrderManagementService;

/**
 *
 * @author Naveen_P08
 */
@Startup
@Singleton
public class CancelOrderScheduler {
    @Resource SessionContext ctx;
    
    @Inject
    @Logging
    Logger logger;
    
    @EJB
    private CompleteOrderMsgService msgService;
    
    @EJB
    private OrderManagementService orderService;
    
    @Schedule(hour="*", minute="*/1", second="50", info = "Every 5 second timer")
    public void process(){
        logger.log(Level.INFO,"Cancel Order started......");
        String orderId = msgService.recieveOrder();
        if(orderId!=null && StringUtils.isNumeric(orderId)){
            IOrder order = orderService.findOrder(Long.valueOf(orderId));
            logger.log(Level.INFO,"Cancelling Orderd :".concat(String.valueOf(order.getId())));
            orderService.cancelOrder(order);
        }
        logger.log(Level.INFO,"Cancel Order Scheduler Completed @ ".concat(new Date().toString()));
    }
}
