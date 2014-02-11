/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.managedbeans.flow;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.ejb.EJB;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.shoppingbasket.entity.IBasket;
import org.shoppingbasket.entity.Item;
import org.shoppingbasket.service.ShoppingCartManagementService;

/**
 *
 * @author Naveen_P08
 */
public abstract class BasicFlow {
    @EJB
    private ShoppingCartManagementService service;
    
    public Double basketTotalCost(IBasket basket) {
        return getService().getbasketCost(basket);
    }
    
     public Double itemTotalCost(Item item) {
        return getService().getItemCost(item);
    }
    
    public StreamedContent getStreamedContent(byte[] image) {
        byte imgByte[] = new byte[image.length];
        int index = 0;
        for (Byte img : image){
            imgByte[index++] = img.byteValue();
        }
        InputStream stream = new ByteArrayInputStream(imgByte);
        return new DefaultStreamedContent(stream, "image/jpg");
    }

    /**
     * @return the service
     */
    public ShoppingCartManagementService getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(ShoppingCartManagementService service) {
        this.service = service;
    }

    
}
