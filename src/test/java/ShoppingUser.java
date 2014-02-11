/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.shoppingbasket.entity.IBasket;
import org.shoppingbasket.entity.ShoppingUserAddress;

/**
 *
 * @author Naveen_P08
 */
@Entity
@Table(name = "USERS_TABLE", schema = "SHOPPING_BASKET")
public class ShoppingUser implements Serializable {
    
    @Id
    @Column(name = "USER_NAME")
    private String uname;

    @Column(name = "USER_PASSWORD")
    @NotNull
    private String upassword;

    @Column(name = "USER_EMAIL")
    @NotNull
    private String uemail;

    @Column(name = "USER_DOB")
    @NotNull
    private Date udob;

    @JoinColumn(name = "USER_BASKETS")
    @OneToMany(targetEntity = IBasket.class)
    private Set<IBasket> baskets;

    @JoinColumn(name = "USER_ADDRESS")
    @OneToMany(targetEntity = ShoppingUserAddress.class)
    private Set<ShoppingUserAddress> uaddress;

    @JoinColumn(name = "USERS",referencedColumnName = "USER_NAME")
    @OneToMany(cascade = {CascadeType.ALL})
    private Set<ShoppingUserGroups> userRoles;

//    @JoinColumn(name="USER_GROUP")
//    @ManyToMany
//    @JoinTable(
//      name="SHOPPING_BASKET.SHOPPING_USER_GROUPS_MAPPING",
//      joinColumns={@JoinColumn(name="USER", referencedColumnName="ID")},
//      inverseJoinColumns={@JoinColumn(name="USER_GROUP", referencedColumnName="ID")})
    /**
     * @return the uname
     */
    
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
     * @return the baskets
     */
    public Set<IBasket> getBaskets() {
        return baskets;
    }

    /**
     * @param baskets the baskets to set
     */
    public void setBaskets(Set<IBasket> baskets) {
        this.baskets = baskets;
    }

    /**
     * @return the uaddress
     */
    public Set<ShoppingUserAddress> getUaddress() {
        return uaddress;
    }

    /**
     * @param uaddress the uaddress to set
     */
    public void setUaddress(Set<ShoppingUserAddress> uaddress) {
        this.uaddress = uaddress;
    }

    /**
     * @return the userRoles
     */
    public Set<ShoppingUserGroups> getUserRoles() {
        return userRoles;
    }

    /**
     * @param userRoles the userRoles to set
     */
    public void setUserRoles(Set<ShoppingUserGroups> userRoles) {
        this.userRoles = userRoles;
    }
    
    /**
     * @return the uname
     */
    public String getUname() {
        return uname;
    }

    /**
     * @param uname the uname to set
     */
    public void setUname(String uname) {
        this.uname = uname;
    }
}
