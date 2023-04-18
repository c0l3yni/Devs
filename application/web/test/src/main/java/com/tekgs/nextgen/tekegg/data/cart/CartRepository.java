package com.tekgs.nextgen.tekegg.data.cart;

import java.util.List;

public class CartRepository {
    public static CartRepository getInstance() {
        return new CartRepository();
    }

    public Cart query(CartCalibratable cartDefinition) {
        for (Cart candidate : getCartsFromService()) {
            if (candidate.equivalent(cartDefinition)) {
                return candidate;
            }
        }
        return null;
    }

    private List<Cart> getCartsFromService() {
        return CartRequest.getInstance().getAll().getCartList();
    }
}
