package com.tekgs.nextgen.tekegg.service;

import com.tekgs.nextgen.tekegg.data.cart.Cart;
import com.tekgs.nextgen.tekegg.data.cart.CartCalibratable;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

public class TekEggResponse implements TekEggResponseCalibratable{
    public static final String successMessage = "OK";
    private final Response.StatusType statusInfo;
    private List<Cart> carts = new ArrayList<>();

    private TekEggResponse(Response response) {
        this.statusInfo = response.getStatusInfo();
        if(response.hasEntity()) carts = response.readEntity(new GenericType<>() {});
    }

    public static TekEggResponse getInstance(Response response) {
        return new TekEggResponse(response);
    }


    @Override
    public Boolean isSuccessful() {
        return successMessage.equals(statusInfo.getReasonPhrase());
    }

    @Override
    public List<CartCalibratable> getCarts() {
        return new ArrayList<>(carts);
    }

    public Cart getCart(){
        return this.carts.get(0);
    }
}
