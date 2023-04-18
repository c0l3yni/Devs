package com.tekgs.nextgen.tekegg.service;

import com.tekgs.nextgen.tekegg.data.cart.Cart;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.Response;

public class TekEggRequest {
    private static final String DOMAIN_URL = "http://localhost:8000";

    private static final String END_POINT = "cart";

    private static final String TEKEGG_CART_URI = String.format("%s/%s",DOMAIN_URL,END_POINT);
    private final Client client;

    private final TekEggRequestPayload tekEggRequestPayload;

    public TekEggRequest(Cart cart) {
        this.client = ClientBuilder.newClient();
        this.tekEggRequestPayload = TekEggRequestPayload.getInstance(cart);
    }

    public static TekEggRequest getInstance(Cart cart) {
        return new TekEggRequest(cart);
    }

    public static TekEggRequest getInstance() { return new TekEggRequest(null);}

    public TekEggResponse get() {
        WebTarget target = this.client.target(String.format("%s/%s",TEKEGG_CART_URI, this.tekEggRequestPayload.getCart().getId()));
        TekEggResponse tekEggResponse;
        try(Response response = target.request().get()){
            tekEggResponse = TekEggResponse.getInstance(response);
        }
        finally {
            client.close();
        }
        return tekEggResponse;
    }

    public TekEggResponse head() {
        WebTarget target = this.client.target(TEKEGG_CART_URI);
        TekEggResponse tekEggResponse;
        try(Response response = target.request().head()){
            tekEggResponse = TekEggResponse.getInstance(response);
        }
        finally {
            client.close();
        }
        return tekEggResponse;
    }

    public TekEggResponse put() {
        TekEggResponse cartResponse;
        WebTarget target = this.client.target(String.format("%s/%s",TEKEGG_CART_URI, this.tekEggRequestPayload.getCart().getId()));
        Entity<TekEggRequestPayload> payload = Entity.entity(tekEggRequestPayload, "application/json");
        try (Response response = target.request().put(payload)) {
            cartResponse = TekEggResponse.getInstance(response);
        } finally {
            client.close();
        }
        return cartResponse;
    }
}
