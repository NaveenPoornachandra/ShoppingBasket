/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.beans;

import java.util.HashSet;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import org.shoppingbasket.annotation.AdminBeanType;
import org.shoppingbasket.entity.GroupEntity;
import org.shoppingbasket.entity.IAsset;
import org.shoppingbasket.entity.IAssets;
import org.shoppingbasket.entity.ICost;
import org.shoppingbasket.entity.Item;
import org.shoppingbasket.entity.UserEntity;

/**
 *
 * @author Naveen_P08
 */
@RequestScoped
@AdminBeanType
public class AdminBean{
    
    private Item item;
    
    private IAsset asset;
    
    private IAssets assets;
    
    private ICost cost;
    
    private GroupEntity group;
    
    private UserEntity user;
     
    private Long assetMapId[];
    
    @PostConstruct
    public void init(){
        item = new Item();
        asset = new IAsset();
        asset.setIcost(new ICost());
        assets = new IAssets();
        assets.setAssets(new HashSet<>());
        assetMapId= new Long[15];
    }

    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @return the asset
     */
    public IAsset getAsset() {
        return asset;
    }

    /**
     * @param asset the asset to set
     */
    public void setAsset(IAsset asset) {
        this.asset = asset;
    }

    /**
     * @return the assets
     */
    public IAssets getAssets() {
        return assets;
    }

    /**
     * @param assets the assets to set
     */
    public void setAssets(IAssets assets) {
        this.assets = assets;
    }

    /**
     * @return the cost
     */
    public ICost getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(ICost cost) {
        this.cost = cost;
    }

    /**
     * @return the group
     */
    public GroupEntity getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    /**
     * @return the user
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * @return the assetMapId
     */
    public Long[] getAssetMapId() {
        return assetMapId;
    }

    /**
     * @param assetMapId the assetMapId to set
     */
    public void setAssetMapId(Long assetMapId[]) {
        this.assetMapId = assetMapId;
    }
    
}
