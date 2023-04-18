package com.tekgs.nextgen.tekegg.service;

import com.tekgs.nextgen.tekegg.data.cart.Cart;
import com.tekgs.nextgen.tekegg.data.cart.CartCalibratable;

public class TekEggRequestPayload {
    private final Cart cart;

    public TekEggRequestPayload(Cart cart) {
        this.cart = cart;
    }

    public static TekEggRequestPayload getInstance(Cart cart) {
        return new TekEggRequestPayload(cart);
    }

    public Cart getCart() {
        return cart;
    }
}
