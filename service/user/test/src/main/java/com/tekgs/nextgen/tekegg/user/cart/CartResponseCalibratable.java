package com.tekgs.nextgen.tekegg.user.cart;

import com.tekgs.nextgen.tekegg.data.cart.Cart;
import com.tekgs.nextgen.tekegg.data.cart.CartCalibratable;

import java.util.List;

public interface CartResponseCalibratable {
    boolean isSuccessful();
    List<CartCalibratable> getCarts();
}
