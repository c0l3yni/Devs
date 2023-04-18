package com.tekgs.nextgen.tekegg.data.financial.payment;

import java.util.Arrays;

public class TekEggPayment implements TekEggPaymentCalibratable {
    public static final int VALID_AMOUNT_MIN = 50;
    public static final int VALID_AMOUNT_MAX = 99999999;
    private static final String[] VALID_SOURCES = new String[]{Source.AMEX, "tok_visa", "tok_visa_debit", "tok_mastercard", "tok_mastercard_debit", "tok_mastercard_prepaid", "tok_discover", "tok_diners", "tok_jcb", "tok_unionpay"};
    private final Integer amount;
    private final String currency;
    private final String source;

    public TekEggPayment(TekEggPaymentDefinition tekEggPaymentDefinition) {
        this.amount = tekEggPaymentDefinition.getAmount();
        this.currency = tekEggPaymentDefinition.getCurrency();
        this.source = tekEggPaymentDefinition.getSource();
    }

    public static TekEggPayment getInstance() {
        return new TekEggPayment(TekEggPaymentDefinition.getInstance());
    }

    public static TekEggPayment getInstance(TekEggPaymentDefinition tekEggPaymentDefinition) {
        return new TekEggPayment(tekEggPaymentDefinition);
    }

    public int getShippingCost() {
        return 1000;
    }

    public Boolean isSourceValid() {
        return Arrays.asList(VALID_SOURCES).contains(this.source);
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

    public boolean isValid() {
        return isAmountValid() && isCurrencyValid() && isSourceValid();
    }

    private boolean isAmountValid() {
        return amount >= VALID_AMOUNT_MIN && amount <= VALID_AMOUNT_MAX;
    }

    private boolean isCurrencyValid() {
        return Currency.USD.equalsIgnoreCase(currency);
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
