package com.tekgs.nextgen.tekegg.user.cart;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

public class CartRequest {
    private static final String DOMAIN_URL = "http://localhost:8000";
    private static final String ENDPOINT = "cart";
    private static final String USER_SERVICE_URI = String.format("%s/%s", DOMAIN_URL, ENDPOINT);
    private final CartRequestPayload cartRequestPayload;
    Client client;
    
    private CartRequest(String cartId, String productId, Integer newQuantity) {
        this.client = ClientBuilder.newClient();
        this.cartRequestPayload = CartRequestPayload.getInstance(cartId, productId, newQuantity);
    }
    
    public static CartRequest getInstance() {
        return new CartRequest(null, null, null);
    }
    
    public static CartRequest getInstance(String cartId) {
        return new CartRequest(cartId, null, null);
    }
    
    public static CartRequest getInstance(String cartId, String productId, int newQuantity) {
        return new CartRequest(cartId, productId, newQuantity);
    }
    
    private CartResponse get(WebTarget target) {
        CartResponse cartResponse;
        try (Response response = target.request().get()) {
            cartResponse = CartResponse.getInstance(response);
        } finally {
            client.close();
        }
        return cartResponse;
    }
    
    public CartResponse getAll() {
        WebTarget target = client.target(USER_SERVICE_URI);
        return get(target);
    }
    
    public CartResponse getById() {
        String getUrl = String.format("%s/%s", USER_SERVICE_URI, this.cartRequestPayload.getCartId());
        WebTarget target = client.target(getUrl);
        return get(target);
    }
    
    public CartResponse put() {
        CartResponse cartResponse;
        String putUrl = String.format("%s/%s", USER_SERVICE_URI, this.cartRequestPayload.getCartId());
        WebTarget target = client.target(putUrl);
        Entity<CartRequestPayload> payload = Entity.entity(cartRequestPayload, "application/json");
        try (Response response = target.request().put(payload)) {
            cartResponse = CartResponse.getInstance(response);
        } finally {
            client.close();
        }
        return cartResponse;
    }
}
