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
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.shoppingbasket.dao.SystemServicesDAO;
import org.shoppingbasket.entity.SystemServices;
import org.shoppingbasket.entity.enums.SystemServicesType;

/**
 *
 * @author Naveen_P08
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SystemManagementService {
    
    @EJB
    SystemServicesDAO systemDAO;
    
    public boolean isMailEnabled(){
        Map<String,Object> params = new HashMap<>();
        params.put("type", SystemServicesType.MAIL);
        List<SystemServices> services = systemDAO.findWithNamedQuery("SystemServices.ByType",params);
        return (services.size()>0) ? services.get(0).getEnabled(): false;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateMailService(boolean enabled){
         Map<String,Object> params = new HashMap<>();
        params.put("type", SystemServicesType.MAIL);
        List<SystemServices> services = systemDAO.findWithNamedQuery("SystemServices.ByType",params);
        SystemServices service = services.get(0);
        service.setEnabled(enabled);
    }
    
}
