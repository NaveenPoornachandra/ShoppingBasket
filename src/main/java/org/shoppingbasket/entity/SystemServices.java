/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.entity;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.shoppingbasket.entity.enums.SystemServicesType;
import org.shoppingbasket.jpa.converters.BooleanToIntegerConverter;

/**
 *
 * @author Naveen_P08
 */
@Entity
@Table(name = "SYSTEM_SERVICES",schema = "SHOPPING_BASKET")
@Cacheable(true)
@NamedQueries({
    @NamedQuery(name = "SystemServices.ALL",query = "Select s from SystemServices s"),
    @NamedQuery(name = "SystemServices.ByType",query = "Select s from SystemServices s where s.type=:type"),
    @NamedQuery(name = "SystemServices.ByEnabled",query = "Select s from SystemServices s where s.enabled=true")
})
public class SystemServices implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name = "SERVICE_TYPE")
    @Enumerated(EnumType.STRING)
    private SystemServicesType type;
    
    @Column(name = "ENABLED")
    @Convert(converter = BooleanToIntegerConverter.class)
    private Boolean enabled;
    
     /**
     * @return the type
     */
    public SystemServicesType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(SystemServicesType type) {
        this.type = type;
    }

    /**
     * @return the enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
        if (!(object instanceof SystemServices)) {
            return false;
        }
        SystemServices other = (SystemServices) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.shoppingbasket.entity.SystemServices[ id=" + id + " ]";
    }
    
}
