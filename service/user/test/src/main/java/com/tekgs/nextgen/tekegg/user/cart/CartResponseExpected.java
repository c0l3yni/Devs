package com.tekgs.nextgen.tekegg.user.cart;

import com.tekgs.nextgen.tekegg.data.cart.CartCalibratable;
import com.tekgs.nextgen.tekegg.data.cart.CartExpected;
import com.tekgs.nextgen.tekegg.data.cart.CartProvider;

import java.util.ArrayList;
import java.util.List;

public class CartResponseExpected implements CartResponseCalibratable {
    private List<CartCalibratable> carts = new ArrayList<>();

    public CartResponseExpected(List<CartExpected> cartExpectedList) {
        if (cartExpectedList == null) {
            this.carts.addAll(CartProvider.getInstance().get());
        } else {
            this.carts.addAll(cartExpectedList);
        }
    }

    public static CartResponseExpected getInstance() {
        return new CartResponseExpected(null);
    }

    public static CartResponseExpected getInstance(List<CartExpected> cartExpectedList) {
        return new CartResponseExpected(cartExpectedList);
    }

    public static CartResponseExpected getInstance(CartExpected cartExpected) {
        List<CartExpected> cartExpectedList = new ArrayList<>();
        cartExpectedList.add(cartExpected);
        return new CartResponseExpected(cartExpectedList);
    }

    @Override
    public boolean isSuccessful() {
        return true;
    }

    @Override
    public List<CartCalibratable> getCarts() {
        return carts;
    }


}
