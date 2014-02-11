/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.service;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.el.ELManager;
import javax.el.ELProcessor;
import javax.inject.Inject;
import org.shoppingbasket.dao.BasketAccessDAO;
import org.shoppingbasket.dao.ItemAccessDAO;
import org.shoppingbasket.dao.UserAccessDAO;
import org.shoppingbasket.entity.IBasket;
import org.shoppingbasket.entity.Item;
import org.shoppingbasket.entity.UserEntity;
import org.shoppingbasket.service.finders.ItemsCostFinder;

/**
 *
 * @author Naveen_P08
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ShoppingCartManagementService {

    @Inject
    private ItemAccessDAO itemDAO;

    @Inject
    private BasketAccessDAO cartDAO;

    @Inject
    private UserAccessDAO userDAO;

    private ELProcessor elProcessor;

    private ELManager elManager;

    @PostConstruct
    public void init() {
        elProcessor = new ELProcessor();
        elManager = elProcessor.getELManager();
    }

    public UserEntity getCurrentUser(String unserName) {
        return userDAO.find(unserName);
    }

    public Item getItemById(Long itemId) {
        return itemDAO.find(itemId);
    }

    public void saveBasket(IBasket basket) {
        basket.setUpdateDate(new Date());
        if (basket.getId() == null) {
            basket.setCreateDate(new Date());
            cartDAO.create(basket);
        } else {
            cartDAO.update(basket);
        }
        basket.setUpdateDate(new Date());
        for (Item item : basket.getBitems()) {
            item.setIbasket(basket);
            item.setIquantity(item.getIquantity()-1);
            itemDAO.update(item);
        }
    }

    public List<IBasket> loadUserBaskets(String userName) {
        Map<String, String> params = new HashMap<>();
        params.put("uname", userName);
        List<IBasket> baskets = cartDAO.findWithNamedQuery("IBasket.ByUName", params);
        return baskets;
    }

    public List<Item> loadAllItems() {
         Map<String, Long> params = new HashMap<>();
        params.put("qunatity", 0l);
        List<Item> items = itemDAO.findWithNamedQuery("Item.All",params);
        return items;
    }

    public void proccessUserItems(List<Item> items, Set<Item> basketItems) {
        for (Item item : items) {
            if (basketItems.contains(item)) {
                item.setUserSelected(Boolean.TRUE);
            }
        }
    }

    public Double getbasketCost(IBasket basket) {
        return ItemsCostFinder.findCost(basket.getBitems());
    }
    
    public Double getItemCost(Item item) {
        Set<Item> items = new HashSet<>();
        items.add(item);
        return ItemsCostFinder.findCost(items);
    }

    private Double getbasketCosts(IBasket basket) {
        
        //        List<IAsset> allAsset = new ArrayList<>();
//        basket.getBitems().parallelStream()
//                .forEach((item) -> item.getIassets().stream()
//                        .forEach((assets) -> allAsset.addAll(assets.getAssets())));
//        return allAsset.parallelStream().mapToDouble((asset) -> asset.getIcost().getIamount() 
//                    - (asset.getIcost().getIamount() * asset.getIcost().getIdiscount()) / 100).sum();

        //        return (Double) elProcessor.eval("items.parallelStream()"
//                + ".map(ITemAssetsFinder::findItemsAssets)"
//                + ".reduce(null,null).parallelStream().map((asset)-> asset.getIcost()"
//                + ".getIamount()-asset.getIcost().getIdiscount()).sum()");
//        return (Double) elProcessor.eval("items.parallelStream()"
//                + ".forEach((item) -> item.getIassets()"
//                + ".parallelStream()"
//                + ".forEach((assets) -> assets.getAssets()"
//                + ".parallelStream()"
//                + ".mapToDouble((asset) -> asset.getIcost()"
//                + ".getIamount() - asset.getIcost().getIdiscount()).sum()).sum()).sum()");
//                basket.getBitems().parallelStream()
//                .mapToDouble((Item item) -> item.getIassets()
//                        .parallelStream()
//                        .mapToDouble((IAssets assets) -> assets.getAssets()
//                                .parallelStream()
//                                .mapToDouble((IAsset asset) -> asset.getIcost()
//                                        .getIamount() - asset.getIcost().getIdiscount()).sum()).sum()).sum();
//        basket.getBitems().stream().forEach((Item item) -> {
//            item.getIassets().stream().forEach((assets) -> {
//                assets.getAssets().stream().forEach((asset) -> {
//                    
//                });
//            });
//        });
        return null;
    }
}
