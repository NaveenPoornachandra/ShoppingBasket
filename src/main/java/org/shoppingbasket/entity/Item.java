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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Naveen_P08
 */
@Entity
@Table(name = "SHOPPING_ITEM",schema = "SHOPPING_BASKET")
@NamedQueries({
    @NamedQuery(name = "Item.All",query = "Select i from Item i where i.iquantity > :qunatity"),
    @NamedQuery(name = "Item.ByID",query = "Select i from Item i where i.id=:itemId")
})
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ITEM_ID")
    private Long id;
    
    @Column(name = "ITEM_NAME")
    private String iname;
    
    @Column(name = "ITEM_ENABLED")
    private Boolean ienabled = true;
    
    @OneToMany(mappedBy = "item")
    private Set<IAssets> iassets;
    
    @JoinColumn(name = "ITEM_BASKET")
    @ManyToOne(targetEntity = IBasket.class)
    private IBasket ibasket;
    
    @Column(name = "ITEM_CREATEION_DATE")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    
    @Column(name = "ITEM_QUANTITY")
    private Long iquantity;
    
    @Transient
    private Boolean userSelected;
    
    @Transient
    private Integer qunatity;
    
    @Column(name = "ITEM_IMAGE")
    @Lob
    private byte[] itemImage;

    /**
     * @return the iname
     */
    public String getIname() {
        return iname;
    }

    /**
     * @param iname the iname to set
     */
    public void setIname(String iname) {
        this.iname = iname;
    }

    /**
     * @return the iassets
     */
    public Set<IAssets> getIassets() {
        return iassets;
    }

    /**
     * @param iassets the iassets to set
     */
    public void setIassets(Set<IAssets> iassets) {
        this.iassets = iassets;
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
     * @return the itemId
     */
    public Long getId() {
        return id;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setId(Long itemId) {
        this.id = itemId;
    }
    
    /**
     * @return the ienabled
     */
    public Boolean getIenabled() {
        return ienabled;
    }

    /**
     * @param ienabled the ienabled to set
     */
    public void setIenabled(Boolean ienabled) {
        this.ienabled = ienabled;
    }

    /**
     * @return the userSelected
     */
    public Boolean getUserSelected() {
        return userSelected;
    }

    /**
     * @param userSelected the userSelected to set
     */
    public void setUserSelected(Boolean userSelected) {
        this.userSelected = userSelected;
    }
    
    
    /**
     * @return the qunatity
     */
    public Integer getQunatity() {
        return qunatity;
    }

    /**
     * @param qunatity the qunatity to set
     */
    public void setQunatity(Integer qunatity) {
        this.qunatity = qunatity;
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
    
    /**
     * @return the itemImage
     */
    public byte[] getItemImage() {
        return itemImage;
    }

    /**
     * @param itemImage the itemImage to set
     */
    public void setItemImage(byte[] itemImage) {
        this.itemImage = itemImage;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }
    
     @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "org.shoppingbasket.entity.Item[ id=" + id + " ]";
    }

    /**
     * @return the iquantity
     */
    public Long getIquantity() {
        return iquantity;
    }

    /**
     * @param iquantity the iquantity to set
     */
    public void setIquantity(Long iquantity) {
        this.iquantity = iquantity;
    }

}
