/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.batch;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.AbstractBatchlet;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import org.shoppingbasket.annotation.Logging;
import org.shoppingbasket.entity.SystemMessage;
import org.shoppingbasket.entity.UserEntity;
import org.shoppingbasket.entity.enums.MessageType;
import org.shoppingbasket.service.AdminManagementService;
import org.shoppingbasket.service.MailManagementService;
import org.shoppingbasket.service.MessageManagementService;
import org.shoppingbasket.service.OrderManagementService;

/**
 *
 * @author Naveen_P08
 */
@Named
public class DeliveryBatchlet extends AbstractBatchlet {

    @Inject
    @Logging
    private Logger logger;

    @EJB
    OrderManagementService service;

    @EJB
    MessageManagementService msgService;

    @EJB
    AdminManagementService adminService;
    
    @EJB
    MailManagementService messageService;

    @Override
    public String process() throws Exception {
        logger.log(Level.INFO, "Batchlet: Delivery -> Started ");
        List<UserEntity> delUsers = adminService.loadDeliveryUser();
        StringBuilder messageBuffer = new StringBuilder("");
        for (UserEntity delUser : delUsers) {
            if (delUser.getDeliveryProcess().size() > 0) {
                SystemMessage message;
                List<SystemMessage> messages = msgService.getMessageByType(delUser.getId(), MessageType.DELIVERY_PROCESS);

                messageBuffer.append("You have ".concat(String.valueOf(delUser.getDeliveryProcess().size())
                        .concat(" orders to deliver")));
                logger.log(Level.INFO, messageBuffer.toString());
                if (messages.size() > 0) {
                    message = messages.get(0);
                    message.setMsgRead(!messageBuffer.toString().equalsIgnoreCase(message.getDescription()));
                    message.setDescription(messageBuffer.toString());
                    msgService.updateMessage(message);
                    if(message.getMsgRead()){
                        messageService.sendMailRemainder(delUser, message.getDescription());
                    }
                } else {
                    msgService.addMessage(delUser, messageBuffer.toString(), MessageType.DELIVERY_PROCESS);
                }
            }
        }
        logger.log(Level.INFO, "Batchlet: Delivery -> Ended ");
        return "COMPLETED";
    }

}
