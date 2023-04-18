package com.tekgs.nextgen.tekegg.inventory;

import com.tekgs.nextgen.tekegg.data.product.ProductCalibratable;

import java.util.List;

public interface InventoryResponseCalibratable {
    Boolean isSuccessful();
    List<ProductCalibratable> getProducts();
}
