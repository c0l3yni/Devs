package com.tekgs.nextgen.tekegg.data.cart;

import java.util.Comparator;
import java.util.List;

public class CartProvider {
    public static CartProvider getInstance() {
        return new CartProvider();
    }
    
    public Cart get(CartCalibratable cartDefinition) {
        Cart cart = null;
        List<Cart> cartList = CartRepository.getInstance().query().stream().sorted(Comparator.comparingLong(Cart::getUpdatedAt).reversed()).toList();
        for (Cart candidate : cartList) {
            Cart cartToUpdate = CartRequest.getInstance(candidate).get().getCart();
            if (cartToUpdate.equivalent(cartDefinition)) {
                cart = cartToUpdate;
                break;
            }
        }
        
        if (cart != null) {
            CartRequest.getInstance(cart).put();
        } else {
            cart = CartRequest.getInstance(cartDefinition.toPayload()).post().getCart();
        }
        
        
        return cart;
        
    }
}
