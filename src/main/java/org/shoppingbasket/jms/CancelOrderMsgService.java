/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.jms;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import org.shoppingbasket.annotation.Logging;

/**
 *
 * @author Naveen_P08
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CancelOrderMsgService {

    @Inject
    @Logging
    Logger logger;

    @Inject @JMSConnectionFactory("jms/ShoppingBasket/cancelOrderQueue")
    JMSContext context;

    @Resource(mappedName = "jms/cancelOrderQueue")
    Queue queue;

    public void submitOrder(String orderId) {
        logger.log(Level.INFO, "Cancelling the Order:".concat(orderId));
        context.createProducer().send(queue, orderId);
    }

    public String recieveOrder() {
        logger.log(Level.INFO, "Recieving the Order the Order");
        return context.createConsumer(queue).receiveBody(String.class);
    }

}
