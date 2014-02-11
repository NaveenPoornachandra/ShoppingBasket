/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.shoppingbasket.entity.enums.MessagePriority;
import org.shoppingbasket.entity.enums.MessageType;

/**
 *
 * @author Naveen_P08
 */
@Entity
@Table(name = "SYSTEM_MESSAGE", schema = "SHOPPING_BASKET")
@NamedQueries({
    @NamedQuery(name = "SystemMessage.ALL", query = "Select s from SystemMessage s"),
    @NamedQuery(name = "SystemMessage.NotRead", query = "Select s from SystemMessage s where s.msgRead=false"),
    @NamedQuery(name = "SystemMessage.NotReadByUser", query = "Select s from SystemMessage s where s.msgRead=false and s.user.id=:uname"),
    @NamedQuery(name = "SystemMessage.ByType", query = "Select s from SystemMessage s where s.user.id=:uname and s.type=:type and s.createdDate between :start and :end")
})
public class SystemMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "MSG_DESC")
    private String description;

    @JoinColumn(name = "MSG_USER")
    @ManyToOne
    private UserEntity user;

    @Column(name = "MSG_PRIORITY")
    @Enumerated(EnumType.STRING)
    private MessagePriority priority;

    @JoinColumn(name = "MSG_ORDER")
    @OneToOne
    private IOrder order;

    @Column(name = "MSG_TYPE")
    @Enumerated(EnumType.STRING)
    private MessageType type;

    @Column(name = "MSG_READ")
    private Boolean msgRead;

    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Column(name = "READ_DATE")
    @Temporal(TemporalType.DATE)
    private Date readDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
     * @return the priority
     */
    public MessagePriority getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(MessagePriority priority) {
        this.priority = priority;
    }

    /**
     * @return the order
     */
    public IOrder getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(IOrder order) {
        this.order = order;
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
        if (!(object instanceof SystemMessage)) {
            return false;
        }
        SystemMessage other = (SystemMessage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.shoppingbasket.entity.SystemMessage[ id=" + id + " ]";
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the readDate
     */
    public Date getReadDate() {
        return readDate;
    }

    /**
     * @param readDate the readDate to set
     */
    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }

    /**
     * @return the msgRead
     */
    public Boolean getMsgRead() {
        return msgRead;
    }

    /**
     * @param msgRead the msgRead to set
     */
    public void setMsgRead(Boolean msgRead) {
        this.msgRead = msgRead;
    }

    /**
     * @return the type
     */
    public MessageType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(MessageType type) {
        this.type = type;
    }

}
