/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.servlet.http.Part;
import org.shoppingbasket.dao.AssetAccessDAO;
import org.shoppingbasket.dao.AssetsAccessDAO;
import org.shoppingbasket.dao.DeliveryProcessDAO;
import org.shoppingbasket.dao.GroupAccessDAO;
import org.shoppingbasket.dao.ItemAccessDAO;
import org.shoppingbasket.dao.OrderProcessDAO;
import org.shoppingbasket.dao.UserAccessDAO;
import org.shoppingbasket.entity.DeliveryProcess;
import org.shoppingbasket.entity.GroupEntity;
import org.shoppingbasket.entity.IAsset;
import org.shoppingbasket.entity.IAssets;
import org.shoppingbasket.entity.Item;
import org.shoppingbasket.entity.OrderProcess;
import org.shoppingbasket.entity.UserEntity;
import org.shoppingbasket.entity.enums.DeliveryStage;
import org.shoppingbasket.entity.enums.OrderProcessStage;
import org.shoppingbasket.util.Constant;

/**
 *
 * @author Naveen_P08
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AdminManagementService {

    @EJB
    private AssetAccessDAO assetDAO;

    @EJB
    private AssetsAccessDAO assetsDAO;

    @EJB
    private ItemAccessDAO itemDAO;

    @EJB
    private UserAccessDAO userDAO;

    @EJB
    private GroupAccessDAO groupDAO;
    
    @EJB
    private OrderProcessDAO processDAO;
    
    @EJB
    private DeliveryProcessDAO deliveryDAO;

    public void registerUser(UserEntity user) {
        UserEntity regUser = userDAO.find(user.getId());
        GroupEntity unRegGrp = groupDAO.find(Constant.GROUP_UN_REG);
        GroupEntity regGrp = groupDAO.find(Constant.GROUP_REG);
        regUser.getGroups().add(regGrp);
        regUser.getGroups().remove(unRegGrp);
    }

    public void unRegisterUser(UserEntity user) {
        UserEntity regUser = userDAO.find(user.getId());
        GroupEntity unRegGrp = groupDAO.find(Constant.GROUP_UN_REG);
        GroupEntity regGrp = groupDAO.find(Constant.GROUP_REG);
        regUser.getGroups().remove(regGrp);
        regUser.getGroups().add(unRegGrp);
    }

    public UserEntity addUserToGroup(String userName, String groupName) {
        UserEntity user = userDAO.find(userName);
        GroupEntity grp = groupDAO.find(groupName);
        user.getGroups().add(grp);
        return user;
    }
    
    public OrderProcess addUserToProcessing(String stage,UserEntity user){
        OrderProcess process = new OrderProcess();
        process.setUser(user);
        process.setProcessStage(OrderProcessStage.valueOf(stage));
        processDAO.create(process);
        return process;
    }
    
     public DeliveryProcess addUserToDelivery(String stage,UserEntity user){
        DeliveryProcess process = new DeliveryProcess();
        process.setUser(user);
        process.setDeliveryStage(DeliveryStage.valueOf(stage));
        deliveryDAO.create(process);
        return process;
    }

    public void remUserFromGroup(String userName, String groupName) {
        UserEntity user = userDAO.find(userName);
        GroupEntity grp = groupDAO.find(groupName);
        user.getGroups().remove(grp);
    }

    public UserEntity getUserByName(String userName) {
        return userDAO.find(userName);
    }

    public void createUser(UserEntity user) {
        userDAO.create(user);
    }

    public void createAsset(IAsset asset, Part assetImage) {
        byte[] imgByte = new byte[100000];
        try {
            BufferedInputStream reader = new BufferedInputStream(assetImage.getInputStream());
            reader.read(imgByte);
            asset.setAssetImage(imgByte);
        } catch (IOException ex) {
            Logger.getLogger(AdminManagementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        asset.setCreateDate(new Date());
        assetDAO.create(asset);
    }

    public void addAssetToAssets(Long assetIds[], IAssets assets) {
        List<IAsset> assetLst;
        Map<String, List<Long>> params = new HashMap<>();
        params.put("assetIds", Arrays.asList(assetIds));
        assetLst = assetDAO.findWithNamedQuery("Asset.byIds", params);
        assets.getAssets().addAll(assetLst);
        for (IAsset asset : assets.getAssets()) {
            asset.setAssetGroup(assets);
        }
    }

    public void addAssetsToItem(Long assetsIds[], Item item) {
        List<IAssets> assetsLst;
        Map<String, List<Long>> params = new HashMap<>();
        params.put("assetsIds", Arrays.asList(assetsIds));
        assetsLst = assetsDAO.findWithNamedQuery("Assets.ByAssetsIds", params);
        item.getIassets().addAll(assetsLst);
        for (IAssets assets : item.getIassets()) {
            assets.setItem(item);
        }
    }

    public void saveAssets(IAssets assets) {

    }

    public void createAssets(IAssets assets) {
        assets.setCreateDate(new Date());
        assetsDAO.create(assets);
    }

    public void createItem(Item item, Part itemImage) {
        byte[] imgByte = new byte[100000];
        try {
            BufferedInputStream reader = new BufferedInputStream(itemImage.getInputStream());
            reader.read(imgByte);
            item.setItemImage(imgByte);
        } catch (IOException ex) {
            Logger.getLogger(AdminManagementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        item.setCreateDate(new Date());
        itemDAO.create(item);
    }

    public List<IAssets> getAllAssets() {
        return assetsDAO.findWithNamedQuery("Assets.ALL");

    }

    public List<Item> getItems() {
        Map<String, Long> params = new HashMap<>();
        params.put("qunatity", 0l);
        return itemDAO.findWithNamedQuery("Item.All",params);
    }

    public List<IAsset> getAllAsset() {
        return assetDAO.findWithNamedQuery("Asset.ALL");
    }

    public List<UserEntity> loadProcessingUser() {
        List<UserEntity> users = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        List<String> grps = new ArrayList<>();
        grps.add("ORDER_PROCESS");
        grps.add("ORDER_QA");
        params.put("gnames", grps);
        List<GroupEntity> groups = groupDAO.findWithNamedQuery("Group.byGroupName", params);
        for (GroupEntity group : groups) {
            users.addAll(group.getUsers());
        }
        return users;
    }

    public List<UserEntity> loadDeliveryUser() {
        List<UserEntity> users = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        List<String> grps = new ArrayList<>();
        grps.add("ORDER_DELIVER");
        params.put("gnames", grps);
        List<GroupEntity> groups = groupDAO.findWithNamedQuery("Group.byGroupName", params);
        for (GroupEntity group : groups) {
            users.addAll(group.getUsers());
        }
        return users;
    }

}
