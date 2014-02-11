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
@Table(name = "SHOPPING_ITEM_ASSETS",schema = "SHOPPING_BASKET")
@NamedQueries({
    @NamedQuery(name = "Assets.ALL",query = "Select a from IAssets a"),
    @NamedQuery(name = "Assets.ByItemId",query = "Select a from IAssets a where a.item.id = :itemId"),
    @NamedQuery(name = "Assets.ByItemName",query = "Select a from IAssets a where a.item.iname=:itemName"),
    @NamedQuery(name = "Assets.ByAssetsIds",query = "Select a from IAssets a where a.id in :assetsIds")
})
public class IAssets implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ASSETS_ID")
    private Long id;
    
    @Column(name = "ASSETS_NAME")
    private String assetsName;
    
    @OneToMany(mappedBy = "assetGroup")
    private Set<IAsset> assets;
    
    @JoinColumn(name = "ASSETS_ITEM")
    @ManyToOne(targetEntity = Item.class)
    private Item item;
    
    @Column(name = "ASSETS_CREATEION_DATE")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date createDate;

    /**
     * @return the assets
     */
    public Set<IAsset> getAssets() {
        return assets;
    }

    /**
     * @param assets the assets to set
     */
    public void setAssets(Set<IAsset> assets) {
        this.assets = assets;
    }
    
    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @return the assetsName
     */
    public String getAssetsName() {
        return assetsName;
    }

    /**
     * @param assetsName the assetsName to set
     */
    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }

    /**
     * @return the assetsId
     */
    public Long getId() {
        return id;
    }

    /**
     * @param assetsId the assetsId to set
     */
    public void setId(Long assetsId) {
        this.id = assetsId;
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
        if (!(object instanceof IAssets)) {
            return false;
        }
        IAssets other = (IAssets) object;
        return (this.id != null || other.id == null) && (this.id.equals(other.id) || this.id == null);
    }

    @Override
    public String toString() {
        return "org.shoppingbasket.entity.IAssets[ id=" + id + " ]";
    }

}
