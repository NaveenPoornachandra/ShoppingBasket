/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Naveen_P08
 */
@Entity
@Table(name = "SHOPPING_USER_GROUPS", schema = "SHOPPING_BASKET")
public class ShoppingUserGroups implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USER_GROUP")
    private String ugroup;

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the ugroup
     */
    public String getUgroup() {
        return ugroup;
    }

    /**
     * @param ugroup the ugroup to set
     */
    public void setUgroup(String ugroup) {
        this.ugroup = ugroup;
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
        if (!(object instanceof ShoppingUserGroups)) {
            return false;
        }
        ShoppingUserGroups other = (ShoppingUserGroups) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "org.shoppingbasket.entity.ShoppingUserGroups[ id=" + id + " ]";
    }

}
