package com.tekgs.nextgen.tekegg.data.cart;

import java.util.List;

public class CartProvider {
    public static CartProvider getInstance() {
        return new CartProvider();
    }
    
    public List<Cart> get() {
        return CartRepository.getInstance().queryAll();
    }
    
    public Cart get(CartCalibratable cartDefinition) {
        return CartRepository.getInstance().query(cartDefinition);
    }
    
}
