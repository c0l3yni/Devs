package com.tekgs.nextgen.tekegg.data.cart.product;

public class Product implements ProductCalibratable {
    private final String id;

    public Product() {
        this.id = null;
    }
    @Override
    public boolean equivalent(ProductCalibratable comparator) {
        return comparator.getId() != null && comparator.getId().equals(this.getId());
    }
    @Override
    public String getId() {
        return this.id;
    }
}
