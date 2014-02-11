/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import org.shoppingbasket.annotation.Logging;
import org.shoppingbasket.dao.DeliveryProcessDAO;
import org.shoppingbasket.dao.OrderAccessDAO;
import org.shoppingbasket.dao.OrderProcessDAO;
import org.shoppingbasket.dao.PaymentAccessDAO;
import org.shoppingbasket.dao.StatusAccessDAO;
import org.shoppingbasket.dao.UserAccessDAO;
import org.shoppingbasket.entity.DeliveryProcess;
import org.shoppingbasket.entity.IBasket;
import org.shoppingbasket.entity.IOrder;
import org.shoppingbasket.entity.IPayment;
import org.shoppingbasket.entity.OrderProcess;
import org.shoppingbasket.entity.UserEntity;
import org.shoppingbasket.entity.enums.DeliveryStage;
import org.shoppingbasket.entity.enums.OrderProcessStage;
import org.shoppingbasket.entity.enums.Status;
import org.shoppingbasket.jms.SubmitOrderMsgService;

/**
 *
 * @author Naveen_P08
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class OrderManagementService {

    @Inject
    @Logging
    Logger logger;

    @EJB
    SubmitOrderMsgService messageService;

    @EJB
    OrderAccessDAO orderDAO;

    @EJB
    UserAccessDAO userDAO;

    @EJB
    StatusAccessDAO statusDAO;

    @EJB
    PaymentAccessDAO paymentDAO;

    @EJB
    OrderProcessDAO orderProcessDAO;

    @EJB
    DeliveryProcessDAO deliveryProcessDAO;
    
    @EJB
    MailManagementService mailService;
    
    @Asynchronous
    public Future<IOrder> createOrder(UserEntity user, IBasket basket, IPayment payment) {
        IOrder order = new IOrder();
        logger.log(Level.INFO, "Creating the Order : ");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 5);
        UserEntity userRef = userDAO.find(user.getId());
        order.setIowner(userRef);
        order.setIbasket(basket);
        order.setIcompletionDate(cal.getTime());
        order.setIpayments(basket.getPayments());
        order.setIsubmitDate(new Date());
        order.setProcessStage(OrderProcessStage.NEW);
        order.setDeliveryStage(DeliveryStage.NOTSTARTED);
        Map<String, Object> params = new HashMap<>();
        params.put("status", Status.CREATED);
        order.setStatus(statusDAO.findWithNamedQuery("IStatus.ByStatus", params).get(0));
        orderDAO.create(order);
        payment.setIorder(order);
        mailService.sendOrderConfirmation(order);
        return new AsyncResult<>(order);
    }

    public void updateOrder(IOrder order) {
        logger.log(Level.INFO, "Updating the Order : ".concat(String.valueOf(order.getId())));
        orderDAO.update(order);
    }

    public void submitOrder(IOrder order) {
        logger.log(Level.INFO, "Submitting the Order : ".concat(String.valueOf(order.getId())));
        Map<String, Object> params = new HashMap<>();
        params.put("status", Status.SUBMITTED);
        order.setStatus(statusDAO.findWithNamedQuery("IStatus.ByStatus", params).get(0));
        orderDAO.update(order);
    }

    public void cancelOrder(IOrder order) {
        logger.log(Level.INFO, "Cancelling the Order : ".concat(String.valueOf(order.getId())));
        Map<String, Object> params = new HashMap<>();
        params.put("status", Status.CANCELLED);
        order.setStatus(statusDAO.findWithNamedQuery("IStatus.ByStatus", params).get(0));
        orderDAO.update(order);
    }
    
    
    public IOrder processOrder(IOrder order) {
        logger.log(Level.INFO, "Processing the Order : ".concat(String.valueOf(order.getId())));
        Map<String, Object> params = new HashMap<>();
        params.put("status", Status.PROCESSING);
        this.processStage(order);
        order.setStatus(statusDAO.findWithNamedQuery("IStatus.ByStatus", params).get(0));
        order = orderDAO.update(order);
        return order;

    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void processStage(IOrder order) {
        int ordinal = order.getProcessStage().ordinal();
        int min = Integer.MAX_VALUE;
        OrderProcess orderProcessTmp = null;
        order.setOrderProcess(null);
        
        if (ordinal < OrderProcessStage.values().length) {
            order.setProcessStage(OrderProcessStage.values()[ordinal + 1]);
        }

        if (order.getProcessStage().ordinal() == OrderProcessStage.values().length) {
            return;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("stage", order.getProcessStage());
        List<OrderProcess> orderProcessList = orderProcessDAO.findWithNamedQuery("OrderProcess.ByStage", params);
        for (OrderProcess orderProcess : orderProcessList) {
            if (min > orderProcess.getOrderProcess().size()) {
                min = orderProcess.getOrderProcess().size();
                orderProcessTmp = orderProcess;
            }
        }
        order.setOrderProcess(orderProcessTmp);
        mailService.sendProcessingMail(orderProcessTmp.getUser(), order);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void processDelivery(IOrder order) {
        int ordinal = order.getDeliveryStage().ordinal();
        int min = Integer.MAX_VALUE;
        DeliveryProcess deliveryProcessTmp = null;
        order.setOrderDelivery(null);
        if (ordinal < DeliveryStage.values().length) {
            order.setDeliveryStage(DeliveryStage.values()[ordinal + 1]);
        }
        if (order.getDeliveryStage().ordinal() == DeliveryStage.values().length) {
            return;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("stage", order.getDeliveryStage());
        List<DeliveryProcess> orderProcessList = deliveryProcessDAO.findWithNamedQuery("DeliveryProcess.ByStage", params);
        for (DeliveryProcess orderProcess : orderProcessList) {
            if (min > orderProcess.getOrderDelivery().size()) {
                min = orderProcess.getOrderDelivery().size();
                deliveryProcessTmp = orderProcess;
            }
        }
        order.setOrderDelivery(deliveryProcessTmp);
         mailService.sendDeliveryMail(deliveryProcessTmp.getUser(), order);
    }

    public IOrder deliverOrder(IOrder order) {
        logger.log(Level.INFO, "Delivering the Order : ".concat(String.valueOf(order.getId())));
        Map<String, Object> params = new HashMap<>();
        params.put("status", Status.DELEVERED);
        this.processDelivery(order);
        order.setStatus(statusDAO.findWithNamedQuery("IStatus.ByStatus", params).get(0));
        order = orderDAO.update(order);
        return order;

    }

    public void completeOrder(IOrder order) {
        logger.log(Level.INFO, "Completing the Order : ".concat(String.valueOf(order.getId())));
        Map<String, Object> params = new HashMap<>();
        params.put("status", Status.COMPLETED);
        order.setStatus(statusDAO.findWithNamedQuery("IStatus.ByStatus", params).get(0));
        orderDAO.update(order);
    }

    public List<IOrder> listCreatedOrders() {
        Map<String, Object> params = new HashMap<>();
        params.put("status", Status.CREATED);
        List<IOrder> orders = orderDAO.findWithNamedQuery("IOrder.ByStatus", params);
        return orders;
    }

    public void updateOrders(List<IOrder> orders) {
        orderDAO.updateAll(orders);
    }

    public IOrder findOrder(Long orderId) {
        return orderDAO.find(orderId);
    }

    public List<IOrder> listUserOrders(String userName) {
        logger.log(Level.INFO, "Searching user Orders for user : ".concat(userName));
        Map<String, String> params = new HashMap<>();
        params.put("uname", userName);
        return orderDAO.findWithNamedQuery("IOrder.ByUser", params);
    }

    public IOrder getOrder(Long orderId) {
        logger.log(Level.INFO, "Searching Orders by Id : ".concat(String.valueOf(orderId)));
        return orderDAO.find(orderId);
    }

    public List<IOrder> listProcessingOrders(String userName) {
        List<IOrder> orders = new ArrayList<>();
        Map<String, String> params = new HashMap<>();
        params.put("uname", userName);
        List<OrderProcess> orderProcesses = orderProcessDAO.findWithNamedQuery("OrderProcess.ByUser", params);
        for (OrderProcess orderProcess : orderProcesses) {
            orders.addAll(orderProcess.getOrderProcess());
        }
        return orders;
    }

    public List<IOrder> listDeliveryOrders(String userName) {
        List<IOrder> orders = new ArrayList<>();
        Map<String, String> params = new HashMap<>();
        params.put("uname", userName);
        List<DeliveryProcess> deliveryProcesses = deliveryProcessDAO.findWithNamedQuery("DeliveryProcess.ByUser", params);
        for (DeliveryProcess deliveryProcess : deliveryProcesses) {
            orders.addAll(deliveryProcess.getOrderDelivery());
        }
        return orders;
    }

}
