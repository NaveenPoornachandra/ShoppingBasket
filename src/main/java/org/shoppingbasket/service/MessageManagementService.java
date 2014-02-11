/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.shoppingbasket.dao.SystemMessageDAO;
import org.shoppingbasket.entity.SystemMessage;
import org.shoppingbasket.entity.UserEntity;
import org.shoppingbasket.entity.enums.MessageType;

/**
 *
 * @author Naveen_P08
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class MessageManagementService {

    @EJB
    SystemMessageDAO messageDAO;

    public List<SystemMessage> getMessageByType(String userName, MessageType type) {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.set(Calendar.HOUR_OF_DAY, 0);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);

        endDate.set(Calendar.HOUR_OF_DAY, 11);
        endDate.set(Calendar.MINUTE, 59);
        endDate.set(Calendar.SECOND, 50);

        Map<String, Object> params = new HashMap<>();
        params.put("uname", userName);
        params.put("type", type);
        params.put("start", startDate.getTime());
        params.put("end", endDate.getTime());

        return messageDAO.findWithNamedQuery("SystemMessage.ByType", params);

    }

    public void addMessage(UserEntity user, String message,MessageType type) {
        SystemMessage systemMessage = new SystemMessage();
        systemMessage.setDescription(message);
        systemMessage.setUser(user);
        systemMessage.setCreatedDate(new Date());
        systemMessage.setMsgRead(Boolean.FALSE);
        systemMessage.setType(type);
        messageDAO.create(systemMessage);
    }

    public void updateMessage(SystemMessage systemMessage) {
        messageDAO.update(systemMessage);
    }

    public void deleteMessage(UserEntity user) {

    }

    public List<SystemMessage> readUserMessages(String userName) {
        List<SystemMessage> userMessages;
        Map<String, String> params = new HashMap<>();
        params.put("uname", userName);
        userMessages = messageDAO.findWithNamedQuery("SystemMessage.NotReadByUser", params);
        for (SystemMessage message : userMessages) {
            message.setMsgRead(Boolean.TRUE);
            message.setReadDate(new Date());
        }
        return userMessages;
    }
}
