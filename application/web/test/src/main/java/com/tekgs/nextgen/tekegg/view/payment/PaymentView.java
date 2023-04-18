package com.tekgs.nextgen.tekegg.view.payment;

import com.softwareonpurpose.uinavigator.UiElement;
import com.softwareonpurpose.uinavigator.UiLocatorType;
import com.softwareonpurpose.uinavigator.UiView;
import com.tekgs.nextgen.tekegg.data.cart.Cart;
import com.tekgs.nextgen.tekegg.data.financial.payment.TekEggPayment;
import com.tekgs.nextgen.tekegg.view.TekEggView;
import com.tekgs.nextgen.tekegg.view.purchase.PurchaseView;


public class    PaymentView extends TekEggView implements PaymentViewCalibratable {
    private static final String LOCATOR_VALUE = "payment";
    private static final String LOCATOR_TYPE = UiLocatorType.ID;
    private static final String DESCRIPTION = "'Payment' view";
    private static final String RELATIVE_URI = "payment";

    public PaymentView() {
        super(RELATIVE_URI, UiElement.getInstance(DESCRIPTION, LOCATOR_TYPE, LOCATOR_VALUE));
    }

    public static PaymentView directNav() {
        new PaymentView().load();
        return UiView.expect(PaymentView.class);
    }

    public static PaymentView directNav(Cart cart) {
        new PaymentView().load(String.format("?cart_id=%s", cart.getId()));
        return UiView.expect(PaymentView.class);
    }

    @Override
    public String getTotalAmountDue() {
        String description = "Total Amount Due";
        String locatorValue = "total";
        UiElement totalAmountDue = getUiElementById(description, locatorValue);
        return totalAmountDue.getText();
    }

    @Override
    public String getInvalidCurrencyMessage() {
        String description = "'Currency' error message";
        String locatorValue = "currency-error-message";
        UiElement invalidCurrencyMessageElement = getUiElementById(description, locatorValue);
        return invalidCurrencyMessageElement.getText();
    }

    @Override
    public String getInvalidSourceMessage() {
        String description = "'Source' error message";
        String locatorValue = "source-error-message";
        UiElement invalidSourceMessageElement = getUiElementById(description, locatorValue);
        return invalidSourceMessageElement.getText();
    }

    @Override
    public String getShippingAmount() {
        String description = "'Shipping' amount";
        String locatorValue = "shipping-amount";
        UiElement shippingAmountElement = getUiElementById(description, locatorValue);
        return shippingAmountElement.getText();
    }

    @Override
    public String getSubtotalAmount() {
        String description = "Subtotal Amount";
        String locatorValue = "subtotal-amount";
        UiElement subtotalAmountElement = getUiElementById(description, locatorValue);
        return subtotalAmountElement.getText();
    }

    public PurchaseView submit(TekEggPayment payment) {
        getCurrencyElement().set(payment.getCurrency());
        getSourceElement().set(payment.getSource());
        getSubmitElement().click();
        return UiView.expect(PurchaseView.class);
    }

    public PaymentView submitInvalid(TekEggPayment payment) {
        getCurrencyElement().set(payment.getCurrency());
        getSourceElement().set(payment.getSource());
        getSubmitElement().click();
        return UiView.expect(PaymentView.class);
    }

    private UiElement getCurrencyElement() {
        String description = "Currency";
        String locatorValue = "currency";
        return UiElement.getInstance(description, UiLocatorType.NAME, locatorValue, this.getElement());
    }

    private UiElement getSourceElement() {
        String description = "Source";
        String locatorValue = "source";
        return UiElement.getInstance(description, UiLocatorType.NAME, locatorValue, this.getElement());
    }

    private UiElement getSubmitElement() {
        String description = "'Submit' button";
        String locatorValue = "submit";
        return getUiElementById(description, locatorValue);
    }

    @Override
    protected boolean areKeyElementsLoaded() {
        return true;
    }
}
