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
import org.shoppingbasket.entity.enums.OrderProcessStage;

/**
 *
 * @author Naveen_P08
 */
@Entity
@Table(name = "ORDER_PROCESS",schema = "SHOPPING_BASKET")
@NamedQueries({
    @NamedQuery(name = "OrderProcess.ALL",query = "Select o from OrderProcess o"),
    @NamedQuery(name = "OrderProcess.ByUser",query = "Select o from OrderProcess o where o.user.id=:uname"),
    @NamedQuery(name = "OrderProcess.ByStage",query = "Select o from OrderProcess o where o.processStage=:stage")
})
public class OrderProcess implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToMany(mappedBy = "orderProcess")
    private Set<IOrder> orderProcess;
    
    @Column(name = "ORDER_PROCESS_STAGE")
    @Enumerated(EnumType.STRING)
    @NotNull
    private OrderProcessStage processStage;
    
    @Version
    private Long version;
    
    @JoinColumn(name = "PROCESS_USER")
    @ManyToOne
    private UserEntity user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    /**
     * @return the orderProcess
     */
    public Set<IOrder> getOrderProcess() {
        return orderProcess;
    }

    /**
     * @param orderProcess the orderProcess to set
     */
    public void setOrderProcess(Set<IOrder> orderProcess) {
        this.orderProcess = orderProcess;
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
        if (!(object instanceof OrderProcess)) {
            return false;
        }
        OrderProcess other = (OrderProcess) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.shoppingbasket.entity.OrderProcess[ id=" + id + " ]";
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
