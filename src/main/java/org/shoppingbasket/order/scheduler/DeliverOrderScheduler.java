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
import org.shoppingbasket.entity.enums.DeliveryStage;
import org.shoppingbasket.entity.enums.OrderProcessStage;
import org.shoppingbasket.jms.CompleteOrderMsgService;
import org.shoppingbasket.jms.DeliveryOrderMsgService;
import org.shoppingbasket.service.OrderManagementService;

/**
 *
 * @author Naveen_P08
 */
@Startup
@Singleton
public class DeliverOrderScheduler {

    @Resource
    SessionContext ctx;

    @Inject
    @Logging
    Logger logger;

    @EJB
    private DeliveryOrderMsgService msgReciever;

    @EJB
    private CompleteOrderMsgService msgSender;

    @EJB
    private OrderManagementService orderService;

    @Schedule(hour = "*", minute = "*/1", second = "30", info = "Every 7 second timer")
    public void process() {
        logger.log(Level.INFO, "Deliver Order Scheduler started......");
        String orderId = msgReciever.recieveOrder();
        if (orderId != null && StringUtils.isNumeric(orderId)) {
            IOrder order = orderService.findOrder(Long.valueOf(orderId));
            if (order.getProcessStage().equals(OrderProcessStage.COMPLETING)
                    && order.getDeliveryStage().equals(DeliveryStage.NOTSTARTED)) {
                orderService.processDelivery(order);
                msgReciever.submitOrder(orderId);
            } else if (order.getDeliveryStage().equals(DeliveryStage.COMPLETE)) {
                msgSender.submitOrder(orderId);
            } else {
                msgReciever.submitOrder(orderId);
            }
        }
        logger.log(Level.INFO, "Deliver Order Scheduler Completed @ ".concat(new Date().toString()));

    }

}
