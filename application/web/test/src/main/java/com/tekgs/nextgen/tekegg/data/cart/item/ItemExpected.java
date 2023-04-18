package com.tekgs.nextgen.tekegg.data.cart.item;

import com.tekgs.nextgen.tekegg.data.product.ProductCalibratable;

public class ItemExpected implements ItemCalibratable {
    private final ProductCalibratable product;
    private Integer quantity;

    public ItemExpected(ProductCalibratable product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public static ItemExpected getInstance(ProductCalibratable product, Integer quantity) {
        return new ItemExpected(product, quantity);
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

    @Override
    public void increaseQuantity() {
        this.quantity += 1;
    }

    @Override
    public void decreaseQuantity() {
        this.quantity -= 1;
    }
    
    @Override
    public Boolean isQuantityGreaterThanFive() {
        return this.getQuantity() > 5;
    }
    
}
