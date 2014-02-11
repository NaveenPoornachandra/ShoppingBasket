/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import org.shoppingbasket.entity.enums.DeliveryStage;
import org.shoppingbasket.entity.enums.OrderProcessStage;

/**
 *
 * @author Naveen_P08
 */
@Entity
@Table(name = "ITEM_ORDER",schema = "SHOPPING_BASKET")
    @NamedQueries({
    @NamedQuery(name = "IOrder.ALL",query = "Select o from IOrder o"),
    @NamedQuery(name = "IOrder.ById",query = "Select o from IOrder o where o.id=:orderId"),
    @NamedQuery(name = "IOrder.ByUser",query = "Select o from IOrder o where o.iowner.id=:uname"),
    @NamedQuery(name = "IOrder.ByStatus",query = "Select o from IOrder o where o.status.status=:status"),
    @NamedQuery(name = "IOrder.ByStage",query = "Select o from IOrder o where o.processStage=:stage")
})
@SequenceGenerator(name = "ORDER_SEQUENCE",initialValue = 10000,schema = "SHOPPING_BASKET",sequenceName = "ORDER_SEQUENCE")
public class IOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ORDER_SEQUENCE")
    private Long id;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "SUBMIT_DATE")
    @NotNull
    private Date isubmitDate;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "COMPLETION_DATE")
    @NotNull
    private Date icompletionDate;
    
    @JoinColumn(name = "ORDER_OWNER")
    @OneToOne
    @NotNull
    private UserEntity iowner;
    
    @JoinColumn(name = "ITEM_BASKET")
    @OneToOne
    @NotNull
    private IBasket ibasket;
    
    
    @OneToMany(mappedBy = "iorder")
    @NotNull
    private Set<IPayment> ipayments;
    
    @JoinColumn(name = "ORDER_STATUS")
    @ManyToOne
    private IStatus status;
    
    @JoinColumn(name = "ORDER_PROCESS")
    @ManyToOne
    private OrderProcess orderProcess;
    
    @Column(name = "0RDER_PROCESS_STAGE")
    @Enumerated(EnumType.STRING)
    @NotNull
    private OrderProcessStage processStage;
    
    @JoinColumn(name = "ORDER_DELIVERY")
    @ManyToOne
    private DeliveryProcess orderDelivery;
    
    @Column(name = "0RDER_DELIVERY_STAGE")
    @Enumerated(EnumType.STRING)
    @NotNull
    private DeliveryStage deliveryStage;
    
    @Version
    private Long version;

    /**
     * @return the orderId
     */
    public Long getId() {
        return id;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setId(Long orderId) {
        this.id = orderId;
    }

    /**
     * @return the isubmitDate
     */
    public Date getIsubmitDate() {
        return isubmitDate;
    }

    /**
     * @param isubmitDate the isubmitDate to set
     */
    public void setIsubmitDate(Date isubmitDate) {
        this.isubmitDate = isubmitDate;
    }

    /**
     * @return the icompletionDate
     */
    public Date getIcompletionDate() {
        return icompletionDate;
    }

    /**
     * @param icompletionDate the icompletionDate to set
     */
    public void setIcompletionDate(Date icompletionDate) {
        this.icompletionDate = icompletionDate;
    }

    /**
     * @return the iowner
     */
    public UserEntity getIowner() {
        return iowner;
    }

    /**
     * @param iowner the iowner to set
     */
    public void setIowner(UserEntity iowner) {
        this.iowner = iowner;
    }

    /**
     * @return the ibasket
     */
    public IBasket getIbasket() {
        return ibasket;
    }

    /**
     * @param ibasket the ibasket to set
     */
    public void setIbasket(IBasket ibasket) {
        this.ibasket = ibasket;
    }

   /**
     * @return the status
     */
    public IStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(IStatus status) {
        this.status = status;
    }
    
     /**
     * @return the ipayments
     */
    public Set<IPayment> getIpayments() {
        return ipayments;
    }

    /**
     * @param ipayments the ipayments to set
     */
    public void setIpayments(Set<IPayment> ipayments) {
        this.ipayments = ipayments;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IOrder)) {
            return false;
        }
        IOrder other = (IOrder) object;
        return (this.getId() != null || other.getId() == null) && (this.getId() == null || this.getId().equals(other.getId()));
    }

    @Override
    public String toString() {
        return "org.shoppingbasket.entity.IOrder[ id=" + getId() + " ]";
    }

    /**
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * @return the orderProcess
     */
    public OrderProcess getOrderProcess() {
        return orderProcess;
    }

    /**
     * @param orderProcess the orderProcess to set
     */
    public void setOrderProcess(OrderProcess orderProcess) {
        this.orderProcess = orderProcess;
    }

    /**
     * @return the processStage
     */
    public OrderProcessStage getProcessStage() {
        return processStage;
    }

    /**
     * @param processStage the processStage to set
     */
    public void setProcessStage(OrderProcessStage processStage) {
        this.processStage = processStage;
    }

    /**
     * @return the orderDelivery
     */
    public DeliveryProcess getOrderDelivery() {
        return orderDelivery;
    }

    /**
     * @param orderDelivery the orderDelivery to set
     */
    public void setOrderDelivery(DeliveryProcess orderDelivery) {
        this.orderDelivery = orderDelivery;
    }

    /**
     * @return the deliveryStage
     */
    public DeliveryStage getDeliveryStage() {
        return deliveryStage;
    }

    /**
     * @param deliveryStage the deliveryStage to set
     */
    public void setDeliveryStage(DeliveryStage deliveryStage) {
        this.deliveryStage = deliveryStage;
    }
}
