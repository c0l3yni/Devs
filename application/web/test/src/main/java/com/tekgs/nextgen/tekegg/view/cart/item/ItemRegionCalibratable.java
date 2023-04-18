package com.tekgs.nextgen.tekegg.view.cart.item;

public interface ItemRegionCalibratable {
    String getProductDescription();

    boolean equivalent(ItemRegionCalibratable comparator);
    
    Integer getQuantity();
    void clickIncreaseQuantity();
    void clickDecreaseQuantity();

    String getItemTotal();

    String getUnitPrice();

    String getThumbnailSrc();
}
