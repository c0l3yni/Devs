package com.tekgs.nextgen.tekegg.view.products;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiView;
import com.tekgs.nextgen.tekegg.data.cart.CartCalibratable;
import com.tekgs.nextgen.tekegg.region.product.ProductListRegion;
import com.tekgs.nextgen.tekegg.view.TekEggView;
import com.tekgs.nextgen.tekegg.view.cart.CartView;

public class ProductsView extends TekEggView implements ProductsViewCalibratable {
    private static final String RELATIVE_URI = "products";
    private static final String LOCATOR_VALUE = "products-view";
    private static final String DESCRIPTION = "'Products' view";
    private static final String LOCATOR_TYPE = UiLocatorType.ID;

    public ProductsView() {
        super(RELATIVE_URI, UiElement.getInstance(DESCRIPTION, LOCATOR_TYPE, LOCATOR_VALUE));
    }

    public static ProductsView directNav() {
        new ProductsView().load();
        return TekEggView.expect(ProductsView.class);
    }

    public static ProductsView directNav(CartCalibratable cart) {
        new ProductsView().load(String.format("?cart_id=%s", cart.getId()));
        return  TekEggView.expect(ProductsView.class);
    }

    @Override
    protected boolean areKeyElementsLoaded() {
        return this.getElement().isDisplayed();
    }

    @Override
    public ProductListRegion inProducts() {
        return ProductListRegion.getInstance(this.getElement());
    }

    @Override
    public String getEmptyMessage() {
        String description = "'Empty' Message";
        String locatorValue = "empty";
        UiElement emptyMessageElement = getUiElementById(description, locatorValue);
        return emptyMessageElement.getText();
    }
}
