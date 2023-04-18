package com.tekgs.nextgen.tekegg.data.cart.product;

public class ProductDefinition implements ProductCalibratable {
    private String id;

    public static ProductDefinition getInstance() {
        return new ProductDefinition();
    }

    @Override
    public boolean equivalent(ProductCalibratable comparator) {
        return false;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
