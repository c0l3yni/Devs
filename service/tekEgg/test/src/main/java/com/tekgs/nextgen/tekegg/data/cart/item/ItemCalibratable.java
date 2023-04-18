package com.tekgs.nextgen.tekegg.data.cart.item;

import com.tekgs.nextgen.tekegg.data.cart.product.ProductCalibratable;

public interface ItemCalibratable {
    ProductCalibratable getProduct();

    Integer getQuantity();

    boolean equivalent(ItemCalibratable comparator);
}
