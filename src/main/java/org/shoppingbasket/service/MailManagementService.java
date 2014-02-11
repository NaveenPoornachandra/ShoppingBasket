/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import org.shoppingbasket.annotation.Logging;
import org.shoppingbasket.entity.IOrder;
import org.shoppingbasket.entity.Item;
import org.shoppingbasket.entity.ShoppingUserAddress;
import org.shoppingbasket.entity.UserEntity;
import org.shoppingbasket.mail.MailerBean;

/**
 *
 * @author Naveen_P08
 */
@Stateless
public class MailManagementService {

    @EJB
    MailerBean mailerBean;

    @EJB
    DeliveryManagementService deliveryService;

    @Inject
    @Logging
    Logger logger;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void sendProcessingMail(UserEntity user, IOrder order) {
        logger.log(Level.INFO, "Sending processing mail to:".concat(user.getId()));
        StringBuilder messageBody = new StringBuilder();
        messageBody.append("You gave order with id :").append(order.getId().toString());
        mailerBean.sendMessage("Importent! Order need to be processed", user.getUemail(), messageBody.toString());
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void sendDeliveryMail(UserEntity user, IOrder order) {
        logger.log(Level.INFO, "Sending delivering mail to:".concat(user.getId()));
        StringBuilder messageBody = new StringBuilder();
        messageBody.append("You gave order with id :").append(order.getId().toString());
        mailerBean.sendMessage("Importent! Order need to be delivered", user.getUemail(), messageBody.toString());
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void sendMailRemainder(UserEntity user, String message) {
        logger.log(Level.INFO, "Sending mail remainder to:".concat(user.getId()));
        mailerBean.sendMessage("Importent! Remainder mail ", user.getUemail(), message);
    }

    public void sendOrderConfirmation(IOrder order) {
        logger.log(Level.INFO, "Sending mail for Order confirmation :".concat(order.getIowner().getId()));
        StringBuilder message = new StringBuilder();
        ShoppingUserAddress address = deliveryService.getUserDeliveryAddress(order.getIowner().getId()).get(0);
        message.append("Your order has been placed successfully.")
                .append("Please find the order number for your future references (")
                .append(order.getId()).append(")\n")
                .append("\n Items ordered are as follows : \n");
        for (Item item : order.getIbasket().getBitems()) {
            message.append(item.getIname())
                    .append("\n");
        }

        message.append("\n Please confirm your delivery address which is mentioned below;\n")
                .append(address.getDnum())
                .append("\n")
                .append(address.getLine1())
                .append("\n")
                .append(address.getLine2())
                .append("\n")
                .append(address.getStreet())
                .append("\n")
                .append(address.getCity())
                .append("\n")
                .append(address.getZipCode())
                .append("\n")
                .append(address.getCountry())
                .append("\n \n If you find any issues with the above address, please contact our representatives immediately.");
        mailerBean.sendMessage("Important! Order Confirmation ", order.getIowner().getUemail(), message.toString());
    }

}
