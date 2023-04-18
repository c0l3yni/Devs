package com.tekgs.nextgen.tekegg.data.cart;

import com.tekgs.nextgen.tekegg.data.cart.item.ItemCalibratable;

import java.util.List;

public interface CartCalibratable {
    Integer getTotal();

    String getId();

    boolean equivalent(CartCalibratable comparator);

    List<ItemCalibratable> getItems();

    Boolean isPurchasable();
    
    Boolean isCartEmpty();

    CartCalibratable toPayload();
}
