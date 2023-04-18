package com.tekgs.nextgen.tekegg.data.cart;


import com.google.gson.Gson;
import com.tekgs.nextgen.tekegg.data.cart.item.ItemCalibratable;
import com.tekgs.nextgen.tekegg.data.cart.item.ItemDefinition;

import java.util.ArrayList;
import java.util.List;

public class CartDefinition implements CartCalibratable {
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final List<ItemCalibratable> items = new ArrayList<>();
    private String id;
    
    private CartDefinition(CartCalibratable cart) {
        if (cart != null) {
            this.items.addAll(cart.getItems());
            this.id = cart.getId();
        }
    }
    
    public static CartDefinition getInstance() {
        return new CartDefinition(null);
    }
    
    public static CartDefinition getInstance(CartCalibratable cart) {
        return new CartDefinition(cart);
    }
    
    @Override
    public List<ItemCalibratable> getItems() {
        return new ArrayList<>(this.items);
    }
    
    @Override
    public boolean equivalent(CartCalibratable comparator) {
        return false;
    }
    
    @Override
    public String getId() {
        return this.id;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s ", this.getClass().getSimpleName(), new Gson().toJson(this));
    }
    
    public CartDefinition withUpdatedItem(ItemDefinition itemDefinition) {
        this.items.set(0, itemDefinition);
        return this;
    }
}
