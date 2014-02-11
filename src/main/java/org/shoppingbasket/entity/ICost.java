/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Naveen_P08
 */
@Entity
@Table(name = "ITEM_COST", schema = "SHOPPING_BASKET")
@NamedQueries({
    @NamedQuery(name = "Cost.ALL", query = "select c from ICost c"),
    @NamedQuery(name = "Cost.ById", query = "select c from ICost c where c.id =:costId")
})
public class ICost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COST_ID")
    private Long id;
    
    @Column(name = "ITEM_AMOUNT")
    @NotNull
    private Double iamount;
    
    @Column(name = "ITEM_DISCOUNT")
    @Range(max = 100,min = 0)
    private Double idiscount;
    
    @OneToOne(mappedBy = "icost")
    private IAsset asset;

    /**
     * @return the iamount
     */
    public Double getIamount() {
        return iamount;
    }

    /**
     * @param iamount the iamount to set
     */
    public void setIamount(Double iamount) {
        this.iamount = iamount;
    }

    /**
     * @return the idiscount
     */
    public Double getIdiscount() {
        return idiscount;
    }

    /**
     * @param idiscount the idiscount to set
     */
    public void setIdiscount(Double idiscount) {
        this.idiscount = idiscount;
    }
    
     /**
     * @return the asset
     */
    public IAsset getAsset() {
        return asset;
    }

    /**
     * @param asset the asset to set
     */
    public void setAsset(IAsset asset) {
        this.asset = asset;
    }

    /**
     * @return the costId
     */
    public Long getId() {
        return id;
    }

    /**
     * @param costId the costId to set
     */
    public void setId(Long costId) {
        this.id = costId;
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
        if (!(object instanceof ICost)) {
            return false;
        }
        ICost other = (ICost) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "org.shoppingbasket.entity.ICost[ id=" + id + " ]";
    }
}
