package com.tekgs.nextgen.tekegg.view.purchase;

import com.tekgs.nextgen.tekegg.data.financial.payment.TekEggPayment;

public class PurchaseViewExpected implements PurchaseViewCalibratable {
    private final TekEggPayment payment;

    public PurchaseViewExpected(TekEggPayment payment) {
        this.payment = payment;
    }

    public static PurchaseViewExpected getInstance() {
        return new PurchaseViewExpected(null);
    }

    public static PurchaseViewExpected getInstance(TekEggPayment payment) {
        return new PurchaseViewExpected(payment);
    }

    @Override
    public String getConfirmationMessage() {
        return payment != null && payment.isValid() ? "Purchase was successful" : "Purchase failed";
    }
}
