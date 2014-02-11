/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shoppingbasket.managedbeans.flow;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.shoppingbasket.annotation.FlowScopeType;
import org.shoppingbasket.annotation.ShoppingCartType;
import org.shoppingbasket.beans.ShoppingCart;
import org.shoppingbasket.entity.DeliveryAddress;
import org.shoppingbasket.entity.IAsset;
import org.shoppingbasket.entity.IBasket;
import org.shoppingbasket.entity.Item;
import org.shoppingbasket.entity.PersonalAddress;
import org.shoppingbasket.entity.ShoppingUserAddress;
import org.shoppingbasket.entity.SystemMessage;
import org.shoppingbasket.entity.UserEntity;
import org.shoppingbasket.entity.enums.AddressType;
import org.shoppingbasket.service.DeliveryManagementService;
import org.shoppingbasket.service.MessageManagementService;

/**
 *
 * @author Naveen_P08
 */
@Named
@FlowScoped("shopping")
public class ShoppingFlow extends BasicFlow implements Serializable {

    @Inject
    @ShoppingCartType
    private ShoppingCart cart;

    @EJB
    private DeliveryManagementService deliveryService;

    @EJB
    private MessageManagementService messageService;

    private List<Item> items;

    private UserEntity user;

    private List<SystemMessage> messages;

    @Inject
    @FlowScopeType
    Map<Object, Object> currentFlow;

    @PostConstruct
    public void init() {
        if (currentFlow.get("cart") != null) {
            this.cart = (ShoppingCart) currentFlow.get("cart");
        }
        if (currentFlow.get("user") != null) {
            this.user = (UserEntity) currentFlow.get("user");
        }
        user = getService().getCurrentUser(getCurrentUser());
        cart.setUserBaskets(getService().loadUserBaskets(user.getId()));
        setItems(getService().loadAllItems());
        messages = messageService.readUserMessages(user.getId());
    }

    public IAsset getPrimaryAsset(Item item) {
        Iterator<IAsset> assetItr = item.getIassets().iterator().next().getAssets().iterator();
        IAsset primaryAsset = null;
        while (assetItr.hasNext()) {
            IAsset asset = assetItr.next();
            primaryAsset = asset;
            if (asset.getPrimary()) {
                return asset;
            }
        }
        return primaryAsset;
    }

    public void loadActiveBasket(Long basketId) {
        this.items = getService().loadAllItems();
        for (IBasket basket : cart.getUserBaskets()) {
            if (basket.getId().compareTo(basketId) == 0) {
                cart.setBasket(basket);
            }
        }
        getService().proccessUserItems(items, cart.getBasket().getBitems());
    }

    public String getCurrentUser() {
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
    }

    public void clearBasket() {
        cart.getBasket().getBitems().clear();
    }

    public void addItemToCart(Item item) {
        cart.getBasket().getBitems().add(item);
        item.setUserSelected(true);
    }

    public void addItem1ToCart(Long itemId) {
        Item item = getService().getItemById(itemId);
        cart.getBasket().getBitems().add(item);
    }

    public void removeItemFromCart(Item item) {
        cart.getBasket().getBitems().remove(item);
        item.setUserSelected(false);
    }

    public void saveBasket() {
        cart.getBasket().setBuser(user);
        getService().saveBasket(cart.getBasket());
        cart.setUserBaskets(getService().loadUserBaskets(user.getId()));
    }

    /**
     * @return the basket
     */
    public String proceedBasket() {
        return "basket";
    }

    /**
     * @return the checkout
     */
    @RolesAllowed("REG_USER")
    public String getCheckout() {
        return "checkout";
    }

    /**
     * @return the logout
     */
    public String getLogout() {
        return "logout";
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return "login";
    }

    public String goPayment() {
        deliveryService.createDeliveryAddress(cart.getDaddress(), user.getId());
        deliveryService.createPersonalAddress(cart.getPaddress(), user.getId());
        return "payment";
    }

    public String goBasket() {
        return "checkout";
    }

    public String goCart() {
        return "shopping";
    }

    public String orderManagement() {
        return "order";
    }

    public String goCheckout() {
        List<ShoppingUserAddress> addresses = deliveryService.listUserAddresses(user.getId());
        for (ShoppingUserAddress address : addresses) {
            if (address.getAddressType().compareTo(AddressType.PERSONAL) == 0) {
                cart.setPaddress((PersonalAddress) address);
            } else {
                cart.setDaddress((DeliveryAddress) address);
            }
        }

        return "checkout";
    }

    public String getAdmin() {
        return "admin";
    }

    /**
     * @return the cart
     */
    public ShoppingCart getCart() {
        return cart;
    }

    /**
     * @param cart the cart to set
     */
    public void setCart(ShoppingCart cart) {
        this.cart = cart;
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
     * @return the items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * @return the messages
     */
    public List<SystemMessage> getMessages() {
        return messages;
    }

    /**
     * @param messages the messages to set
     */
    public void setMessages(List<SystemMessage> messages) {
        this.messages = messages;
    }
}
