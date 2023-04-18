package com.tekgs.nextgen.tekegg.view.purchase;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.tekgs.nextgen.tekegg.view.TekEggView;

public class PurchaseView extends TekEggView implements PurchaseViewCalibratable {
    private static final String LOCATOR_VALUE = "purchase";
    private static final String LOCATOR_TYPE = UiLocatorType.ID;
    private static final String DESCRIPTION = "'Purchase' view";
    private static final String RELATIVE_URI = "purchase";
    public static final int PERFORMANCE_BAR_IN_MILLISECONDS = 3000;

    public PurchaseView() {
        super(RELATIVE_URI, UiElement.getInstance(DESCRIPTION, LOCATOR_TYPE, LOCATOR_VALUE));
    }

    private static void waitForPaymentToUnload() {
        String description = "'Payment' view";
        String locatorValue = "payment";
        UiElement paymentViewElement = UiElement.getInstance(description, UiLocatorType.ID, locatorValue);
        //noinspection StatementWithEmptyBody
        long timeout = System.currentTimeMillis() + PERFORMANCE_BAR_IN_MILLISECONDS;
        while (paymentViewElement.isDisplayed() && System.currentTimeMillis() < timeout) ;
    }

    @Override
    public String getConfirmationMessage() {
        String description = "Purchase Confirmation Message";
        String locatorValue = "confirmation-message";
        UiElement confirmationMessage = getUiElementById(description, locatorValue);
        return confirmationMessage.getText();
    }

    @Override
    protected boolean areKeyElementsLoaded() {
        waitForPaymentToUnload();
        String description = "Purchase Confirmation Message";
        String locatorValue = "confirmation-message";
        UiElement getConfirmationMessageElement = getUiElementById(description, locatorValue);
        return getConfirmationMessageElement.waitUntilVisible();
    }
}
