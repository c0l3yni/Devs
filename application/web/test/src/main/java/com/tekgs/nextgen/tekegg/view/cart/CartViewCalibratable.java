package com.tekgs.nextgen.tekegg.view.cart;

import com.tekgs.nextgen.tekegg.view.cart.item.ItemListRegionCalibratable;

public interface CartViewCalibratable {
    String getTotalAmount();

    ItemListRegionCalibratable inItems();

    String getEmptyMessage();

    String getMinimumTotalMessage();

    Boolean isButtonEnabled();

    String getMaximumTotalMessage();
}
