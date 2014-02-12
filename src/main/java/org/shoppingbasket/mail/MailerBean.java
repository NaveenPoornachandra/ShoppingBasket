/**
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.mail;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.shoppingbasket.annotation.AdminUser;
import org.shoppingbasket.entity.UserEntity;
import org.shoppingbasket.service.SystemManagementService;

/**
 *
 * @author ievans
 */
@Named
@Stateless
public class MailerBean {

    @Resource(name = "mail/shoppingBasket")
    private Session session;
    
    @Inject
    @AdminUser
    private UserEntity admin;
    
    @EJB
    SystemManagementService systemService;

    private static final Logger logger = Logger.getLogger(MailerBean.class.getName());

    @Asynchronous
    public Future<String> sendMessage(String subject, String emailAddr, String messageBody) {
        String status;
        if(!systemService.isMailEnabled()){
           return new AsyncResult<>("Mail service not enabled!");
        }
        try {
            Message message = new MimeMessage(session);
            message.setFrom();
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailAddr, false));
            message.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(admin.getUemail(), false));
            message.setSubject(subject);
            message.setHeader("X-Mailer", "JavaMail");
            Date timeStamp = new Date();
            message.setText(messageBody);
            message.setSentDate(timeStamp);
            Transport.send(message);
            status = "Sent";
            logger.log(Level.INFO, "Mail sent to {0}", emailAddr);
        } catch (MessagingException ex) {
            logger.severe("Error in sending message.");
            status = "Encountered an error: " + ex.getMessage();
            logger.severe(ex.getMessage());
        }
        return new AsyncResult<>(status);
    }

    public static void main(String[] args) {

        final String username = "naveen.poornachandra@gmail.com";
        final String password = "4ps00cs061";

        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "25");
        props.put("mail-smtps-host", "smtp.gmail.com");
        props.put("mail-smtps-password", "4ps00cs061");
        props.put("mail-smtps-socketFactory-class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail-smtps-auth", "true");
        props.put("mail-smtps-socketFactory-port", "465");
        props.put("mail-smtps-port", "465");
        props.put("mail-smtps-starttls-enable", "true");
        props.put("mail.smtps.connectiontimeout", "60000");
        props.put("mail.smtps.timeout", "180000");
        props.put("mail-smtps-user", "naveen.poornachandra@gmail.com");
        props.put("mail-smtps-socketFactory-fallback", "false");
        props.put("mail.imaps.host", "imap.gmail.com");
        props.put("mail.imaps.port", "993");
        props.put("mail-imaps-password", "4ps00cs061");
        props.put("mail.imaps.starttls.enable", "true");
        props.put("mail.imaps.user", "naveen.poornachandra@gmail.com");
        props.put("mail.imaps.socketFactory.fallback", "false");
        props.put("mail.imaps.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.imaps.connectiontimeout", "60000");
        props.put("mail.imaps.timeout", "180000");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("naveen.poornachandra@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("naveen.poornachandra@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
