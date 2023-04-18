package com.tekgs.nextgen.tekegg.view.cart.item;

import java.util.List;

public interface ItemListRegionCalibratable {
    List<ItemRegionCalibratable> getItems();
    
    ItemRegionCalibratable getItem(String id);
}
