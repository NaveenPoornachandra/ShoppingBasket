/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.jsf.listener;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

/**
 *
 * @author Naveen_P08
 */
public class AddAssetListener implements ActionListener{

    @Override
    public void processAction(ActionEvent event) throws AbortProcessingException {
        FacesContext fc = FacesContext.getCurrentInstance();
        
        UIComponent panelGrid = findAssetPanel(fc
                .getViewRoot(),FacesContext.getCurrentInstance());
         
        HtmlOutputLabel assetLabel = new HtmlOutputLabel();
        assetLabel.setValue("Assets Name");
        
        panelGrid.getChildren().add(assetLabel);
        
        ValueExpression assetValue = fc.getApplication().getExpressionFactory()
                .createValueExpression(fc.getELContext(), "#{adminFlow.bean.assetMapId}", Object.class);
        HtmlSelectOneMenu selectOne = new HtmlSelectOneMenu();
        selectOne.setValueExpression("value", assetValue);

        UISelectItems assetItems = new UISelectItems();
        ValueExpression var = fc.getApplication().getExpressionFactory()
                .createValueExpression(fc.getELContext(), "asset", Object.class);
        ValueExpression value = fc.getApplication().getExpressionFactory()
                .createValueExpression(fc.getELContext(), "#{adminFlow.assetList}", Object.class);
        ValueExpression itemValue = fc.getApplication().getExpressionFactory()
                .createValueExpression(fc.getELContext(), "#{asset.assetId}", Object.class);
        ValueExpression itemLabel = fc.getApplication().getExpressionFactory()
                .createValueExpression(fc.getELContext(), "#{asset.assetName}", Object.class);
        assetItems.setValueExpression("var", var);
        assetItems.setValueExpression("value", value);
        assetItems.setValueExpression("itemValue", itemValue);
        assetItems.setValueExpression("itemLabel", itemLabel);
        
        selectOne.getChildren().add(assetItems);
        panelGrid.getChildren().add(selectOne);
    }
    
    public UIComponent findAssetPanel(UIViewRoot root,FacesContext context){
        final UIComponent[] panelComponent = new UIComponent[1];
        root.visitTree(VisitContext.createVisitContext(context), new VisitCallback() {
            @Override
            public VisitResult visit(VisitContext context, UIComponent target) {
                if(target.getId().equalsIgnoreCase("assetGrid")){
                    panelComponent[0] = target;
                }
                return VisitResult.ACCEPT;
            }
        });
        return panelComponent[0];
    }
    
}
