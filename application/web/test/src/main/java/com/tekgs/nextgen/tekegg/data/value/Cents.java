package com.tekgs.nextgen.tekegg.data.value;

import java.text.NumberFormat;
import java.util.Locale;

public class Cents {
    private final Double amount;

    public Cents(Integer amount) {
        this.amount = amount == null ? 0d : (double) amount;
    }

    public Cents(Long amount) {
        this.amount = amount == null ? 0d : (double) amount;
    }

    public static Cents getInstance(Integer amount) {
        return new Cents(amount);
    }

    public static Cents getInstance(Long amount) {
        return new Cents(amount);
    }

    public String inDollarFormat() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        return numberFormat.format(this.amount / 100);
    }
}
