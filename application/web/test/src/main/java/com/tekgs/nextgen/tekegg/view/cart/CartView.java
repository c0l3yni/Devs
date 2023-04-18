package com.tekgs.nextgen.tekegg.view.cart;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiView;
import com.tekgs.nextgen.tekegg.data.cart.CartCalibratable;
import com.tekgs.nextgen.tekegg.data.product.ProductCalibratable;
import com.tekgs.nextgen.tekegg.view.TekEggView;
import com.tekgs.nextgen.tekegg.view.cart.item.ItemListRegion;
import com.tekgs.nextgen.tekegg.view.cart.item.ItemRegionCalibratable;
import com.tekgs.nextgen.tekegg.view.payment.PaymentView;


public class CartView extends TekEggView implements CartViewCalibratable {
    private static final String LOCATOR_VALUE = "cart";
    private static final String LOCATOR_TYPE = UiLocatorType.ID;
    private static final String DESCRIPTION = " 'Cart' view";
    private static final String RELATIVE_URI = "cart";

    public CartView() {
        super(RELATIVE_URI, UiElement.getInstance(DESCRIPTION, LOCATOR_TYPE, LOCATOR_VALUE));
    }

    public static CartView directNav() {
        new CartView().load();
        return UiView.expect(CartView.class);
    }

    public static CartView directNav(CartCalibratable cart) {
        new CartView().load(String.format("?cart_id=%s", cart.getId()));
        return UiView.expect(CartView.class);
    }

    @Override
    public String getTotalAmount() {
        String description = "'Total' amount";
        String locatorValue = "total";
        UiElement totalAmountElement = getUiElementById(description, locatorValue);
        return totalAmountElement.getText();
    }

    @Override
    public ItemListRegion inItems() {
        return ItemListRegion.getInstance(this.getElement());
    }

    @Override
    public String getEmptyMessage() {
        String description = "'Empty' message";
        String locatorValue = "empty";
        UiElement emptyMessageElement = getUiElementById(description, locatorValue);
        return emptyMessageElement.getText();
    }

    @Override
    public String getMinimumTotalMessage() {
        String locatorValue = "minimum-total-message";
        String description = "'Minimum Total' message";
        UiElement minimumTotalMessageElement = getUiElementById(description, locatorValue);
        return minimumTotalMessageElement.getText();
    }

    @Override
    public String getMaximumTotalMessage() {
        String locatorValue = "maximum-total-message";
        String description = "'Maximum Total' message";
        UiElement maximumTotalMessageElement = getUiElementById(description, locatorValue);
        return maximumTotalMessageElement.getText();
    }

    @Override
    public Boolean isButtonEnabled() {
        return !Boolean.parseBoolean(getCheckoutElement().getAttribute("disabled"));
    }

    private UiElement getCheckoutElement() {
        String description = "'Checkout' button";
        String locatorValue = "checkout";
        return getUiElementById(description, locatorValue);
    }

    public PaymentView checkout() {
        getCheckoutElement().click();
        return UiView.expect(PaymentView.class);
    }

    public CartView updateQuantity(ProductCalibratable product, int newQuantity) {
        ItemRegionCalibratable itemToUpdate = inItems().getItem(product.getId());
        Integer startingQuantity = itemToUpdate.getQuantity();
        int quantityDifference = newQuantity - startingQuantity;
        while (quantityDifference != 0) {
            if (quantityDifference > 0) {
                itemToUpdate.clickIncreaseQuantity();
                quantityDifference -= 1;
            } else {
                itemToUpdate.clickDecreaseQuantity();
                quantityDifference += 1;
            }
        }
        return UiView.expect(CartView.class);
    }

    @Override
    protected boolean areKeyElementsLoaded() {
        return true;
    }
}
