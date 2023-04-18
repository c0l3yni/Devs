package com.tekgs.nextgen.tekegg.user.cart;

import com.tekgs.nextgen.tekegg.data.cart.CartCalibratable;

@SuppressWarnings("ClassCanBeRecord")
public class CartRequestPayload {
    
    private final CartCalibratable cart;
    
    public CartRequestPayload(CartCalibratable cart) {
        this.cart = cart;
        
    }
    
    public static CartRequestPayload getInstance(CartCalibratable cart) {
        return new CartRequestPayload(cart);
    }
    
    public CartCalibratable getCart() {
        return this.cart;
    }
}
