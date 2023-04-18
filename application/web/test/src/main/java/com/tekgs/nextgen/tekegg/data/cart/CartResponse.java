package com.tekgs.nextgen.tekegg.data.cart;

import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class CartResponse {
    private final List<Cart> carts;
    
    private CartResponse(Response response) {
        this.carts = response.readEntity(new GenericType<>() {
        });
    }
    
    public static CartResponse getInstance(Response response) {
        return new CartResponse(response);
    }
    
    public List<Cart> getCartList() {
        return carts;
    }

    public Cart getCart() {
        return this.carts.get(0);
    }
}
