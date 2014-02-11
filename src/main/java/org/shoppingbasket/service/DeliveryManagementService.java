/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.shoppingbasket.dao.AddressAccessDAO;
import org.shoppingbasket.dao.UserAccessDAO;
import org.shoppingbasket.entity.enums.AddressType;
import org.shoppingbasket.entity.DeliveryAddress;
import org.shoppingbasket.entity.PersonalAddress;
import org.shoppingbasket.entity.ShoppingUserAddress;
import org.shoppingbasket.entity.UserEntity;

/**
 *
 * @author Naveen_P08
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DeliveryManagementService {
    
    @EJB
    private UserAccessDAO userDAO;
    
    @EJB
    private AddressAccessDAO addressDAO;
    
    public List<ShoppingUserAddress> listUserAddresses(String userName){
        Map<String,String> params = new HashMap<>();
        params.put("uname", userName);
        return addressDAO.findWithNamedQuery("Address.ByUserName", params);
    }
    
    public DeliveryAddress getUserDeliveryAddress(String userName){
        Map<String,Object> params = new HashMap<>();
        params.put("uname", userName);
        params.put("addressType", AddressType.DELIVERY);
        return (DeliveryAddress) addressDAO.findWithNamedQuery("Address.ByUserNameType", params);
    }
    
    public PersonalAddress getUserPersonalAddress(String userName){
        Map<String,Object> params = new HashMap<>();
        params.put("uname", userName);
        params.put("addressType", AddressType.PERSONAL);
        return (PersonalAddress) addressDAO.findWithNamedQuery("Address.ByUserNameType", params);
    }
    
    public void createDeliveryAddress(DeliveryAddress delivery,String userName){
        delivery.setAddressType(AddressType.DELIVERY);
        UserEntity user = userDAO.find(userName);
        delivery.setSuser(user);
        if(delivery.getId()==null){
            addressDAO.create(delivery);
        } else {
            addressDAO.update(delivery);
        }
    }
    
    public void createPersonalAddress(PersonalAddress personal,String userName){
        personal.setAddressType(AddressType.PERSONAL);
        UserEntity user = userDAO.find(userName);
        personal.setSuser(user);
       
        if(personal.getId()==null){
            addressDAO.create(personal);
        } else {
            addressDAO.update(personal);
        }
    }
    
}
