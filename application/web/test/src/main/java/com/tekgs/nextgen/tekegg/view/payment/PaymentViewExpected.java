package com.tekgs.nextgen.tekegg.view.payment;

import com.tekgs.nextgen.tekegg.data.cart.Cart;
import com.tekgs.nextgen.tekegg.data.cart.CartCalibratable;
import com.tekgs.nextgen.tekegg.data.financial.payment.TekEggPayment;
import com.tekgs.nextgen.tekegg.data.value.Cents;


public class PaymentViewExpected implements PaymentViewCalibratable {
    private final TekEggPayment payment;
    private final Cents totalAmount;
    private final Cents subtotalAmount;
    private final Cents shippingAmount;


    public PaymentViewExpected(CartCalibratable cart, TekEggPayment payment) {
        int subtotal = cart == null ? 0 : cart.getTotal();
        int initialShippingAmount = subtotal == 0 ? 0 : 1000;
        int total = subtotal + initialShippingAmount;
        this.subtotalAmount = Cents.getInstance(subtotal);
        this.shippingAmount = Cents.getInstance(initialShippingAmount);
        this.totalAmount = Cents.getInstance(total);
        this.payment = payment;
    }

    public static PaymentViewExpected getInstance(TekEggPayment payment) {
        return new PaymentViewExpected(null, payment);
    }

    public static PaymentViewExpected getInstance(CartCalibratable cart) {
        return new PaymentViewExpected(cart, null);
    }

    public static PaymentViewExpected getInstance(Cart cart, TekEggPayment payment) {
        return new PaymentViewExpected(cart, payment);
    }

    public static PaymentViewExpected getInstance() {
        return new PaymentViewExpected(null, null);
    }

    @Override
    public String getTotalAmountDue() {
        return this.totalAmount.inDollarFormat();
    }

    @Override
    public String getInvalidCurrencyMessage() {
        return this.payment == null ? "" : "usd".equals(this.payment.getCurrency()) ? "" : "Currency invalid";
    }

    @Override
    public String getInvalidSourceMessage() {
        return this.payment == null ? "" : this.payment.isSourceValid() ? "" : "Source invalid";
    }

    @Override
    public String getShippingAmount() {
        return this.shippingAmount.inDollarFormat();
    }

    @Override
    public String getSubtotalAmount() {
        return this.subtotalAmount.inDollarFormat();
    }
}
