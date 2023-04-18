package com.tekgs.nextgen.tekegg.data.cart.item;

import com.tekgs.nextgen.tekegg.data.cart.product.Product;
import com.tekgs.nextgen.tekegg.data.cart.product.ProductCalibratable;

public class Item implements ItemCalibratable {
    private final Integer quantity;
    private final Product product;
    
    public Item() {
        this.quantity = null;
        this.product = null;
    }
    
    @Override
    public ProductCalibratable getProduct() {
        return product;
    }
    
    @Override
    public Integer getQuantity() {
        return quantity;
    }
    
    @Override
    public boolean equivalent(ItemCalibratable comparator) {
        if (comparator == null) {
            return false;
        }
        if (comparator.getProduct().getId() != null && this.getProduct().getId().equals(comparator.getProduct().getId())) {
            return true;
        }
        @SuppressWarnings("ConstantConditions")
        boolean isEquivalent = this.product.equivalent(comparator.getProduct());
        isEquivalent &= comparator.getQuantity() == null || this.getQuantity().equals(comparator.getQuantity());
        return isEquivalent;
    }
    
}
