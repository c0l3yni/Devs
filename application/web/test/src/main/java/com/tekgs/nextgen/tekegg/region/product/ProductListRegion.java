package com.tekgs.nextgen.tekegg.region.product;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiRegion;

import java.util.ArrayList;
import java.util.List;

public class ProductListRegion extends UiRegion implements ProductListRegionCalibratable {
    private static final String DESCRIPTION = "'Products' region";
    private static final String LOCATOR_TYPE = UiLocatorType.ID;
    private static final String LOCATOR_VALUE = "product-list";

    protected ProductListRegion(UiElement parentElement) {
        super(UiElement.getInstance(DESCRIPTION, LOCATOR_TYPE, LOCATOR_VALUE, parentElement));
    }

    public static ProductListRegion getInstance(UiElement parent) {
        return new ProductListRegion(parent);
    }

    @Override
    public List<ProductRegionCalibratable> getProducts() {
        List<ProductRegionCalibratable> products = new ArrayList<>();
        List<UiElement> productElementList = UiElement.getList("Product", UiLocatorType.CLASS, "product", this.getElement());
        productElementList.forEach(productElement -> {
            products.add(ProductRegion.getInstance(productElement));
        });
        return products;
    }


}
