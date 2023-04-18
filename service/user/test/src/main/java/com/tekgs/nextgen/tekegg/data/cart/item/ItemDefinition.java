package com.tekgs.nextgen.tekegg.data.cart.item;

import com.tekgs.nextgen.tekegg.data.cart.product.ProductCalibratable;

public class ItemDefinition implements ItemCalibratable {
    private ProductCalibratable product;
    private Integer quantity;
    
    public ItemDefinition(ItemCalibratable cartItem) {
        if (cartItem != null) {
            this.product = cartItem.getProduct();
            this.quantity = cartItem.getQuantity();
        }
    }
    
    public static ItemDefinition getInstance() {
        return new ItemDefinition(null);
    }
    
    public static ItemDefinition getInstance(ItemCalibratable cartItem) {
        return new ItemDefinition(cartItem);
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
        return false;
    }

    public ItemDefinition withProduct(ProductCalibratable product) {
        this.product = product;
        return this;
    }
    
    public ItemDefinition withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
}
