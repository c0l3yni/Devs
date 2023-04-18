package com.tekgs.nextgen.tekegg.view.cart.item;

import com.tekgs.nextgen.tekegg.data.cart.item.ItemCalibratable;

import java.util.ArrayList;
import java.util.List;

public class ItemListRegionExpected implements ItemListRegionCalibratable {
    private final List<ItemCalibratable> items = new ArrayList<>();

    public ItemListRegionExpected(List<ItemCalibratable> products) {
        this.items.addAll(products);
    }

    public static ItemListRegionExpected getInstance(List<ItemCalibratable> items) {
        return new ItemListRegionExpected(items);
    }

    public List<ItemRegionCalibratable> getItems() {
        List<ItemRegionCalibratable> itemRegions = new ArrayList<>();
        this.items.forEach(item -> itemRegions.add(ItemRegionExpected.getInstance(item)));
        return itemRegions;
    }

    @Override
    public ItemRegionCalibratable getItem(String id) {
        ItemCalibratable itemFound = this.items.stream()
                .filter(expectedItem -> expectedItem.getProduct().getId().equals(id))
                .findFirst()
                .orElse(null);
        return itemFound == null ? null : ItemRegionExpected.getInstance(itemFound);
    }
}
