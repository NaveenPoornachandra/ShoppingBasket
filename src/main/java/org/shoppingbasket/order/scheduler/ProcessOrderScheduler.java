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
import org.shoppingbasket.entity.enums.OrderProcessStage;
import org.shoppingbasket.jms.CancelOrderMsgService;
import org.shoppingbasket.jms.DeliveryOrderMsgService;
import org.shoppingbasket.jms.ProcessOrderMsgService;
import org.shoppingbasket.service.OrderManagementService;

/**
 *
 * @author Naveen_P08
 */
@Startup
@Singleton
public class ProcessOrderScheduler {

    @Resource
    SessionContext ctx;

    @Inject
    @Logging
    Logger logger;

    @EJB
    private DeliveryOrderMsgService msgSender;

    @EJB
    private CancelOrderMsgService msgSender2;

    @EJB
    private ProcessOrderMsgService msgReciever;

    @EJB
    private OrderManagementService orderService;

    @Schedule(hour = "*", minute = "*/1", second = "20", info = "Every 9 second timer")
    public void process() {
        logger.log(Level.INFO, "Process Order Scheduler started......");
        String orderId = msgReciever.recieveOrder();
        if (orderId != null && StringUtils.isNumeric(orderId)) {
            IOrder order = orderService.findOrder(Long.valueOf(orderId));
            if (order.getProcessStage().equals(OrderProcessStage.NEW)) {
                orderService.processStage(order);
                msgReciever.submitOrder(orderId);
            } else if (order.getProcessStage().equals(OrderProcessStage.COMPLETING)) {
                if (order.getId() % 2 == 0) {
                    msgSender2.submitOrder(orderId);
                } else {
                    msgSender.submitOrder(orderId);
                }
            } else {
                msgReciever.submitOrder(orderId);
            }
        }
        logger.log(Level.INFO, "Process Order Scheduler done @ Time ".concat(new Date().toString()));

    }

}
