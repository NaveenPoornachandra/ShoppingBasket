/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.entity;

import org.shoppingbasket.entity.enums.AddressType;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Naveen_P08
 */
@Entity
@Table(name = "SHOPPING_USER_ADDRESS", schema = "SHOPPING_BASKET")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ADDRESS_TYPE", discriminatorType = DiscriminatorType.STRING, length = 20)
@NamedQueries({
    @NamedQuery(name = "Address.ByUserName", query = "Select a from ShoppingUserAddress a where a.suser.id=:uname"),
    @NamedQuery(name = "Address.ByUserNameType", query = "Select a from ShoppingUserAddress a where a.suser.id=:uname and a.addressType=:addressType")
   })
public abstract class ShoppingUserAddress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DOOR_NUMBER")
    private Long dnum;

    @Column(name = "LINE_ONE")
    private String line1;

    @Column(name = "LINE_TWO")
    private String line2;

    @Column(name = "STREET")
    private String street;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ZIP_CODE")
    private Long zipCode;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "ADDRESS_TYPE")
    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @JoinColumn(name = "USER_ADDRESS")
    @ManyToOne
    private UserEntity suser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the dnum
     */
    public Long getDnum() {
        return dnum;
    }

    /**
     * @param dnum the dnum to set
     */
    public void setDnum(Long dnum) {
        this.dnum = dnum;
    }

    /**
     * @return the line1
     */
    public String getLine1() {
        return line1;
    }

    /**
     * @param line1 the line1 to set
     */
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    /**
     * @return the line2
     */
    public String getLine2() {
        return line2;
    }

    /**
     * @param line2 the line2 to set
     */
    public void setLine2(String line2) {
        this.line2 = line2;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the zipCode
     */
    public Long getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(Long zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the suser
     */
    public UserEntity getSuser() {
        return suser;
    }

    /**
     * @param suser the suser to set
     */
    public void setSuser(UserEntity suser) {
        this.suser = suser;
    }

    /**
     * @return the addressType
     */
    public AddressType getAddressType() {
        return addressType;
    }

    /**
     * @param addressType the addressType to set
     */
    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
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
        if (!(object instanceof ShoppingUserAddress)) {
            return false;
        }
        ShoppingUserAddress other = (ShoppingUserAddress) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.shoppingbasket.entity.Shopping_User_Address[ id=" + id + " ]";
    }
}
