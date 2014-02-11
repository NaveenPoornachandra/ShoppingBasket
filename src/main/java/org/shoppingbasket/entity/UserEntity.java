/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.Email;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Naveen_P08
 */
@Entity
@Table(name = "USER_TABLE",schema = "SHOPPING_BASKET")
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "User.ALL", query = "Select u from UserEntity u"),
    @NamedQuery(name = "User.byName",query = "Select u from UserEntity u where u.id = :name")})
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "USER_NAME")
    private String id;

    @Column(name = "USER_PASSWORD")
    @NotNull
    private String upassword;
    
    @Transient
    private String cpassword;
    
    @Column(name = "USER_MAIL")
    @NotNull
    @Email
    private String uemail;
    
    @Column(name = "USER_DOB")
    @NotNull
    @Past
    @Temporal(TemporalType.DATE)
    private Date udob;
    
    @OneToMany(mappedBy = "buser")
    private Set<IBasket> ubaskets;
    
    @OneToMany(mappedBy = "suser")
    private Set<DeliveryAddress> daddress;
    
    @OneToMany(mappedBy = "suser")
    private Set<PersonalAddress> paddress;
    
    @OneToOne(mappedBy = "iowner")
    private IOrder iorder;
    
    @OneToMany(mappedBy = "payer")
    private Set<IPayment> iPayments;
    
    @OneToMany(mappedBy = "user")
    private Set<OrderProcess> orderProcess;
    
    @OneToMany(mappedBy = "user")
    private Set<DeliveryProcess> deliveryProcess;

    @ManyToMany
    @JoinTable(name = "USER_GROUP", 
            joinColumns = @JoinColumn(name = "USER_NAME"), 
            inverseJoinColumns = @JoinColumn(name = "GROUP_NAME"))
    private List<GroupEntity> groups = new ArrayList<>();

    /**
     * @return the uname
     */
    public String getId() {
        return id;
    }

    /**
     * @param uname the uname to set
     */
    public void setId(String uname) {
        this.id = uname;
    }

    /**
     * @return the upassword
     */
    public String getUpassword() {
        return upassword;
    }

    /**
     * @param upassword the upassword to set
     */
    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    /**
     * @return the groups
     */
    public List<GroupEntity> getGroups() {
        return groups;
    }

    /**
     * @param groups the groups to set
     */
    public void setGroups(List<GroupEntity> groups) {
        this.groups = groups;
    }
 
    /**
     * @return the uemail
     */
    public String getUemail() {
        return uemail;
    }

    /**
     * @param uemail the uemail to set
     */
    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    /**
     * @return the udob
     */
    public Date getUdob() {
        return udob;
    }

    /**
     * @param udob the udob to set
     */
    public void setUdob(Date udob) {
        this.udob = udob;
    }

    /**
     * @return the ubaskets
     */
    public Set<IBasket> getUbaskets() {
        return ubaskets;
    }

    /**
     * @param ubaskets the ubaskets to set
     */
    public void setUbaskets(Set<IBasket> ubaskets) {
        this.ubaskets = ubaskets;
    }

    /**
     * @return the cpassword
     */
    public String getCpassword() {
        return cpassword;
    }

    /**
     * @param cpassword the cpassword to set
     */
    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
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
    
   /**
     * @return the daddress
     */
    public Set<DeliveryAddress> getDaddress() {
        return daddress;
    }

    /**
     * @param daddress the daddress to set
     */
    public void setDaddress(Set<DeliveryAddress> daddress) {
        this.daddress = daddress;
    }

    /**
     * @return the paddress
     */
    public Set<PersonalAddress> getPaddress() {
        return paddress;
    }

    /**
     * @param paddress the paddress to set
     */
    public void setPaddress(Set<PersonalAddress> paddress) {
        this.paddress = paddress;
    }
    
     /**
     * @return the iPayments
     */
    public Set<IPayment> getiPayments() {
        return iPayments;
    }

    /**
     * @param iPayments the iPayments to set
     */
    public void setiPayments(Set<IPayment> iPayments) {
        this.iPayments = iPayments;
    }
    
    /**
     * @return the orderProcess
     */
    public Set<OrderProcess> getOrderProcess() {
        return orderProcess;
    }

    /**
     * @param orderProcess the orderProcess to set
     */
    public void setOrderProcess(Set<OrderProcess> orderProcess) {
        this.orderProcess = orderProcess;
    }
    
    
    /**
     * @return the deliveryProcess
     */
    public Set<DeliveryProcess> getDeliveryProcess() {
        return deliveryProcess;
    }

    /**
     * @param deliveryProcess the deliveryProcess to set
     */
    public void setDeliveryProcess(Set<DeliveryProcess> deliveryProcess) {
        this.deliveryProcess = deliveryProcess;
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
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        return (this.getId() != null || other.getId() == null) && (this.getId() == null || this.getId().equals(other.getId()));
    }

    @Override
    public String toString() {
        return "org.learning.jpa.entity.User[ id=" + getId() + " ]";
    }
   
}
