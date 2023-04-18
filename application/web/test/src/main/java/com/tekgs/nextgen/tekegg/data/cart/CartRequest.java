package com.tekgs.nextgen.tekegg.data.cart;


import com.google.gson.Gson;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

public class CartRequest {
    private static final String DOMAIN_URL = "http://localhost:8000";
    private static final String ENDPOINT = "cart/";
    private static final String USER_SERVICE_URI = String.format("%s/%s", DOMAIN_URL, ENDPOINT);
    Client client;


    private CartRequest() {
        this.client = ClientBuilder.newClient();
    }

    public static CartRequest getInstance() {
        return new CartRequest();
    }

    public CartResponse getAll() {
        CartResponse cartResponse;
        WebTarget target = client.target(USER_SERVICE_URI);
        try (Response response = target.request().get()) {
            cartResponse = CartResponse.getInstance(response);
        } finally {
            client.close();
        }
        return cartResponse;
    }
}
