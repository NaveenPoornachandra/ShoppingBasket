/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.service.finders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.shoppingbasket.entity.IAsset;
import org.shoppingbasket.entity.IAssets;
import org.shoppingbasket.entity.ICost;
import org.shoppingbasket.entity.Item;

/**
 *
 * @author Naveen_P08
 */
public class ItemsCostFinder {

    public static Double findCost(Set<Item> items) {
        return items.parallelStream().map(org.shoppingbasket.service.finders.ITemAssetsFinder::findItemsAssets)
                .findAny().get().parallelStream().mapToDouble((asset) -> asset.getIcost()
                        .getIamount() - (asset.getIcost().getIamount() * asset.getIcost().getIdiscount()) / 100).sum();

    }

    public static void main(String arg[]) {
        Item item = new Item();
        Item item2 = new Item();
        IAssets assets = new IAssets();
        IAssets assets2 = new IAssets();
        IAsset asset1 = new IAsset();
        IAsset asset2 = new IAsset();
        IAsset asset3 = new IAsset();
        ICost cost1 = new ICost();
        cost1.setIamount(10d);
        cost1.setIdiscount(5d);

        ICost cost2 = new ICost();
        cost2.setIamount(20d);
        cost2.setIdiscount(10d);
        
        ICost cost3 = new ICost();
        cost3.setIamount(40d);
        cost3.setIdiscount(10d);
        
        asset3.setId(3l);
        asset3.setIcost(cost3);
        

        asset1.setIcost(cost1);
        asset1.setId(1l);
        asset2.setIcost(cost2);
        asset2.setId(2l);

        assets.setAssets(new HashSet<>());
        assets.getAssets().add(asset1);
        assets.getAssets().add(asset2);
        
        assets2.setAssets(new HashSet<>());
        assets2.getAssets().add(asset3);
        assets2.setId(4l);
        
        item.setId(1l);
        item.setIassets(new HashSet<>());
        item.getIassets().add(assets);
        
        item2.setId(2l);
        item2.setIassets(new HashSet<>());
        item2.getIassets().add(assets2);

        Set<Item> set = new HashSet<>();
        set.add(item);
        set.add(item2);

        List<IAsset> allAsset = new ArrayList<>();

        set.parallelStream().forEach((itm)->itm.getIassets().stream()
                .forEach((assts)->allAsset.addAll(assts.getAssets())));
        
        Double sum = allAsset.parallelStream().mapToDouble((asset) -> asset.getIcost().getIamount() 
                    - (asset.getIcost().getIamount() * asset.getIcost().getIdiscount()) / 100).sum();
        
        System.out.println(findCost(set));
        System.out.println(sum);

    }

}
