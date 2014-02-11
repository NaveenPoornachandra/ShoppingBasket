/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.managebeans.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Naveen_P08
 */
@Named("learnEL")
@RequestScoped
public class LearnExpressionLanguage {
    private List<Integer> lstInt = new ArrayList<>();
    
    public LearnExpressionLanguage(){
        lstInt.add(10);
        lstInt.add(5);
        lstInt.add(7);
        lstInt.add(4);
        
    }

    /**
     * @return the lstInt
     */
    public List<Integer> getLstInt() {
        return lstInt;
    }

    /**
     * @param lstInt the lstInt to set
     */
    public void setLstInt(List<Integer> lstInt) {
        this.lstInt = lstInt;
    }
}
