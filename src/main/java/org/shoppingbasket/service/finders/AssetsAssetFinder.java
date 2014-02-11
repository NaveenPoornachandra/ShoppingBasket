/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.service.finders;

import java.util.List;
import org.shoppingbasket.entity.IAsset;
import org.shoppingbasket.entity.IAssets;

/**
 *
 * @author Naveen_P08
 */
public class AssetsAssetFinder {
     public static List<IAsset> findAssetsAsset(IAssets assets){
        return assets.getAssets().parallelStream().collect(java.util.stream.Collectors.toList());
    }
}
