/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.entity;

import org.shoppingbasket.entity.enums.PaymentMode;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Naveen_P08
 */
@Entity
@Cacheable(false)
@Table(name = "SHOPPING_PAYMENT",schema = "SHOPPING_BASKET")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PAYMENT_TYPE",discriminatorType = DiscriminatorType.STRING,length = 20)
@NamedQueries({
    @NamedQuery(name = "IPayment.ALL",query = "Select p from IPayment p"),
    @NamedQuery(name = "IPayment.ByMode",query = "Select p from IPayment p where p.paymentMode=:paymentMode"),
    @NamedQuery(name = "IPayment.ByUser",query = "Select p from IPayment p where p.payer.id=:uname"),
    @NamedQuery(name = "IPayment.ByOrder",query = "Select p from IPayment p where p.iorder.id=:orderId")
})
public abstract class IPayment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PAYMENT_ID")
    private Long id;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "PAYMENT_DATE")
    @NotNull
    private Date paymentDate;
    
    @Column(name = "PAYMENT_MODE")
    @Enumerated(EnumType.STRING)
    @NotNull
    private PaymentMode paymentMode;
    
    @JoinColumn(name = "PAYER")
    @ManyToOne
    @NotNull
    private UserEntity payer;
    
    @JoinColumn(name = "PAYMENT_BASKET")
    @ManyToOne
    @NotNull
    private IBasket basket;
    
    @JoinColumn(name = "ORDER_PAYMENT")
    @ManyToOne
    private IOrder iorder;

    public Long getId() {
        return getPaymentId();
    }

    public void setId(Long id) {
        this.setPaymentId(id);
    }

    /**
     * @return the paymentId
     */
    public Long getPaymentId() {
        return id;
    }

    /**
     * @param paymentId the paymentId to set
     */
    public void setPaymentId(Long paymentId) {
        this.id = paymentId;
    }

    /**
     * @return the paymentDate
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * @param paymentDate the paymentDate to set
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * @return the paymentMode
     */
    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    /**
     * @param paymentMode the paymentMode to set
     */
    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }
    
     /**
     * @return the payer
     */
    public UserEntity getPayer() {
        return payer;
    }

    /**
     * @param payer the payer to set
     */
    public void setPayer(UserEntity payer) {
        this.payer = payer;
    }
    
    /**
     * @return the iorder
     */
    public IOrder getIorder() {
        return iorder;
    }

    /**
     * @param iorder the iorder to set
     */
    public void setIorder(IOrder iorder) {
        this.iorder = iorder;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getPaymentId() != null ? getPaymentId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IPayment)) {
            return false;
        }
        IPayment other = (IPayment) object;
        return (this.getPaymentId() != null || other.getPaymentId() == null) && (this.getPaymentId() == null || this.getPaymentId().equals(other.getPaymentId()));
    }

    @Override
    public String toString() {
        return "org.shoppingbasket.entity.IPayment[ id=" + getPaymentId() + " ]";
    }

    /**
     * @return the basket
     */
    public IBasket getBasket() {
        return basket;
    }

    /**
     * @param basket the basket to set
     */
    public void setBasket(IBasket basket) {
        this.basket = basket;
    }
    
}
