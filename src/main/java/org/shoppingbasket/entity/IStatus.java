/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.entity;

import org.shoppingbasket.entity.enums.Status;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Naveen_P08
 */
@Entity
@Table(name = "ORDER_STATUS",schema = "SHOPPING_BASKET")
@NamedQueries({
    @NamedQuery(name = "IStatus.ALL",query = "Select s from IStatus s"),
    @NamedQuery(name = "IStatus.ByStatus",query = "Select s from IStatus s where s.status=:status")
})
public class IStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STATUS_ID")
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_CODE")
    private Status status;
    
    @Column(name = "STATUS_DESCRIPTION")
    private String description;
    
     /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof IStatus)) {
            return false;
        }
        IStatus other = (IStatus) object;
        return (this.getId() != null || other.getId() == null) && (this.getId() == null || this.getId().equals(other.getId()));
    }

    @Override
    public String toString() {
        return "org.shoppingbasket.entity.IStatus[ id=" + getId() + " ]";
    }

    
}
