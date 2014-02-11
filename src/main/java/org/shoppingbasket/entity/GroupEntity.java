/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Naveen_P08
 */
@Entity
@Table(name = "GROUP_TABLE",schema = "SHOPPING_BASKET")
@NamedQueries({
    @NamedQuery(name = "Group.ALL", query = "Select g from GroupEntity g"),
    @NamedQuery(name = "Group.byUserName", query = "Select g from GroupEntity g"),
    @NamedQuery(name = "Group.byGroupName", query = "Select g from GroupEntity g where g.id in :gnames")})
public class GroupEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "GROUP_NAME")
    private String id;
    
    @JoinColumn(name = "GROUP_OWNER")
    @OneToOne
    private UserEntity groupOwner;
    
    @ManyToMany(mappedBy = "groups")
    private List<UserEntity> users = new ArrayList<>();

    /**
     * @return the gname
     */
    public String getId() {
        return id;
    }

    /**
     * @param gname the gname to set
     */
    public void setId(String gname) {
        this.id = gname;
    }

    /**
     * @return the groupOwner
     */
    public UserEntity getGroupOwner() {
        return groupOwner;
    }

    /**
     * @param groupOwner the groupOwner to set
     */
    public void setGroupOwner(UserEntity groupOwner) {
        this.groupOwner = groupOwner;
    }

    /**
     * @return the users
     */
    public List<UserEntity> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<UserEntity> users) {
        this.users = users;
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
        if (!(object instanceof GroupEntity)) {
            return false;
        }
        GroupEntity other = (GroupEntity) object;
        return (this.getId() != null || other.getId() == null) 
                && (this.getId() == null || this.getId().equals(other.getId()));
    }

    @Override
    public String toString() {
        return "org.learning.jpa.entity.Group[ id=" + getId() + " ]";
    }

}
