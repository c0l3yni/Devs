package com.tekgs.nextgen.tekegg.data.cart;


import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

public class CartRequest {
    private static final String DOMAIN_URL = "http://localhost:8000";
    private static final String ENDPOINT = "cart/";
    private static final String USER_SERVICE_URI = String.format("%s/%s", DOMAIN_URL, ENDPOINT);
    private final CartRequestPayload cartRequestPayload;
    Client client;


    private CartRequest(CartCalibratable cart) {
        this.client = ClientBuilder.newClient();
        this.cartRequestPayload = CartRequestPayload.getInstance(cart);
    }

    public static CartRequest getInstance(CartCalibratable cart) {
        return new CartRequest(cart);
    }

    public CartResponse get() {
        CartResponse cartResponse;
        String url = String.format("%s/%s", USER_SERVICE_URI, this.cartRequestPayload.getCart().getId());
        WebTarget target = client.target(url);
        try (Response response = target.request().get()) {
            cartResponse = CartResponse.getInstance(response);
        } finally {
            client.close();
        }
        return cartResponse;
    }

    public CartResponse put() {
        String putUrl = String.format("%s/%s", USER_SERVICE_URI, this.cartRequestPayload.getCart().getId());
        WebTarget target = client.target(putUrl);
        CartResponse cartResponse;
        Entity<CartRequestPayload> payload = Entity.entity(cartRequestPayload, "application/json");
        try (Response response = target.request().put(payload)) {
            cartResponse = CartResponse.getInstance(response);
        } finally {
            client.close();
        }
        return cartResponse;
    }

    public CartResponse post() {
        WebTarget target = client.target(USER_SERVICE_URI);
        CartResponse cartResponse;
        Entity<CartRequestPayload> payload = Entity.entity(cartRequestPayload, "application/json");
        try (Response response = target.request().post(payload)) {
            cartResponse = CartResponse.getInstance(response);
        } finally {
            client.close();
        }
        return cartResponse;
    }


}
