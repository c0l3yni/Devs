package com.tekgs.nextgen.tekegg.data.cart.item;

import com.tekgs.nextgen.tekegg.data.cart.product.ProductCalibratable;

@SuppressWarnings("ClassCanBeRecord")
public class ItemExpected implements ItemCalibratable {
    private final ProductCalibratable product;
    private final Integer quantity;
    
    public ItemExpected(ProductCalibratable product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    
    public static ItemExpected getInstance(ProductCalibratable product, Integer quantity) {
        return new ItemExpected(product, quantity);
    }
    
    @Override
    public ProductCalibratable getProduct() {
        return this.product;
    }
    
    @Override
    public Integer getQuantity() {
        return this.quantity;
    }
    
    @Override
    public boolean equivalent(ItemCalibratable comparator) {
        return false;
    }
}
