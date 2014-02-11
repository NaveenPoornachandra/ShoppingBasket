/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.service.finders;

import java.util.List;
import org.shoppingbasket.entity.IAsset;
import org.shoppingbasket.entity.Item;

/**
 *
 * @author Naveen_P08
 */
public class ITemAssetsFinder {
     public static List<IAsset> findItemsAssets(Item item){
         return item.getIassets().parallelStream().map(org.shoppingbasket.service.finders.AssetsAssetFinder::findAssetsAsset).findAny().get();
     }
}
