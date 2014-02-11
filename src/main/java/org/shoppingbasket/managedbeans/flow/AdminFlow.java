/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.managedbeans.flow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.shoppingbasket.annotation.AdminBeanType;
import org.shoppingbasket.annotation.FlowScopeType;
import org.shoppingbasket.annotation.Logging;
import org.shoppingbasket.annotation.ShoppingCartType;
import org.shoppingbasket.beans.AdminBean;
import org.shoppingbasket.beans.ProcessingBean;
import org.shoppingbasket.beans.ShoppingCart;
import org.shoppingbasket.entity.IAsset;
import org.shoppingbasket.entity.IAssets;
import org.shoppingbasket.entity.IOrder;
import org.shoppingbasket.entity.Item;
import org.shoppingbasket.entity.UserEntity;
import org.shoppingbasket.service.AdminManagementService;

/**
 *
 * @author Naveen_P08
 */
@Named
@FlowScoped("admin")
public class AdminFlow implements Serializable {
    
    @Inject
    @Logging
    private Logger logger;
    
    private boolean ierror;
    
    @Inject
    @AdminBeanType
    private AdminBean bean;
    
    @Inject
    @AdminBeanType
    private ProcessingBean processing;
    
    @Inject
    @ShoppingCartType
    private ShoppingCart cart;
    
    private UserEntity user;
   
    @EJB
    private AdminManagementService service;
    
    private List<UserEntity> processingUsers;
    
    private List<UserEntity> deliveryUsers;
    
    private List<IOrder> processingOrders;
    
     private List<IOrder>deliveryOrders;
  

    private List<IAssets> assetsList = new ArrayList<>();

    private List<Item> itemList = new ArrayList<>();

    private List<IAsset> assetList = new ArrayList<>();
    
    private Part assetImage;
    
    private Part itemImage;
      
    private String processingType;
    
    @Inject
    @FlowScopeType
    Map<Object,Object> currentFlow;
    
    public void init(){
        if (currentFlow.get("cart") != null) {
            this.cart = (ShoppingCart) currentFlow.get("cart");
        }
        if (currentFlow.get("user") != null) {
            this.user = (UserEntity) currentFlow.get("user");
        }
        processingType = "processing";
    }
 
    public void createAsset(){
        this.ierror = true;
        logger.log(Level.INFO,"Creating Asset");
        service.createAsset(bean.getAsset(),assetImage);
        this.ierror = false;
        this.assetList = service.getAllAsset();
    }
    
    public void createAssets(){
        logger.log(Level.INFO,"Creating Assets");
       service.createAssets(bean.getAssets());
    }
    
    public void addAssets(){
        if(bean.getAssets().getId()== null){
            service.createAssets(bean.getAssets());
        }
        service.addAssetToAssets(bean.getAssetMapId(), bean.getAssets());
        this.assetsList = service.getAllAssets();
    }
    
    public void addItem(){
        if(bean.getItem().getId() == null){
            service.createItem(bean.getItem(),itemImage);
        }
        service.addAssetsToItem(bean.getAssetMapId(), bean.getItem());
        this.itemList = service.getItems();
    }
   
    public String getLogout() {
        return "/logout";
    }

    public String getAllAssets() {
        this.setAssetList(service.getAllAsset());
        this.setAssetsList(service.getAllAssets());
        return "manageAssets";
    }

    public String getAllItems() {
        this.itemList = service.getItems();
        this.assetsList = service.getAllAssets();
        return "manageItem";
    }

    public String getAllAsset() {
        this.setAssetList(service.getAllAsset());
        return "manageAsset";
    }
    
    public String manageProcessingUsers(){
        this.loadProcessingUsers();
        this.loadDeliveryUsers();
        this.processingType="processing";
        return "manageProcessing";
    }

    /**
     * @return the assetsList
     */
    public List<IAssets> getAssetsList() {
        return assetsList;
    }

    /**
     * @param assetsList the assetsList to set
     */
    public void setAssetsList(List<IAssets> assetsList) {
        this.assetsList = assetsList;
    }

    /**
     * @return the itemList
     */
    public List<Item> getItemList() {
        return itemList;
    }

    /**
     * @param itemList the itemList to set
     */
    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    /**
     * @return the bean
     */
    public AdminBean getBean() {
        return bean;
    }

