/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.jsf.listener;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

/**
 *
 * @author Naveen_P08
 */
public class SameAsPersonalAddrListener implements ValueChangeListener {

    @Override
    public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {
        UIComponent[] addrPanels = findDeliverAddrPanel();
        if ((Boolean) event.getNewValue()) {
            ((UIInput) addrPanels[1].findComponent("ddnum"))
                    .setValue(((UIInput) addrPanels[0].findComponent("pdnum")).getValue());
            ((UIInput) addrPanels[1].findComponent("dline1"))
                    .setValue(((UIInput) addrPanels[0].findComponent("pline1")).getValue());
            ((UIInput) addrPanels[1].findComponent("dline2"))
                    .setValue(((UIInput) addrPanels[0].findComponent("pline2")).getValue());
            ((UIInput) addrPanels[1].findComponent("dstreet"))
                    .setValue(((UIInput) addrPanels[0].findComponent("pstreet")).getValue());
            ((UIInput) addrPanels[1].findComponent("dcity"))
                    .setValue(((UIInput) addrPanels[0].findComponent("pcity")).getValue());
            ((UIInput) addrPanels[1].findComponent("dzipCode"))
                    .setValue(((UIInput) addrPanels[0].findComponent("pzipCode")).getValue());
            ((UIInput) addrPanels[1].findComponent("dcountry"))
                    .setValue(((UIInput) addrPanels[0].findComponent("pcountry")).getValue());
        }
    }

    public UIComponent[] findDeliverAddrPanel() {
        FacesContext context = FacesContext.getCurrentInstance();
        UIViewRoot root = context.getViewRoot();
        final UIComponent[] panelComponent = new UIComponent[2];
        root.visitTree(VisitContext.createVisitContext(context), new VisitCallback() {

            @Override
            public VisitResult visit(VisitContext context, UIComponent target) {
                if (target.getId().equalsIgnoreCase("personalAddress")) {
                    panelComponent[0] = target;
                } else if (target.getId().equalsIgnoreCase("deliverAddress")) {
                    panelComponent[1] = target;
                }
                return VisitResult.ACCEPT;
            }

        });

//                (VisitContext context1, UIComponent target) -> {
//            if (target.getId().equalsIgnoreCase("personalAddress")) {
//                panelComponent[0] = target;
//            } else if (target.getId().equalsIgnoreCase("deliverAddress")) {
//                panelComponent[1] = target;
//            }
//            return VisitResult.ACCEPT;
//        });
        return panelComponent;
    }

}
