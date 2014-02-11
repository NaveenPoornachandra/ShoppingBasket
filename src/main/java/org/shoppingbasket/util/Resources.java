/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.util;

import java.util.Map;
import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.faces.flow.FlowHandler;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.shoppingbasket.annotation.FlowScopeType;
import org.shoppingbasket.annotation.Logging;
import org.shoppingbasket.annotation.UserName;

/**
 *
 * @author Naveen_P08
 */
@Singleton
public class Resources {
    
    @Produces
    @PersistenceContext(unitName = "ShoppingBasket")
    private EntityManager em;
    
    @Produces
    @UserName
    public String getUserName(){
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
    }
    
    
    @Produces
    @Logging
    public Logger getLogger(InjectionPoint ip){
        return Logger.getLogger(ip.getMember().getDeclaringClass().getName());
    }
    
    @Produces
    @FlowScopeType
    public Map<Object,Object> getFlowHandler(){
        return FacesContext.getCurrentInstance().getApplication().getFlowHandler().getCurrentFlowScope();
    }
    
}
