package com.tekgs.nextgen.tekegg.data.cart.item;

import com.tekgs.nextgen.tekegg.data.product.ProductCalibratable;

public class ItemDefinition implements ItemCalibratable {
    private ProductCalibratable product;
    private Integer quantity;
    private boolean isQuantityGreaterThanFive = false;
    
    public static ItemDefinition getInstance() {
        return new ItemDefinition();
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
        return this.getQuantity() == null ? this.isQuantityGreaterThanFive : this.getQuantity() > 5;
    }
    
    public ItemDefinition withProduct(ProductCalibratable product) {
        this.product = product;
        return this;
    }

    public ItemDefinition withQuantity(int newQuantity) {
        this.quantity = newQuantity;
        return this;
    }
    
    public ItemDefinition withQuantityGreaterThanFive(boolean isQuantityGreaterThanFive) {
        this.isQuantityGreaterThanFive = isQuantityGreaterThanFive;
        return this;
    }
}
