package com.tekgs.nextgen.tekegg.region.product;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiRegion;

public class ProductRegion extends UiRegion implements ProductRegionCalibratable {
    protected ProductRegion(UiElement productElement) {
        super(productElement);
    }

    public static ProductRegion getInstance(UiElement productElement) {

        return new ProductRegion(productElement);
    }

    @Override
    public boolean equivalent(ProductRegionCalibratable comparator) {
        return comparator.getProductDescription() == null || this.getProductDescription().equals(comparator.getProductDescription());
    }

    @Override
    public String getProductDescription() {
        return getDescriptionElement().getText();
    }

    @Override
    public String getOutOfStockMessage() {
        return getOutOfStockMessageElement().getText();
    }

    @Override
    public String getButtonDisabledStatus() {
        return getButtonElement().getAttribute("disabled");
    }

    private UiElement getOutOfStockMessageElement() {
        String description = "Out of Stock";
        String locatorValue = "out-of-stock";
        return UiElement.getInstance(description, UiLocatorType.CLASS, locatorValue, this.getElement());
    }

    private UiElement getDescriptionElement() {
        String description = "Description";
        String locatorValue = "description";
        return UiElement.getInstance(description, UiLocatorType.CLASS, locatorValue, this.getElement());
    }

    private UiElement getButtonElement() {
        String description = "Add to 'Cart' button";
        String locatorValue = "add-to-cart";
        return UiElement.getInstance(description, UiLocatorType.CLASS, locatorValue, this.getElement());
    }
}
