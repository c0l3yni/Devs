package com.tekgs.nextgen.tekegg.view.cart.item;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiRegion;

public class ItemRegion extends UiRegion implements ItemRegionCalibratable {
    protected ItemRegion(UiElement regionElement) {
        super(regionElement);
    }

    public static ItemRegion getInstance(UiElement itemElement){
        return new ItemRegion(itemElement);
    }

    @Override
    public String getProductDescription() {
        return getDescriptionElement().getText();
    }

    @Override
    public boolean equivalent(ItemRegionCalibratable comparator) {
        if(comparator == null){
            return false;
        }
        return comparator.getProductDescription() == null || this.getProductDescription().equals(comparator.getProductDescription());
    }
    
    @Override
    public Integer getQuantity() {
        return Integer.valueOf(getQuantityElement().getText());
    }

    @Override
    public void clickIncreaseQuantity() {
        getIncreaseQuantityElement().click();
    }

    @Override
    public void clickDecreaseQuantity() {
        getDecreaseQuantityElement().click();
    }

    @Override
    public String getItemTotal() {
        return getItemTotalElement().getText();
    }

    @Override
    public String getUnitPrice() {
        return getUnitPriceElement().getText();
    }

    @Override
    public String getThumbnailSrc() {
        UiElement thumbnailElement = UiElement.getInstance("Thumbnail", UiLocatorType.CLASS, "thumbnail", this.getElement());
        return thumbnailElement.getAttribute("src");
    }

    private UiElement getUnitPriceElement(){
        return UiElement.getInstance("unit price", UiLocatorType.CLASS, "unit-price", this.getElement());
    }
    private UiElement getItemTotalElement(){
        return UiElement.getInstance("Item total", UiLocatorType.CLASS, "item-total", this.getElement());
    }
    private UiElement getQuantityElement() {
        return UiElement.getInstance("Quantity", UiLocatorType.CLASS, "item-quantity", this.getElement());
    }
    
    private UiElement getDescriptionElement() {
        return UiElement.getInstance("Description", UiLocatorType.CLASS, "description", this.getElement());
    }

    public UiElement getIncreaseQuantityElement() {
        return UiElement.getInstance("Increase quantity", UiLocatorType.CLASS, "increase-quantity", this.getElement());
    }

    public UiElement getDecreaseQuantityElement() {
        return UiElement.getInstance("Decrease quantity", UiLocatorType.CLASS, "decrease-quantity", this.getElement());
    }

}
