package com.tekgs.nextgen.tekegg.view.cart.item;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiRegion;

import java.util.ArrayList;
import java.util.List;

public class ItemListRegion extends UiRegion implements ItemListRegionCalibratable {
    private static final String DESCRIPTION = "'Item List' region";
    private static final String LOCATOR_TYPE = UiLocatorType.ID;
    private static final String LOCATOR_VALUE = "item-list";

    private ItemListRegion(UiElement parent) {
        super(UiElement.getInstance(DESCRIPTION, LOCATOR_TYPE, LOCATOR_VALUE, parent));
    }

    public static ItemListRegion getInstance(UiElement parent) {
        return new ItemListRegion(parent);
    }

    @Override
    public List<ItemRegionCalibratable> getItems() {
        List<ItemRegionCalibratable> itemRegions = new ArrayList<>();
        List<UiElement> itemElementList = UiElement.getList("Item", UiLocatorType.CLASS, "item", this.getElement());
        itemElementList.forEach(itemElement -> {
            itemRegions.add(ItemRegion.getInstance(itemElement));
        });
        return itemRegions;
    }
    
    @Override
    public ItemRegionCalibratable getItem(String id) {
        UiElement itemElement = UiElement.getInstance("Item", UiLocatorType.CLASS, "item", "data-product", String.valueOf(id), this.getElement());
        return ItemRegion.getInstance(itemElement);
    }
}
