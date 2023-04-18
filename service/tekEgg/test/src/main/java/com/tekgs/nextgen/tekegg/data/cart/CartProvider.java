package com.tekgs.nextgen.tekegg.data.cart;

import com.tekgs.nextgen.tekegg.service.TekEggRequest;

import java.util.Comparator;
import java.util.List;

public class CartProvider {
    public static CartProvider getInstance() {
        return new CartProvider();
    }

    public Cart get(CartCalibratable cartDefinition) {
        Cart cart = null;
        List<Cart> cartList = CartRepository.getInstance().query().stream().sorted(Comparator.comparingLong(Cart::getUpdatedAt)).toList();
        for (Cart candidate : cartList) {
            Cart cartToUpdate = TekEggRequest.getInstance(candidate).get().getCart();
            if (cartToUpdate.equivalent(cartDefinition)) {
                cart = cartToUpdate;
            }
        }

        if (cart != null) {
            TekEggRequest.getInstance(cart).put();
        }
        return cart;
    }
    
}
