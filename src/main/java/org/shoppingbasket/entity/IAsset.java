/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Naveen_P08
 */
@Entity
@Table(name = "SHOPPING_ITEM_ASSET", schema = "SHOPPING_BASKET")
@NamedQueries({
    @NamedQuery(name = "Asset.ALL", query = "select a from IAsset a"),
    @NamedQuery(name = "Asset.byId", query = "select a from IAsset a where a.assetGroup.id = :assetId"),
    @NamedQuery(name = "Asset.byIds", query = "select a from IAsset a where a.id in :assetIds")})
public class IAsset implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ASSET_ID")
    private Long id;

    @Column(name = "ASSET_NAME")
    private String assetName;
    
    @Column(name = "ASSET_IMAGE")
    @Lob
    private byte[] assetImage;

    @JoinColumn(name = "ASSET_COST")
    @OneToOne(targetEntity = ICost.class,cascade = CascadeType.ALL)
    private ICost icost;

    @Column(name = "ASSET_PRIMARY")
    private Boolean primary;

    @JoinColumn(name = "ASSET_GROUP")
    @ManyToOne(targetEntity = IAssets.class)
    private IAssets assetGroup;
    
    @Column(name = "ASSET_CREATEION_DATE")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date createDate;

    /**
     * @return the assetId
     */
    public Long getId() {
        return id;
    }

    /**
     * @param assetId the assetId to set
     */
    public void setId(Long assetId) {
        this.id = assetId;
    }

    /**
     * @return the assetName
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * @param assetName the assetName to set
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    /**
     * @return the assetImage
     */
    public byte[] getAssetImage() {
        return assetImage;
    }

    /**
     * @param assetImage the assetImage to set
     */
    public void setAssetImage(byte[] assetImage) {
        this.assetImage = assetImage;
    }

    /**
     * @return the icost
     */
    public ICost getIcost() {
        return icost;
    }

    /**
     * @param icost the icost to set
     */
    public void setIcost(ICost icost) {
        this.icost = icost;
    }

    /**
     * @return the primary
     */
    public Boolean getPrimary() {
        return primary;
    }

    /**
     * @param primary the primary to set
     */
    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    /**
     * @return the assetGroup
     */
    public IAssets getAssetGroup() {
        return assetGroup;
    }

    /**
     * @param assetGroup the assetGroup to set
     */
    public void setAssetGroup(IAssets assetGroup) {
        this.assetGroup = assetGroup;
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
        if (!(object instanceof IAsset)) {
            return false;
        }
        IAsset other = (IAsset) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "org.shoppingbasket.entity.IAsset[ id=" + id + " ]";
    }

}
