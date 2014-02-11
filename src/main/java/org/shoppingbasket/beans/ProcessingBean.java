/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import org.shoppingbasket.annotation.AdminBeanType;
import org.shoppingbasket.entity.enums.DeliveryStage;
import org.shoppingbasket.entity.enums.OrderProcessStage;

/**
 *
 * @author Naveen_P08
 */
@RequestScoped
@AdminBeanType
public class ProcessingBean {

    private String userName;

    private String[] procStageSelects;

    private String[] delStageSelects;

    private String[] procStages;

    private String[] delStages;

    @PostConstruct
    public void init() {
        procStages = new String[OrderProcessStage.values().length];
        delStages = new String[DeliveryStage.values().length];
        int index = 0;
        for (OrderProcessStage stage : OrderProcessStage.values()) {
            procStages[index++] = stage.name();
        }
        index = 0;
        for (DeliveryStage stage : DeliveryStage.values()) {
            delStages[index++] = stage.name();
        }
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
  

    /**
     * @return the procStages
     */
    public String[] getProcStages() {
        return procStages;
    }

    /**
     * @param procStages the procStages to set
     */
    public void setProcStages(String[] procStages) {
        this.procStages = procStages;
    }

    /**
     * @return the delStages
     */
    public String[] getDelStages() {
        return delStages;
    }

    /**
     * @param delStages the delStages to set
     */
    public void setDelStages(String[] delStages) {
        this.delStages = delStages;
    }

    /**
     * @return the procStageSelects
     */
    public String[] getProcStageSelects() {
        return procStageSelects;
    }

    /**
     * @param procStageSelects the procStageSelects to set
     */
    public void setProcStageSelects(String[] procStageSelects) {
        this.procStageSelects = procStageSelects;
    }

    /**
     * @return the delStageSelects
     */
    public String[] getDelStageSelects() {
        return delStageSelects;
    }

    /**
     * @param delStageSelects the delStageSelects to set
     */
    public void setDelStageSelects(String[] delStageSelects) {
        this.delStageSelects = delStageSelects;
    }

}
