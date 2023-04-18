package com.tekgs.nextgen.tekegg.inventory;

import com.tekgs.nextgen.tekegg.data.InventoryProvider;
import com.tekgs.nextgen.tekegg.data.product.ProductCalibratable;
import com.tekgs.nextgen.tekegg.data.product.ProductExpected;

import java.util.ArrayList;
import java.util.List;

public class InventoryResponseExpected implements InventoryResponseCalibratable {
    private final List<ProductCalibratable> products = new ArrayList<>();
    
    public InventoryResponseExpected(List<ProductExpected> productExpectedList) {
        this.products.addAll(productExpectedList == null ? InventoryProvider.getInstance().get() : productExpectedList);
    }

    public static InventoryResponseExpected getInstance() {
        return new InventoryResponseExpected(null);
    }

    public static InventoryResponseExpected getInstance(ProductExpected productExpected) {
        List<ProductExpected> productExpectedList = new ArrayList<>();
        productExpectedList.add(productExpected);
        return new InventoryResponseExpected(productExpectedList);
    }

    @Override
    public Boolean isSuccessful() {
        return true;
    }

    @Override
    public List<ProductCalibratable> getProducts() {
        return this.products;
    }
}
