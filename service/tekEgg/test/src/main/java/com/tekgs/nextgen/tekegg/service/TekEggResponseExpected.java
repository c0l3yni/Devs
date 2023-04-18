package com.tekgs.nextgen.tekegg.service;

import com.tekgs.nextgen.tekegg.data.cart.Cart;
import com.tekgs.nextgen.tekegg.data.cart.CartCalibratable;
import com.tekgs.nextgen.tekegg.data.cart.CartExpected;

import java.util.ArrayList;
import java.util.List;

public class TekEggResponseExpected implements TekEggResponseCalibratable{
    private final List<CartCalibratable> carts =  new ArrayList<>();

    public TekEggResponseExpected(List<CartExpected> cartExpectedList) {
        if(cartExpectedList != null) this.carts.addAll(cartExpectedList);
    }

    public static TekEggResponseExpected getInstance(CartExpected cartExpected) {
        List<CartExpected> cartExpectedList = new ArrayList<>();
        cartExpectedList.add(cartExpected);
        return new TekEggResponseExpected(cartExpectedList);
    }

    public static TekEggResponseExpected getInstance(){
        return new TekEggResponseExpected(null);
    }

    @Override
    public Boolean isSuccessful() {
        return true;
    }

    @Override
    public List<CartCalibratable> getCarts() {
        return carts;
    }
}
