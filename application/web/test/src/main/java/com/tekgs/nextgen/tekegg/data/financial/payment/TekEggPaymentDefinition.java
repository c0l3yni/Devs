package com.tekgs.nextgen.tekegg.data.financial.payment;

import com.google.gson.Gson;

public class TekEggPaymentDefinition implements TekEggPaymentCalibratable {
    private Integer amount;
    private String currency;
    private String source;

    public static TekEggPaymentDefinition getInstance() {
        return new TekEggPaymentDefinition();
    }

    public TekEggPaymentDefinition withAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public TekEggPaymentDefinition withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public TekEggPaymentDefinition withSource(String source) {
        this.source = source;
        return this;
    }

    public TekEggPayment toPayment() {
        return TekEggPayment.getInstance(this);
    }

    @Override
    public Integer getAmount() {
        return this.amount;
    }


    @Override
    public String getSource() {
        return this.source;
    }

    @Override
    public String getCurrency() {
        return this.currency;
    }

    @Override
    public String toString() {
        return String.format("TekEggPaymentDefinition %s ", new Gson().toJson(this));
    }
}
