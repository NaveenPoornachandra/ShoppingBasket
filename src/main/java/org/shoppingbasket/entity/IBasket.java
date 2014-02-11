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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Naveen_P08
 */
@Entity
@Table(name = "SHOPPING_BASKET",schema = "SHOPPING_BASKET")
@NamedQueries({
@NamedQuery(name = "IBasket.ALL",query = "Select b from IBasket b"),
@NamedQuery(name = "IBasket.ById",query = "Select b from IBasket b where b.id=:id"),
@NamedQuery(name = "IBasket.ByUName",query = "Select b from IBasket b where b.buser.id=:uname")
})
public class IBasket implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BASKET_ID")
    private Long id;
    
    @Column(name = "BASKET_UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date updateDate;
    
    @Column(name = "BASKET_CREATEION_DATE")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date createDate;
    
    @OneToMany(mappedBy = "ibasket")
    private Set<Item> bitems;
    
    @JoinColumn(name = "USER_BASKET")
    @ManyToOne
    private UserEntity buser;
    
    @OneToMany(mappedBy = "basket")
    private Set<IPayment> payments;
    
    /**
     * @return the bitems
     */
    public Set<Item> getBitems() {
        return bitems;
    }

    /**
     * @param bitems the bitems to set
     */
    public void setBitems(Set<Item> bitems) {
        this.bitems = bitems;
    }
    
    /**
     * @return the buser
     */
    public UserEntity getBuser() {
        return buser;
    }

    /**
     * @param buser the buser to set
     */
    public void setBuser(UserEntity buser) {
        this.buser = buser;
    }
    
    /**
     * @return the basketId
     */
    public Long getId() {
        return id;
    }

    /**
     * @param basketId the basketId to set
     */
    public void setId(Long basketId) {
        this.id = basketId;
    }
    
    
    /**
     * @return the updateDate
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate the updateDate to set
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
        if (!(object instanceof IBasket)) {
            return false;
        }
        IBasket other = (IBasket) object;
        if ((this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id))) {
        } else {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.shoppingbasket.entity.IBasket[ id=" + id + " ]";
    }

    /**
     * @return the payments
     */
    public Set<IPayment> getPayments() {
        return payments;
    }

    /**
     * @param payments the payments to set
     */
    public void setPayments(Set<IPayment> payments) {
        this.payments = payments;
    }

}
