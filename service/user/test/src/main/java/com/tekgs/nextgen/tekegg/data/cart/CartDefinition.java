package com.tekgs.nextgen.tekegg.data.cart;


import com.google.gson.Gson;
import com.tekgs.nextgen.tekegg.data.cart.item.ItemCalibratable;

import java.util.ArrayList;
import java.util.List;

public class CartDefinition implements CartCalibratable {
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final List<ItemCalibratable> items = new ArrayList<>();
    private Integer total;
    private String id;
    
    public static CartDefinition getInstance() {
        return new CartDefinition();
    }
    
    @Override
    public Integer getTotal() {
        return this.total;
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
    
    public CartDefinition withID(String ID) {
        this.id = ID;
        return this;
    }
}
