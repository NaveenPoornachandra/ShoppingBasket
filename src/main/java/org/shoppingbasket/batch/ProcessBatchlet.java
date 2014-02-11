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
public class ProcessBatchlet extends AbstractBatchlet {

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
        logger.log(Level.INFO, "Batchlet: Processing -> Started ");
        List<UserEntity> procUsers = adminService.loadProcessingUser();
        StringBuilder messageBuffer = new StringBuilder("");
        for (UserEntity pocUser : procUsers) {
            if (pocUser.getOrderProcess().size() > 0) {
                SystemMessage message;
                List<SystemMessage> messages = msgService.getMessageByType(pocUser.getId(), MessageType.ORDER_PROCESS);

                messageBuffer.append("You have ".concat(String.valueOf(pocUser.getOrderProcess().size())
                        .concat(" orders to process")));
                logger.log(Level.INFO, messageBuffer.toString());
                if (messages.size() > 0) {
                    message = messages.get(0);
                    message.setMsgRead(!messageBuffer.toString().equalsIgnoreCase(message.getDescription()) && message.getMsgRead());
                    message.setDescription(messageBuffer.toString());
                    msgService.updateMessage(message);
                    if(message.getMsgRead()){
                        messageService.sendMailRemainder(pocUser, message.getDescription());
                    }
                } else {
                    msgService.addMessage(pocUser, messageBuffer.toString(), MessageType.ORDER_PROCESS);
                }
            }
        }

        logger.log(Level.INFO, "Batchlet: Processing -> Ended ");
        return "COMPLETED";
    }

}