    /**
     * @param bean the bean to set
     */
    public void setBean(AdminBean bean) {
        this.bean = bean;
    }

    /**
     * @return the assetList
     */
    public List<IAsset> getAssetList() {
        return assetList;
    }

    /**
     * @param assetList the assetList to set
     */
    public void setAssetList(List<IAsset> assetList) {
        this.assetList = assetList;
    }

    /**
     * @return the assetImage
     */
    public Part getAssetImage() {
        return assetImage;
    }

    /**
     * @param assetImage the assetImage to set
     */
    public void setAssetImage(Part assetImage) {
        this.assetImage = assetImage;
    }

    /**
     * @return the ierror
     */
    public boolean isIerror() {
        return ierror;
    }

    /**
     * @param ierror the ierror to set
     */
    public void setIerror(boolean ierror) {
        this.ierror = ierror;
    }

    /**
     * @return the itemImage
     */
    public Part getItemImage() {
        return itemImage;
    }

    /**
     * @param itemImage the itemImage to set
     */
    public void setItemImage(Part itemImage) {
        this.itemImage = itemImage;
    }
    
    public void loadProcessingUsers(){
        this.setProcessingUsers(service.loadProcessingUser());
       
    }
    
    public void loadDeliveryUsers(){
        this.setDeliveryUsers(service.loadDeliveryUser());
     }
    
    public void addProcessingUser(){
        UserEntity user = this.service.addUserToGroup(getProcessing().getUserName(), "ORDER_PROCESS");
        for(String stage:this.processing.getProcStageSelects()){
            this.service.addUserToProcessing(stage, user);
        }
        this.loadProcessingUsers();
    }
    
    public void remProcessingUser(String userName){
        this.service.remUserFromGroup(userName, "ORDER_PROCESS");
        this.loadProcessingUsers();
    }
    
     public void addDeliveryUser(){
         UserEntity user = this.service.addUserToGroup(getProcessing().getUserName(), "ORDER_DELIVER");
         for(String stage:this.processing.getDelStageSelects()){
            this.service.addUserToDelivery(stage, user);
        }
         this.loadDeliveryUsers();
    }
    
    public void remDeliveryUser(String userName){
        this.service.remUserFromGroup(userName, "ORDER_DELIVER");
        this.loadDeliveryUsers();
    }

    /**
     * @return the processingType
     */
    public String getProcessingType() {
        return processingType;
    }

    /**
     * @param processingType the processingType to set
     */
    public void setProcessingType(String processingType) {
        this.processingType = processingType;
    }
    
     public void processingTypeChange(ValueChangeEvent event) throws AbortProcessingException {
        this.setProcessingType(String.valueOf(event.getNewValue()));
    }

    /**
     * @return the processingUsers
     */
    public List<UserEntity> getProcessingUsers() {
        return processingUsers;
    }

    /**
     * @param processingUsers the processingUsers to set
     */
    public void setProcessingUsers(List<UserEntity> processingUsers) {
        this.processingUsers = processingUsers;
    }

    /**
     * @return the deliveryUsers
     */
    public List<UserEntity> getDeliveryUsers() {
        return deliveryUsers;
    }

    /**
     * @param deliveryUsers the deliveryUsers to set
     */
    public void setDeliveryUsers(List<UserEntity> deliveryUsers) {
        this.deliveryUsers = deliveryUsers;
    }

    /**
     * @return the processingOrders
     */
    public List<IOrder> getProcessingOrders() {
        return processingOrders;
    }

    /**
     * @param processingOrders the processingOrders to set
     */
    public void setProcessingOrders(List<IOrder> processingOrders) {
        this.processingOrders = processingOrders;
    }

    /**
     * @return the deliveryOrders
     */
    public List<IOrder> getDeliveryOrders() {
        return deliveryOrders;
    }

    /**
     * @param deliveryOrders the deliveryOrders to set
     */
    public void setDeliveryOrders(List<IOrder> deliveryOrders) {
        this.deliveryOrders = deliveryOrders;
    }

    /**
     * @return the processing
     */
    public ProcessingBean getProcessing() {
        return processing;
    }

    /**
     * @param processing the processing to set
     */
    public void setProcessing(ProcessingBean processing) {
        this.processing = processing;
    }
}
