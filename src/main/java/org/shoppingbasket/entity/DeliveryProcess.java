/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import org.shoppingbasket.entity.enums.DeliveryStage;

/**
 *
 * @author Naveen_P08
 */
@Entity
@Table(name = "DELIVERY_PROCESS", schema = "SHOPPING_BASKET")
@NamedQueries({
    @NamedQuery(name = "DeliveryProcess.ALL", query = "Select d from DeliveryProcess d"),
    @NamedQuery(name = "DeliveryProcess.ByUser", query = "Select d from DeliveryProcess d where d.user.id=:uname"),
    @NamedQuery(name = "DeliveryProcess.ByStage", query = "Select d from DeliveryProcess d where d.deliveryStage=:stage")
})
public class DeliveryProcess implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "orderDelivery")
    private Set<IOrder> orderDelivery;

    @Column(name = "DELIVERY_STAGE")
    @Enumerated(EnumType.STRING)
    @NotNull
    private DeliveryStage deliveryStage;

    @JoinColumn(name = "DELIVERY_USER")
    @ManyToOne
    private UserEntity user;
    
    @Version
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
     /**
     * @return the orderDelivery
     */
    public Set<IOrder> getOrderDelivery() {
        return orderDelivery;
    }

    /**
     * @param orderDelivery the orderDelivery to set
     */
    public void setOrderDelivery(Set<IOrder> orderDelivery) {
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

    /**
     * @return the user
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeliveryProcess)) {
            return false;
        }
        DeliveryProcess other = (DeliveryProcess) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.shoppingbasket.entity.DeliveryProcess[ id=" + id + " ]";
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
   
}
