package com.tekgs.nextgen.tekegg.data.financial.payment;

import com.google.gson.Gson;

import java.util.Arrays;

public class TekEggPaymentDefinition implements TekEggPaymentCalibratable {
    public static final int VALID_AMOUNT_MIN = 50;
    public static final int VALID_AMOUNT_MAX = 99999999;
    private static final String[] VALID_SOURCES = new String[]{Source.AMEX, "tok_visa", "tok_visa_debit", "tok_mastercard", "tok_mastercard_debit", "tok_mastercard_prepaid", "tok_discover", "tok_diners", "tok_jcb", "tok_unionpay"};
    private Integer amount;
    private String currency;
    private String source;

    public static TekEggPaymentDefinition getInstance() {
        return new TekEggPaymentDefinition();
    }

    public static int getShippingCost() {
        return 1000;
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

    @Override
    public Integer getAmount() {
        return this.amount;
    }

    public boolean isValid() {
        return isAmountValid() && isCurrencyValid() && isSourceValid();
    }

    public Boolean isSourceValid() {
        return Arrays.asList(VALID_SOURCES).contains(this.source);
    }

    private boolean isAmountValid() {
        return amount >= VALID_AMOUNT_MIN && amount <= VALID_AMOUNT_MAX;
    }

    private boolean isCurrencyValid() {
        return Currency.USD.equalsIgnoreCase(currency);
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

    public enum Currency {
        ;
        public static final String USD = "usd";
    }

    public enum Source {
        ;
        public static final String AMEX = "tok_amex";
    }
}
