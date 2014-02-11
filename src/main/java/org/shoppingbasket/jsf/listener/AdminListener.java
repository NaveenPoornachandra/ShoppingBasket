/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.jsf.listener;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

/**
 *
 * @author Naveen_P08
 */
public class AdminListener implements ActionListener{

    @Override
    public void processAction(ActionEvent event) throws AbortProcessingException {
        System.out.println("Action listener here");
    }
    
}
