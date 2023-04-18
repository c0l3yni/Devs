package com.tekgs.nextgen.tekegg.view.cart.item;

import com.tekgs.nextgen.tekegg.data.cart.item.ItemCalibratable;
import com.tekgs.nextgen.tekegg.data.value.Cents;

public class ItemRegionExpected implements ItemRegionCalibratable {
    public static final String IMG_NOT_FOUND_URL = "https://via.placeholder.com/1280x960.png?text=Image+Not+Found";
    private final ItemCalibratable item;

    public ItemRegionExpected(ItemCalibratable item) {
        this.item = item;
    }

    public static ItemRegionExpected getInstance(ItemCalibratable item) {
        return new ItemRegionExpected(item);
    }

    @Override
    public String getProductDescription() {
        String hiddenBackspaceCharacter = "(U+2408)";
        String description = this.item.getProduct().getDescription();
        return description == null || description.contains(hiddenBackspaceCharacter) ? "" : description;
    }

    @Override
    public boolean equivalent(ItemRegionCalibratable comparator) {
        return false;
    }

    @Override
    public Integer getQuantity() {
        return this.item.getQuantity();
    }

    @Override
    public void clickIncreaseQuantity() {
        this.item.increaseQuantity();
    }

    @Override
    public void clickDecreaseQuantity() {
        this.item.decreaseQuantity();
    }

    @Override
    public String getItemTotal() {
        return Cents.getInstance(this.item.getProduct().getPrice() * this.item.getQuantity()).inDollarFormat();
    }

    @Override
    public String getUnitPrice() {
        return Cents.getInstance(this.item.getProduct().getPrice()).inDollarFormat();
    }

    @Override
    public String getThumbnailSrc() {
        String thumbnailUrl = this.item.getProduct().getThumbnail();
        return thumbnailUrl == null ? IMG_NOT_FOUND_URL : thumbnailUrl;
    }
}
