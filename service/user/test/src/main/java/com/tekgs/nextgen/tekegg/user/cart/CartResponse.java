package com.tekgs.nextgen.tekegg.user.cart;

import com.tekgs.nextgen.tekegg.data.cart.Cart;
import com.tekgs.nextgen.tekegg.data.cart.CartCalibratable;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;


public class CartResponse implements CartResponseCalibratable {
    private final Response.StatusType statusInfo;
    private final List<Cart> carts;
    
    public CartResponse(Response response) {
        statusInfo = response.getStatusInfo();
        carts = response.readEntity(new GenericType<>() {});
    }
    
    public static CartResponse getInstance(Response response) {
        return new CartResponse(response);
    }
    
    @Override
    public boolean isSuccessful() {
        return "OK".equals(statusInfo.getReasonPhrase());
    }
    
    @Override
    public List<CartCalibratable> getCarts() {
        return new ArrayList<>(carts);
    }
}
