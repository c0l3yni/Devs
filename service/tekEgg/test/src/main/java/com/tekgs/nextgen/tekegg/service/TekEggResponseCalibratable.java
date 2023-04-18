package com.tekgs.nextgen.tekegg.service;

import com.tekgs.nextgen.tekegg.data.cart.Cart;
import com.tekgs.nextgen.tekegg.data.cart.CartCalibratable;

import java.util.List;

public interface TekEggResponseCalibratable {
    Boolean isSuccessful();

    List<CartCalibratable> getCarts();
}
